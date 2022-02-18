package com.expensive_pig.carin.game_data;

public class GameConfiguration {
    private final String defaultConfigFilePath = "";

    private int m;
    private int n;
    private float virusSpawnRate;
    private int initialAntibodyCredits;
    private int antibodyPlacementCost;
    private int initialVirusHealth;
    private int initialAntibodyHealth;
    private int virusAttackDamage;
    private int virusAttackGain;
    private int antibodyAttackDamage;
    private int antibodyKillGain;
    private int antibodyMoveHpCost;

    public GameConfiguration() {
        readConfigFromFile(defaultConfigFilePath);
    }

    public GameConfiguration(String config) {
        readConfigFromString(config);
    }


    private void readConfigFromFile(String filename) {
    }

    private void readConfigFromString(String config) {
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public float getVirusSpawnRate() {
        return virusSpawnRate;
    }

    public int getInitialAntibodyCredits() {
        return initialAntibodyCredits;
    }

    public int getAntibodyPlacementCost() {
        return antibodyPlacementCost;
    }

    public int getInitialVirusHealth() {
        return initialVirusHealth;
    }

    public int getInitialAntibodyHealth() {
        return initialAntibodyHealth;
    }

    public int getVirusAttackDamage() {
        return virusAttackDamage;
    }

    public int getVirusAttackGain() {
        return virusAttackGain;
    }

    public int getAntibodyAttackDamage() {
        return antibodyAttackDamage;
    }

    public int getAntibodyKillGain() {
        return antibodyKillGain;
    }

    public int getAntibodyMoveHpCost() {
        return antibodyMoveHpCost;
    }
}
