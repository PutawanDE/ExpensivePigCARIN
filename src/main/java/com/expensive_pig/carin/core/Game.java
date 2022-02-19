package com.expensive_pig.carin.core;

import com.expensive_pig.carin.entity.Entity;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.evaluator.SyntaxError;
import com.expensive_pig.carin.event.InputEvent;
import com.expensive_pig.carin.event.InputEventQueue;
import com.expensive_pig.carin.game_data.GameConfiguration;

import java.util.Map;

public class Game implements Runnable {
    private String sessionId;
    private volatile boolean isGameRunning = true;

    private final GameConfiguration config;
    private InputEventQueue inputEventQueue;
    private WorldGame world;
    private Credit credit;
    private EntityFactory entityFactory;

    private boolean isPause = false;

    private final Program[] antiPrograms;
    private final Program[] virusPrograms;

    private long timeUnitInMs = 5000;

    public Game(String sessionId, GameConfiguration config,
                Program[] antiPrograms, Program[] virusPrograms) {
        this.sessionId = sessionId;
        this.antiPrograms = antiPrograms;
        this.virusPrograms = virusPrograms;
        this.config = config;
    }

    public void setTimeUnit(long timeUnitInMs) {
        this.timeUnitInMs = timeUnitInMs;
    }

    public void pauseResume() {
        isPause = !isPause;
    }

    @Override
    public void run() {
        entityFactory = new EntityFactory(virusPrograms, antiPrograms);
        world = new WorldGame(config.getM(), config.getN());
        entityFactory.setWorld(world);
        world.setEntityFactory(entityFactory);

        inputEventQueue = new InputEventQueue();
        credit = new Credit();

        try {
            gameLoop();
        } catch (SyntaxError e) {
            e.printStackTrace();
        }
    }

    private void gameLoop() throws SyntaxError {
        long lastGameLogicTime = System.currentTimeMillis();
        while (isGameRunning) {
            long currentTime = System.currentTimeMillis();
            long gameLogicDeltaTime = currentTime - lastGameLogicTime;

            if (gameLogicDeltaTime >= timeUnitInMs) {
                lastGameLogicTime = currentTime;

                entityFactory.spawnVirus(config.getVirusSpawnRate());
                evaluateEntities();
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
