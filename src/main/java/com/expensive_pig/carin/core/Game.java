package com.expensive_pig.carin.core;

import com.expensive_pig.carin.SpringContext;
import com.expensive_pig.carin.controller.GameController;
import com.expensive_pig.carin.entity.Entity;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.evaluator.SyntaxError;
import com.expensive_pig.carin.event.*;
import com.expensive_pig.carin.game_data.GameConfiguration;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

@Slf4j
public class Game implements Runnable {
    @Getter
    private String sessionId;
    private boolean isGameRunning = true;

    @Getter
    private final GameConfiguration config;

    private WorldGame world;
    private CreditSystem creditSystem;
    private EntityManager entityManager;
    private GameController gameController;

    private boolean isPlayerPlaceFirstAnti = false;

    private final Program[] antiPrograms;
    private final Program[] virusPrograms;

    private EventQueue<InputEvent> inputEventQueue;

    private float timeScale = 1.0f;
    private final long maxTimeUnitInMs = 5000;

    public Game(String sessionId, GameConfiguration config,
                Program[] antiPrograms, Program[] virusPrograms) {
        this.sessionId = sessionId;
        this.antiPrograms = antiPrograms;
        this.virusPrograms = virusPrograms;
        this.config = config;
        gameController = SpringContext.getBean(GameController.class);
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

        gameLoop();
    }

    private void gameLoop() {
        long gameLastTime = System.nanoTime();
        long inputLastTime = System.nanoTime();
        long gameDeltaTime, inputDeltaTime;

        while (isGameRunning) {
            long currentTime = System.nanoTime();
            gameDeltaTime = currentTime - gameLastTime;
            inputDeltaTime = currentTime - inputLastTime;

            //process input once every 30 ms
            if (inputDeltaTime >= 30 * 1000000) {
                inputLastTime = currentTime;
                processInput();
            }

            if (gameDeltaTime * timeScale >= maxTimeUnitInMs * 1000000) {
                gameLastTime = currentTime;
                update();
            }
        }
    }


    public void addInputEvent(InputEvent event) {
        inputEventQueue.addEvent(event);
    }

    private void processInput() {
        if (!inputEventQueue.isEmpty()) {
            isPlayerPlaceFirstAnti = true;
            InputEvent event = inputEventQueue.removeEvent();

            if (event instanceof BuyEvent buyEvent) {
                int posX = buyEvent.getPosX();
                int posY = buyEvent.getPosY();
                log.info("Receive buy input at x:" + posX + " y:" + posY);

                creditSystem.buyAndPlace(posX, posY, buyEvent.getKind());
            } else if (event instanceof InputMoveEvent inputMoveEvent) {
                int posX = inputMoveEvent.getPosX();
                int posY = inputMoveEvent.getPosY();
                int toPosX = inputMoveEvent.getToPosX();
                int toPosY = inputMoveEvent.getToPosY();

                log.info("Receive move input at x:" + posX + " y:" + posY +
                        " to x:" + toPosX + " to y:" + toPosY);

                Entity toMove = world.getTarget(posX, posY);
                if(toMove != null) {
                    toMove.moveByUser(toPosX, toPosY, config.getAntibodyMoveHpCost());
                }
            }
        }
    }

    private void update() {
        if (isPlayerPlaceFirstAnti) {
            if (entityManager.getNumberAnti() <= 0) { // game over
                gameController.sendOutputEvent(sessionId, new GameOverEvent());
            } else {
                Iterator<Entity> itr = entityManager.entities.listIterator();
                while (itr.hasNext()) {
                    Entity e = itr.next();
                    try {
                        e.evaluate();
                    } catch (SyntaxError ex) {
                        // get rid of e
                        entityManager.reduceNumberAnti();
                        e.die();
                    }
                    if (!e.isAlive()) itr.remove();
                }
                entityManager.spawnInfected();
                entityManager.spawnVirus();
            }

        }

    }
}
