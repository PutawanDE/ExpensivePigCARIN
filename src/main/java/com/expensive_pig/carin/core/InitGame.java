package com.expensive_pig.carin.core;


import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.evaluator.ReadGeneticCode;
import com.expensive_pig.carin.game_data.GameConfiguration;
import com.expensive_pig.carin.game_data.GameSetup;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class InitGame {
    private static final String GAME_CONFIG_FILENAMES = "src/main/java/com/expensive_pig/carin/game_config/" +
            "DefaultGameConfig.txt";

    private static final String[] ANTI_PROGRAM_FILENAMES = {
            "src/main/java/com/expensive_pig/carin/dev_genetic/Anti1.txt",
            "src/main/java/com/expensive_pig/carin/dev_genetic/Anti2.txt",
            "src/main/java/com/expensive_pig/carin/dev_genetic/Anti3.txt"
    };

    private static final String[] VIRUS_PROGRAM_FILENAMES = {
            "src/main/java/com/expensive_pig/carin/dev_genetic/Virus1.txt",
            "src/main/java/com/expensive_pig/carin/dev_genetic/Virus1.txt",
            "src/main/java/com/expensive_pig/carin/dev_genetic/Virus1.txt"
    };

    private static final String[] antibodyType = new String[]{"A1", "A2", "A3"};
    private static final String[] virusType = new String[]{"V1", "V2", "V3"};

    private static final int NUM_ANTI_KINDS = antibodyType.length;
    private static final int NUM_VIRUS_KINDS = virusType.length;

    public static Game createNewGame(String sessionId, GameSetup setup) {
        StringBuilder errorMsg = new StringBuilder();
        Map<String, String> uploadedAntiCodes = setup.getAntiGeneticCodes();
        Map<String, String> uploadedVirusCodes = setup.getVirusGeneticCodes();

        Program[] antiPrograms;
        Program[] virusPrograms;

        GameConfiguration config;

        if (setup.getGameConfig().equals("")) {
            config = GameConfigParser.parseConfigFromFile(GAME_CONFIG_FILENAMES, errorMsg);
        } else {
            config = GameConfigParser.parseConfigFromText(setup.getGameConfig(), errorMsg);
        }
        antiPrograms = createAntiPrograms(uploadedAntiCodes, errorMsg);
        virusPrograms = createVirusPrograms(uploadedVirusCodes, errorMsg);

        if (!errorMsg.isEmpty()) {
            throw new RuntimeException(errorMsg.toString());
        }

        return new Game(sessionId, config, antiPrograms, virusPrograms);
    }

    private static Program[] createAntiPrograms(Map<String, String> uploadedGeneticCodes, StringBuilder errorMsg) {
        ReadGeneticCode programReader = new ReadGeneticCode();
        Program[] antiPrograms = new Program[NUM_ANTI_KINDS];

        for (int i = 0; i < antibodyType.length; i++) {
            try {
                if (uploadedGeneticCodes.containsKey(antibodyType[i])) {
                    antiPrograms[i] = programReader.getProgrambyString(
                            uploadedGeneticCodes.get(antibodyType[i]));
                } else {
                    antiPrograms[i] = programReader.getProgrambyPath(ANTI_PROGRAM_FILENAMES[i]);
                }
            } catch (Exception e) {
                errorMsg.append("Antibody Type ").append(i + 1).append(" error: ")
                        .append(e).append("\n");
            }
        }

        return antiPrograms;
    }

    private static Program[] createVirusPrograms(Map<String, String> uploadedGeneticCodes, StringBuilder errorMsg) {
        ReadGeneticCode programReader = new ReadGeneticCode();
        Program[] virusPrograms = new Program[NUM_VIRUS_KINDS];

        for (int i = 0; i < virusType.length; i++) {
            try {
                if (uploadedGeneticCodes.containsKey(virusType[i])) {
                    virusPrograms[i] = programReader.getProgrambyString(
                            uploadedGeneticCodes.get(virusType[i]));
                } else {
                    virusPrograms[i] = programReader.getProgrambyPath(VIRUS_PROGRAM_FILENAMES[i]);
                }
            } catch (Exception e) {
                errorMsg.append("Virus Type ").append(i + 1).append(" error: ")
                        .append(e).append("\n");
            }
        }

        return virusPrograms;
    }
}
