package com.expensive_pig.carin.core;

import com.expensive_pig.carin.game_data.GameSetup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ReadGameSetupFiles {
    private static final String config_filename = "src/main/java/com/expensive_pig/carin/game_config/" +
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

    public static GameSetup makeDefaultGameSetupFromFiles() throws IOException {
        String config = Files.readString(Paths.get(config_filename));
        Map<String, String> antiCodes = new HashMap<>();
        Map<String, String> virusCodes = new HashMap<>();

        for (int i = 0; i < ANTI_PROGRAM_FILENAMES.length; i++) {
            String code = Files.readString(Paths.get(ANTI_PROGRAM_FILENAMES[i]));
            antiCodes.put("A" + (i + 1), code);
        }

        for (int i = 0; i < VIRUS_PROGRAM_FILENAMES.length; i++) {
            String code = Files.readString(Paths.get(VIRUS_PROGRAM_FILENAMES[i]));
            virusCodes.put("V" + (i + 1), code);
        }

        return new GameSetup(config, antiCodes, virusCodes);
    }
}
