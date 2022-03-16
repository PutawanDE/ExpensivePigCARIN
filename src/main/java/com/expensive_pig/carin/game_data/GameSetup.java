package com.expensive_pig.carin.game_data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class GameSetup {
    private String gameConfig;
    private Map<String, String> antiGeneticCodes;
    private Map<String, String> virusGeneticCodes;
}
