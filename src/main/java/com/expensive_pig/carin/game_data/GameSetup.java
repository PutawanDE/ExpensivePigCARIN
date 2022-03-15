package com.expensive_pig.carin.game_data;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class GameSetup {
    private String gameConfig;
    private Map<String, String> antiGeneticCodes = new HashMap<>();
    private Map<String, String> virusGeneticCodes = new HashMap<>();
}
