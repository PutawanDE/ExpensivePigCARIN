package com.expensive_pig.carin.core;

import com.expensive_pig.carin.event.InputEvent;
import com.expensive_pig.carin.game_data.GameStateModel;
import com.expensive_pig.carin.repository.GameStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@Controller
@EnableWebSocketMessageBroker
public class GameController {
    @Autowired
    private GameStateRepository gameStateRepository;

    @MessageMapping("/input")
    public void receiveInputEvent(@Header("sessionId") String sessionId, InputEvent event) {
        GameStateModel game = gameStateRepository.getBySessionId(sessionId);
        game.addInputEvent(event);
    }
}
