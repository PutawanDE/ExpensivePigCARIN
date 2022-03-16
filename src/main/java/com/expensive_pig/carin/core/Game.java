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
    private boolean isGameEnd = false;

    @Getter
    private final Program[] antiPrograms;
    @Getter
    private final Program[] virusPrograms;

    private EventQueue<InputEvent> inputEventQueue;

    private float speed = 1.0f;
    private final long maxTimeUnitInMs = 5000;

    private int timeUnitPlayed = 0;

    public Game(String sessionId, GameConfiguration config,
                Program[] antiPrograms, Program[] virusPrograms) {
        this.sessionId = sessionId;
        this.antiPrograms = antiPrograms;
        this.virusPrograms = virusPrograms;
        this.config = config;
        gameController = SpringContext.getBean(GameController.class);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
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

    public void end() {
        isGameRunning = false;
        log.info("Game with id: " + sessionId + " ends.");
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

            if (gameDeltaTime * speed >= maxTimeUnitInMs * 1000000) {
                timeUnitPlayed++;
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
                if (toMove != null) {
                    toMove.moveByUser(toPosX, toPosY, config.getAntibodyMoveHpCost());
                }
            }
        }
    }

    private void update() {
        if (isPlayerPlaceFirstAnti && !isGameEnd) {

            Iterator<Entity> itr = entityManager.entities.listIterator();
            while (itr.hasNext()) {
                Entity e = itr.next();
                try {
                    e.evaluate();
                } catch (SyntaxError ex) {
                    // get rid of e
                    e.die();
                }
                if (!e.isAlive()) itr.remove();
            }

            if (entityManager.isFirstVirusSpawn()) {
                gameStatus();
            }

            entityManager.spawnInfected();
            entityManager.spawnVirus();

            gameController.sendOutputEvent(sessionId, new RemainEvent(entityManager.getNumberAnti(),
                    entityManager.getNumberVirus()));
        }
    }

    private void gameStatus() {
        if (entityManager.getNumberAnti() <= 0) {
//            gameController.sendOutputEvent(sessionId, new GameEndEvent("LOST"), timeUnitPlayed, );
            end();
        }
        if (entityManager.getNumberVirus() <= 0) {
//            gameController.sendOutputEvent(sessionId, new GameEndEvent("WIN"), timeUnitPlayed, );
            end();
        }
    }
}
