package com.expensive_pig.carin.repository;

import com.expensive_pig.carin.core.Game;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GameRepository {
    private Map<String, Game> sessionIdGameMap = new ConcurrentHashMap<>();

    public void addNewGame(Game game) {
        sessionIdGameMap.put(game.getSessionId(), game);
    }

    public Game getBySessionId(String sessionId) {
        return sessionIdGameMap.get(sessionId);
    }

    public boolean containGame(String sessionId) {
        return sessionIdGameMap.containsKey(sessionId);
    }

    public Game remove(String sessionId) {
        return sessionIdGameMap.remove(sessionId);
    }
}
