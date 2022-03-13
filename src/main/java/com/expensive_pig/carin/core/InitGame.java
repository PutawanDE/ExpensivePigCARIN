package com.expensive_pig.carin.core;


import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.evaluator.ReadGeneticCode;
import com.expensive_pig.carin.game_data.GameConfiguration;
import com.expensive_pig.carin.game_data.GameSetup;

public class InitGame {
    private static final int NUM_ANTI_KINDS = 3;
    private static final int NUM_VIRUS_KINDS = 3;

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

    public static Game createNewGame(String sessionId, GameSetup setup) {
        StringBuilder errorMsg = new StringBuilder();
        String[] uploadedAntiCodes = setup.getAntiGeneticCodes();
        String[] uploadedVirusCodes = setup.getVirusGeneticCodes();

        Program[] antiPrograms = new Program[NUM_ANTI_KINDS];
        Program[] virusPrograms = new Program[NUM_ANTI_KINDS];

        GameConfiguration config;

        if (setup.getGameConfig().equals("")) {
            config = GameConfigParser.parseConfigFromFile(GAME_CONFIG_FILENAMES, errorMsg);
        } else {
            config = GameConfigParser.parseConfigFromText(setup.getGameConfig(), errorMsg);
        }

        if (uploadedAntiCodes.length == NUM_ANTI_KINDS) {
            antiPrograms = createAntiPrograms(uploadedAntiCodes, errorMsg);
        } else {
            errorMsg.append("Provided genetic codes do not match with number of Antibody Kinds\n");
        }

        if (uploadedVirusCodes.length == NUM_VIRUS_KINDS) {
            virusPrograms = createVirusPrograms(uploadedVirusCodes, errorMsg);
        } else {
            errorMsg.append("Provided genetic codes do not match with number of Virus Kinds\n");
        }

        if (!errorMsg.isEmpty()) {
            throw new RuntimeException(errorMsg.toString());
        }

        return new Game(sessionId, config, antiPrograms, virusPrograms);
    }

    private static Program[] createAntiPrograms(String[] uploadedGeneticCodes, StringBuilder errorMsg) {
        ReadGeneticCode programReader = new ReadGeneticCode();
        Program[] antiPrograms = new Program[NUM_ANTI_KINDS];

        for (int i = 0; i < NUM_ANTI_KINDS; i++) {
            try {
                if (uploadedGeneticCodes[i].equals("")) {
                    antiPrograms[i] = programReader.getProgrambyPath(ANTI_PROGRAM_FILENAMES[i]);
                } else {
                    antiPrograms[i] = programReader.getProgrambyString
                            (uploadedGeneticCodes[i]);
                }
            } catch (Exception e) {
                errorMsg.append("Antibody Type ").append(i).append(" error: ")
                        .append(e).append("\n");
            }
        }
        return antiPrograms;
    }

    private static Program[] createVirusPrograms(String[] uploadedGeneticCodes, StringBuilder errorMsg) {
        ReadGeneticCode programReader = new ReadGeneticCode();
        Program[] virusPrograms = new Program[NUM_VIRUS_KINDS];

        for (int i = 0; i < NUM_VIRUS_KINDS; i++) {
            try {
                if (uploadedGeneticCodes[i].equals("")) {
                    virusPrograms[i] = programReader.getProgrambyPath(VIRUS_PROGRAM_FILENAMES[i]);
                } else {
                    virusPrograms[i] = programReader.getProgrambyString
                            (uploadedGeneticCodes[i]);
                }
            } catch (Exception e) {
                errorMsg.append("Virus Type ").append(i).append(" error: ")
                        .append(e).append("\n");
            }
        }

        return virusPrograms;
    }
}
