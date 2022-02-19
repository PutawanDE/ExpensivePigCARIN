package com.expensive_pig.carin.controller;

import com.expensive_pig.carin.core.Game;
import com.expensive_pig.carin.core.InitGame;
import com.expensive_pig.carin.game_data.GameSetup;
import com.expensive_pig.carin.game_data.GameStartResp;
import com.expensive_pig.carin.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

@Controller
public class StartGameController {

    @Autowired
    private TaskExecutor taskExecutor;

    private SimpMessagingTemplate template;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    public StartGameController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/start")
    public void receiveStartGame(@Header("simpSessionId") String sessionId, GameSetup setup) {
        startNewGame(sessionId, setup);
    }

    @Async
    private void startNewGame(String sessionId, GameSetup setup) {
        GameStartResp resp;
        Game game;

        try {
            game = InitGame.createNewGame(sessionId, setup);
            gameRepository.addNewGame(game);

            resp = new GameStartResp("SUCCESS", null, game.getConfig());
            template.convertAndSend("/queue/game-" + sessionId, resp);

            taskExecutor.execute(game);
        } catch (RuntimeException e) {
            resp = new GameStartResp("FAIL", e.getMessage(), null);
            System.out.println(resp);
            template.convertAndSend("/queue/game-" + sessionId, resp);
        }
    }
}
