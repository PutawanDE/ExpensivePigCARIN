package com.expensive_pig.carin.core;

import com.expensive_pig.carin.entity.Entity;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.evaluator.SyntaxError;
import com.expensive_pig.carin.event.*;
import com.expensive_pig.carin.game_data.GameConfiguration;
import lombok.Getter;

public class Game implements Runnable {
    @Getter
    private String sessionId;
    private volatile boolean isGameRunning = true;

    @Getter
    private final GameConfiguration config;

    private WorldGame world;
    private CreditSystem creditSystem;
    private EntityManager entityManager;

    private boolean isPause = false;

    private final Program[] antiPrograms;
    private final Program[] virusPrograms;

    private EventQueue<InputEvent> inputEventQueue;

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
        world = new WorldGame(config.getM(), config.getN());
        entityManager = new EntityManager(virusPrograms, antiPrograms, config, world, sessionId);

        creditSystem = new CreditSystem(sessionId, config.getInitialAntibodyCredits(),
                config.getAntibodyPlacementCost(), entityManager);

        inputEventQueue = new EventQueue<>();

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

            //TODO: process input 30hz

            if (deltaTime * timeScale >= maxTimeUnitInMs) {
                lastTime = currentTime;

                entityManager.spawnVirus();
                evaluateEntities();

                // update entity list
                entityManager.clearDeadAndSpawnInfected();
            }
        }
    }

    private void evaluateEntities() {
        for (Entity e : entityManager.entities) {
            processInput();
            try {
                e.evaluate();
            } catch (SyntaxError ex) {
                // get rid of e
                e.dead();
            }
        }
    }

    public void addInputEvent(InputEvent event) {
        inputEventQueue.addEvent(event);
    }

    private void processInput() {
        if (!inputEventQueue.isEmpty()) {
            InputEvent event = inputEventQueue.removeEvent();

            if (event instanceof BuyEvent buyEvent) {
                creditSystem.buyAndPlace(buyEvent.getPosX(), buyEvent.getPosY(), buyEvent.getKind());
            } else if (event instanceof InputMoveEvent inputMoveEvent) {
                Entity toMove = world.getTarget(inputMoveEvent.getPosX(), inputMoveEvent.getPosY());
                toMove.moveByUser(inputMoveEvent.getToPosX(), inputMoveEvent.getToPosY(), config.getAntibodyMoveHpCost());
            }
        }
    }
}
