package com.expensive_pig.carin.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class InitializeGame {

    private SimpMessagingTemplate template;

    @Autowired
    public InitializeGame(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/start")
    public void initializeGame(@Header("simpSessionId") String sessionId) throws Exception {
        createNewGame(sessionId, new GameConfiguration());
    }

    private void createNewGame(String sessionId, GameConfiguration gameConfiguration) {
        template.convertAndSend("/queue/game-" + sessionId, gameConfiguration);
    }
}
