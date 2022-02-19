package com.expensive_pig.carin.game_data;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class GameConfiguration {
    @NonNull
    private int m;

    @NonNull
    private int n;

    @NonNull
    private float virusSpawnRate;

    @NonNull
    private int initialAntibodyCredits;

    @NonNull
    private int antibodyPlacementCost;

    @NonNull
    private int initialVirusHp;

    @NonNull
    private int initialAntibodyHp;

    @NonNull
    private int virusAttackDamage;

    @NonNull
    private int virusAttackGain;

    @NonNull
    private int antibodyAttackDamage;

    @NonNull
    private int antibodyKillHpGain;

    @NonNull
    private int antibodyMoveHpCost;

    @NonNull
    private int antibodyKillCreditGain;

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

            if (super.initialVirusHp <= 0 || super.initialAntibodyHp <= 0) {
                throw new RuntimeException("HP must be a positive integer.");
            }

            if (super.virusAttackDamage <= 0 || super.virusAttackGain <= 0) {
                throw new RuntimeException("Virus Attack Damage and Attack Gain " +
                        "must be a positive integer.");
            }

            if (super.antibodyAttackDamage <= 0 || super.antibodyKillHpGain <= 0) {
                throw new RuntimeException("Antibody's Attack Damage and Kill " +
                        "Gain must be a positive integer.");
            }

            if (super.antibodyMoveHpCost < 0 ||
                    super.antibodyMoveHpCost > super.initialAntibodyHp) {
                throw new RuntimeException("Antibody Move Cost must be a positive" +
                        " integer and must not be more than the Antibody " +
                        "initial HP.");
            }

            if (super.antibodyKillCreditGain < 0) {
                throw new RuntimeException("Antibody Kill Credit Gain must be " +
                        "a positive integer.");
            }

            return super.build();
        }
    }
}
