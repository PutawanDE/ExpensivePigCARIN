package com.expensive_pig.carin.evaluator;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class GeneticCodeEvaluatorTest {
    private String[] inFileNames = {
            "src/test/java/com/expensive_pig/carin/evaluator/multipleAssignTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/test.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/infLoopTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/infLoopNested.txt"
    };

    private String[] expectedFileNames = {
            "src/test/java/com/expensive_pig/carin/evaluator/expectedMultipleAssignTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/expectedTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/empty.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/empty.txt",
    };

    @Test
    void parseAndEvaluate() {
        ReadGeneticCode readGeneticCode = new ReadGeneticCode();
        for (int i = 0; i < inFileNames.length; i++) {
            try {
                assertEquals(readFile(expectedFileNames[i]),
                        readGeneticCode.evaluate(readGeneticCode.readFile(inFileNames[i])),
                        "Expected output: " + expectedFileNames[i] + "\n" +
                                "Input: " + inFileNames[i]);
            } catch (SyntaxError e) {
                System.out.println("FAILED------------------------");
                System.out.println("Expected output: " + expectedFileNames[i] + "\n" +
                        "Input: " + inFileNames[i]);
                e.printStackTrace();
            }

        }
    }

    private String readFile(String filePath) {
        Path file = Paths.get(filePath);  // path string
        StringBuilder data = new StringBuilder();
        Charset charset = StandardCharsets.UTF_8;

        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String text;
            while ((text = reader.readLine()) != null) {
                String ignoreComment = text.split("#", text.length())[0];
                data.append(ignoreComment).append("\n");
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return data.toString();
    }
}
