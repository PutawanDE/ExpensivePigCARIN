package com.expensive_pig.carin.controller;

import com.expensive_pig.carin.core.Game;
import com.expensive_pig.carin.core.ReadGameSetupFiles;
import com.expensive_pig.carin.event.BuyEvent;
import com.expensive_pig.carin.event.InputMoveEvent;
import com.expensive_pig.carin.event.OutputEvent;
import com.expensive_pig.carin.event.SetSpeedEvent;
import com.expensive_pig.carin.game_data.GameSetup;
import com.expensive_pig.carin.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import java.io.IOException;

@Controller
@RestController
@EnableWebSocketMessageBroker
public class GameController {
    @Autowired
    private GameRepository gameStateRepository;

    private SimpMessagingTemplate template;

    @Autowired
    public GameController(SimpMessagingTemplate template) {
        this.template = template;
    }

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

    public void sendOutputEvent(String sessionId, OutputEvent event) {
        template.convertAndSend("/queue/game-" + sessionId, event);
    }

    @MessageMapping("/speed/{id}")
    public void setSpeed(@DestinationVariable String id, float speed) {
        Game game = gameStateRepository.getBySessionId(id);
        game.setSpeed(speed);
    }

    @GetMapping("/setup")
    public GameSetup getDefaultSetup() {
        try {
            return ReadGameSetupFiles.makeDefaultGameSetupFromFiles();
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Default Game Setup IOException", e);
        }
    }
}
