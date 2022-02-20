package com.expensive_pig.carin.controller;

import com.expensive_pig.carin.core.Game;
import com.expensive_pig.carin.event.BuyEvent;
import com.expensive_pig.carin.event.InputMoveEvent;
import com.expensive_pig.carin.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@Controller
@EnableWebSocketMessageBroker
public class GameController {
    @Autowired
    private GameRepository gameStateRepository;

    @MessageMapping("/move/{id}")
    public void receiveMoveEvent(@DestinationVariable String id,
                                 InputMoveEvent event) {
        Game game = gameStateRepository.getBySessionId(id);
        game.addInputEvent(event);
    }

    @MessageMapping("/buy/{id}")
    public void receiveBuyEvent(@DestinationVariable String id,
                                BuyEvent event) {
        Game game = gameStateRepository.getBySessionId(id);
        game.addInputEvent(event);
    }
}
