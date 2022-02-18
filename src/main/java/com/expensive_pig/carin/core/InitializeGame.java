package com.expensive_pig.carin.core;

import com.expensive_pig.carin.game_data.GameConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

@Controller
public class InitializeGame {

    private SimpMessagingTemplate template;

    @Autowired
    public InitializeGame(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/start")
    public void initializeGame(@Header("simpSessionId") String sessionId, GameConfiguration config) {
        createNewGame(sessionId, new GameConfiguration());
    }

    @Async
    private void createNewGame(String sessionId, GameConfiguration gameConfiguration) {
        template.convertAndSend("/queue/game-" + sessionId, gameConfiguration);
    }
}
