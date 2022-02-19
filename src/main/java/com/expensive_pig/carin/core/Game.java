package com.expensive_pig.carin.core;

import com.expensive_pig.carin.entity.Entity;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.evaluator.SyntaxError;
import com.expensive_pig.carin.event.InputEvent;
import com.expensive_pig.carin.event.InputEventQueue;
import com.expensive_pig.carin.game_data.Credit;
import com.expensive_pig.carin.game_data.GameConfiguration;
import com.expensive_pig.carin.game_data.WorldGame;
import lombok.Getter;

import java.util.Map;

@Getter
public class Game implements Runnable {
    private String sessionId;
    private volatile boolean isGameRunning = true;

    private GameConfiguration config;
    private InputEventQueue inputEventQueue;
    private WorldGame world;
    private Credit credit;
    private EntityFactory entityFactory;
    private GameLoop gameLoop;

    private Program[] antiPrograms;
    private Program[] virusPrograms;

    private long timeUnitInMs = 5000;

    public Game(String sessionId, GameConfiguration config,
                Program[] antiPrograms, Program[] virusPrograms) {
        this.sessionId = sessionId;
        this.antiPrograms = antiPrograms;
        this.virusPrograms = virusPrograms;
        configGame(config);
    }

    private void configGame(GameConfiguration config) {
        this.config = config;
        world.setMapSize(config.getM(), config.getN());
        credit.setMoney(config.getInitialAntibodyCredits());
    }

    @Override
    public void run() {
        world.connect(entityFactory);
        entityFactory.connect(world);
        entityFactory.importGen(virusPrograms, antiPrograms);

        try {
            gameLoop();
        } catch (SyntaxError e) {
            e.printStackTrace();
        }
    }

    public void setTimeUnit(long timeUnitInMs) {
        this.timeUnitInMs = timeUnitInMs;
    }

    private void gameLoop() throws SyntaxError {
        long lastTime = System.currentTimeMillis();
        while (isGameRunning) {
            long currentTime = System.currentTimeMillis();
            long deltaTime = currentTime - lastTime;

            if (deltaTime > timeUnitInMs) {
                lastTime = currentTime;

                // input stuff
                processInput();

                // spawn entity

                // evaluate
            }
        }
    }

    public void addInputEvent(InputEvent event) {
        inputEventQueue.addEvent(event);
    }

    private void processInput() {
        if (!inputEventQueue.isEmpty()) {
            InputEvent event = inputEventQueue.removeEvent();

            if (event.getAction().equals("buy")) {

            } else if (event.getAction().equals("move")) {
                Map<String, Object> data = event.getData();
                Entity toMove = world.getTarget((int) data.get("oldPosX"),
                        (int) data.get("newPosY"));

                toMove.moveByUser((int)data.get("newPosX"), (int)data.get("newPosY"),
                        config.getAntibodyMoveHpCost());
            }
        }
    }
}
