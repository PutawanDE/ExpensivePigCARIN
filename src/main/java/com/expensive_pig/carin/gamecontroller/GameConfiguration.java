package com.expensive_pig.carin.gamecontroller;

public class GameConfiguration {
    private String config = "default";

    public GameConfiguration() {
    }

    public GameConfiguration(String config) {
        this.config = config;
    }

    public String getContent() {
        return config;
    }
}
