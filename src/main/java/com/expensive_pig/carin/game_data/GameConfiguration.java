package com.expensive_pig.carin.game_data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameConfiguration {
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

    /**
     * Override the builder() method to return our custom build
     * instead of the Lombok generated builder class.
     *
     * @return GameConfigurationBuilder by our custom builder
     */
    public static GameConfigurationBuilder builder() {
        return new CustomBuilder();
    }

    private static class CustomBuilder extends GameConfigurationBuilder {
        /**
         * Adding validations as part of build() method.
         *
         * @return GameConfiguration
         */
        public GameConfiguration build() {
            if (super.virusSpawnRate < 0 || super.virusSpawnRate > 1) {
                throw new RuntimeException("Invalid Virus Spawn Rate, " +
                        "must be between 0, 1.");
            }

            if (super.initialAntibodyCredits <= 0) {
                throw new RuntimeException("Invalid Antibody Credit, must be a " +
                        "positive integer.");
            }

            if (super.antibodyPlacementCost > super.initialAntibodyCredits) {
                throw new RuntimeException("Invalid Antibody Placement Cost, must be <=" +
                        "Initial Antibody Credits");
            }

            if (super.initialVirusHealth <= 0 || super.initialAntibodyHealth <= 0) {
                throw new RuntimeException("HP must be a positive integer.");
            }

            if (super.virusAttackDamage <= 0 || super.virusAttackGain <= 0) {
                throw new RuntimeException("Virus Attack Damage and Attack Gain " +
                        "must be a positive integer.");
            }

            if (super.antibodyAttackDamage <= 0 || super.antibodyKillGain <= 0) {
                throw new RuntimeException("Antibody's Attack Damage and Kill " +
                        "Gain must be a positive integer.");
            }

            if (super.antibodyMoveHpCost < 0 ||
                    super.antibodyMoveHpCost > super.initialAntibodyCredits) {
                throw new RuntimeException("Antibody Move Cost must be a positive" +
                        " integer and must not be more than the Antibody " +
                        "initial credits.");
            }

            return super.build();
        }
    }
}
