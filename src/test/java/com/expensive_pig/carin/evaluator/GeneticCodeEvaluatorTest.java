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
            "src/test/java/com/expensive_pig/carin/evaluator/input/1_multipleAssignTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/input/2_test.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/input/3_infLoopTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/input/4_infLoopNestedTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/input/5_sampleTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/input/6_test.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/input/7_test.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/input/8_test.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/input/9_test.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/input/10_WhileTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/input/11_WhileTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/input/12_BlockStatementTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/input/13_BlockStatementTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/input/14_BlockStatementTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/input/15_OrderTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/input/16_OrderTest.txt"

    };

    private String[] expectedFileNames = {
            "src/test/java/com/expensive_pig/carin/evaluator/expected/1_expectedTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/expected/2_expectedTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/expected/3_expectedTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/expected/4_expectedTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/expected/5_expectedTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/expected/6_expectedSampleTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/expected/7_expectedSampleTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/expected/8_expectedSampleTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/expected/9_expectedSampleTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/expected/10_expectedSampleTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/expected/11_expectedSampleTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/expected/12_expectedSampleTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/expected/13_expectedSampleTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/expected/14_expectedSampleTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/expected/15_expectedSampleTest.txt",
            "src/test/java/com/expensive_pig/carin/evaluator/expected/16_expectedSampleTest.txt"



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
