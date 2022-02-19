package com.expensive_pig.carin.core;

import com.expensive_pig.carin.core.EntityFactory;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.event.InputEvent;
import com.expensive_pig.carin.event.InputEventQueue;
import com.expensive_pig.carin.game_data.Credit;
import com.expensive_pig.carin.game_data.GameConfiguration;
import com.expensive_pig.carin.game_data.GameSetup;
import com.expensive_pig.carin.game_data.WorldGame;
import lombok.Getter;

@Getter
public class Game {
    private String sessionId;
    private GameConfiguration gameConfiguration;
    private InputEventQueue inputEventQueue;
    private WorldGame worldGame;
    private Credit credit;
    private EntityFactory entityFactory;

    private Program[] antiPrograms;
    private Program[] virusPrograms;

    public Game(String sessionId, GameConfiguration config,
                Program[] antiPrograms, Program[] virusPrograms) {
        this.sessionId = sessionId;
        this.antiPrograms = antiPrograms;
        this.virusPrograms = virusPrograms;
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
