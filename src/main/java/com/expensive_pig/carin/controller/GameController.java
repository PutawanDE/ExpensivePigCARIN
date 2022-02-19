package com.expensive_pig.carin.controller;

import com.expensive_pig.carin.core.Game;
import com.expensive_pig.carin.event.InputEvent;
import com.expensive_pig.carin.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@Controller
@EnableWebSocketMessageBroker
public class GameController {
    @Autowired
    private GameRepository gameStateRepository;

    @MessageMapping("/input")
    public void receiveInputEvent(@Header("sessionId") String sessionId, InputEvent event) {
        Game game = gameStateRepository.getBySessionId(sessionId);
        game.addInputEvent(event);
    }
}
