package com.expensive_pig.carin.game_data;

import com.expensive_pig.carin.core.EntityFactory;
import com.expensive_pig.carin.event.InputEvent;
import com.expensive_pig.carin.event.InputEventQueue;

public class GameStateModel {
    private String sessionId;
    private InputEventQueue inputEventQueue;
    private GameConfiguration gameConfiguration;
    private WorldGame worldGame;
    private Credit credit;
    private EntityFactory entityFactory;

    public GameStateModel(String sessionId, GameConfiguration config) {
        this.sessionId = sessionId;
        this.inputEventQueue = new InputEventQueue();
        this.worldGame = new WorldGame();
        this.credit = new Credit();
        this.entityFactory = new EntityFactory();
        configGame(config);
    }

    private void configGame(GameConfiguration config) {
        this.gameConfiguration = config;
        worldGame.setMapSize(config.getM(), config.getN());
        credit.setMoney(config.getInitialAntibodyCredits());
    }

    public void addInputEvent(InputEvent event) {
        inputEventQueue.addEvent(event);
    }
}
