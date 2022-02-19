package com.expensive_pig.carin.game_data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude()
@Getter
@AllArgsConstructor
public class GameStartResp {
    private String status;
    private String msg;
    private GameConfiguration config;
}
