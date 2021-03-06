package com.expensive_pig.carin.controller;

import com.expensive_pig.carin.core.Game;
import com.expensive_pig.carin.core.InitGame;
import com.expensive_pig.carin.event.RestartGameEvent;
import com.expensive_pig.carin.game_data.GameSetup;
import com.expensive_pig.carin.game_data.GameStartResp;
import com.expensive_pig.carin.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller
@Slf4j
public class ServerController {

    @Autowired
    private TaskExecutor taskExecutor;

    private SimpMessagingTemplate template;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    public ServerController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/start")
    public void receiveStartGame(@Header("simpSessionId") String sessionId, @Payload GameSetup setup) {
        log.info("Request to start a game by sessionID: " + sessionId);
        GameStartResp resp;

        if (!gameRepository.containGame(sessionId)) {
            startNewGame(sessionId, setup);
        } else {
            resp = new GameStartResp("FAIL", "Game with this id already exist.", null);

            log.error("Fail to start a game. sessionID: " + sessionId + " msg: " + resp.getMsg());
            template.convertAndSend("/queue/start-" + sessionId, resp);
        }
    }

    @Async
    private void startNewGame(String sessionId, GameSetup setup) {
        GameStartResp resp;
        Game game;

        try {
            game = InitGame.createNewGame(sessionId, setup);
            gameRepository.addNewGame(game);

            resp = new GameStartResp("SUCCESS", null, game.getConfig());
            template.convertAndSend("/queue/start-" + sessionId, resp);

            log.info("Successfully start a game. sessionID: " + sessionId);
            taskExecutor.execute(game);
        } catch (RuntimeException e) {
            resp = new GameStartResp("FAIL", e.getMessage(), null);

            log.error("Fail to start a game. sessionID: " + sessionId + " msg: " + resp.getMsg());
            template.convertAndSend("/queue/start-" + sessionId, resp);
        }
    }

    @MessageMapping("/restart")
    public void receiveRestartGame(@Header("simpSessionId") String sessionId) {
        log.info("Request to restart a game by sessionID: " + sessionId);
        if (gameRepository.containGame(sessionId)) {
            Game oldGame = gameRepository.getBySessionId(sessionId);
            oldGame.end();
            Game newGame = new Game(oldGame.getSessionId(), oldGame.getConfig(),
                    oldGame.getAntiPrograms(), oldGame.getVirusPrograms());
            gameRepository.remove(sessionId);
            gameRepository.addNewGame(newGame);
            taskExecutor.execute(newGame);

            log.info("Successfully restart a game. sessionID: " + sessionId);
            RestartGameEvent event = new RestartGameEvent("SUCCESS", "Successfully restart a game");
            template.convertAndSend("/queue/game-" + sessionId, event);
        } else {
            RestartGameEvent event = new RestartGameEvent("FAIL", "Session ID doesn't exist.");
            template.convertAndSend("/queue/game-" + sessionId, event);
        }
    }

    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) {
        String id = event.getSessionId();
        if (gameRepository.containGame(id)) {
            gameRepository.remove(id).end();
        }

        log.info("Client with id " + event.getSessionId() + " disconnected", event.getUser());
    }
}
