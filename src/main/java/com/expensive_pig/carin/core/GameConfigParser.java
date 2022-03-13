package com.expensive_pig.carin.core;

import com.expensive_pig.carin.game_data.GameConfiguration;

import java.io.FileReader;
import java.util.Scanner;

public class GameConfigParser {
    public static GameConfiguration parseConfigFromText(String textConfig, StringBuilder errorMsg) {
        try (Scanner s = new Scanner(textConfig)) {
            return parseConfigUsingScanner(s);
        } catch (Exception e) {
            errorMsg.append("Game Config error: ").append(e).append("\n");
            e.printStackTrace();
        }

        return null;
    }

    public static GameConfiguration parseConfigFromFile(String filename, StringBuilder errorMsg) {
        try (FileReader fr = new FileReader(filename);
             Scanner s = new Scanner(fr)) {
            return parseConfigUsingScanner(s);
        } catch (Exception e) {
            errorMsg.append("Game Config error: ").append(e).append("\n");
            e.printStackTrace();
        }

        return null;
    }

    private static GameConfiguration parseConfigUsingScanner(Scanner s) {
        return GameConfiguration.builder()
                .m(s.nextInt())
                .n(s.nextInt())
                .virusSpawnRate(s.nextFloat())
                .initialAntibodyCredits(s.nextInt())
                .antibodyPlacementCost(s.nextInt())
                .initialVirusHp(s.nextInt())
                .initialAntibodyHp(s.nextInt())
                .virusAttackDamage(s.nextInt())
                .virusAttackGain(s.nextInt())
                .antibodyAttackDamage(s.nextInt())
                .antibodyKillHpGain(s.nextInt())
                .antibodyMoveHpCost(s.nextInt())
                .antibodyKillCreditGain(s.nextInt())
                .build();
    }
}
