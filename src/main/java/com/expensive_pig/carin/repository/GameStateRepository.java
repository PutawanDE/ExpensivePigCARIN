package com.expensive_pig.carin.repository;

import com.expensive_pig.carin.game_data.GameStateModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameStateRepository {
    private Map<String, GameStateModel> gameStateMap = new ConcurrentHashMap<>();

    public GameStateModel getBySessionId(String sessionId) {
        return gameStateMap.get(sessionId);
    }
}
