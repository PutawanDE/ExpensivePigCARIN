package com.expensive_pig.carin.core;

import com.expensive_pig.carin.entity.Entity;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.evaluator.SyntaxError;
import com.expensive_pig.carin.event.InputEvent;
import com.expensive_pig.carin.event.InputEventQueue;
import com.expensive_pig.carin.game_data.GameConfiguration;
import lombok.Getter;

import java.util.Map;

public class Game implements Runnable {
    private String sessionId;
    private volatile boolean isGameRunning = true;

    @Getter
    private final GameConfiguration config;

    private InputEventQueue inputEventQueue;
    private WorldGame world;
    private CreditSystem creditSystem;
    private EntityFactory entityFactory;

    private boolean isPause = false;

    private final Program[] antiPrograms;
    private final Program[] virusPrograms;

    private float timeScale = 1.0f;
    private final int maxTimeUnitInMs = 5000;

    public Game(String sessionId, GameConfiguration config,
                Program[] antiPrograms, Program[] virusPrograms) {
        this.sessionId = sessionId;
        this.antiPrograms = antiPrograms;
        this.virusPrograms = virusPrograms;
        this.config = config;
    }

    public void setTimeScale(int timeScale) {
        this.timeScale = timeScale;
    }

    @Override
    public void run() {
        entityFactory = new EntityFactory(virusPrograms, antiPrograms, config);
        world = new WorldGame(config.getM(), config.getN());
        entityFactory.injectWorld(world);
        world.injectEntityFactory(entityFactory);

        creditSystem = new CreditSystem(config.getInitialAntibodyCredits(), config.getAntibodyPlacementCost(),
                entityFactory);

        inputEventQueue = new InputEventQueue();

        try {
            gameLoop();
        } catch (SyntaxError e) {
            e.printStackTrace();
        }
    }

    private void gameLoop() throws SyntaxError {
        long lastTime = System.currentTimeMillis();
        long deltaTime;

        while (isGameRunning) {
            long currentTime = System.currentTimeMillis();
            deltaTime = currentTime - lastTime;

            if (deltaTime * timeScale >= maxTimeUnitInMs) {
                lastTime = currentTime;

                processInput();
                entityFactory.spawnVirus(config.getVirusSpawnRate());
                evaluateEntities();

                // update entity list
                //send output
            }
        }
    }

    public void addInputEvent(InputEvent event) {
        inputEventQueue.addEvent(event);
    }

    private void processInput() {
        if (!inputEventQueue.isEmpty()) {
            InputEvent event = inputEventQueue.removeEvent();
            Map<String, Integer> data = event.getData();

            if (event.getAction().equals("buy")) {
                creditSystem.buyAndPlace(data.get("posX"), data.get("posY"), data.get("type"));
            } else if (event.getAction().equals("move")) {
                Entity toMove = world.getTarget(data.get("oldPosX"), data.get("newPosY"));

                toMove.moveByUser(data.get("newPosX"), data.get("newPosY"), config.getAntibodyMoveHpCost());
            }
        }
    }

    private void evaluateEntities() {
        for (Entity e : entityFactory.entities) {
            try {
                e.evaluate();
            } catch (SyntaxError ex) {
                // get rid of e
            }
        }
    }
}
