package com.expensive_pig.carin.evaluator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GeneticCodeEvaluatorTest {
    private static ArrayList<Path> correctGrammarTests = new ArrayList<>();
    private static ArrayList<Path> expectedCorrectGrammarTests = new ArrayList<>();

    private static ArrayList<Path> incorrectGrammarTests = new ArrayList<>();

    private static ArrayList<Path> manyTimesEvalTests = new ArrayList<>();
    private static ArrayList<Path> expectedManyTimesEvalTests = new ArrayList<>();

    @BeforeAll
    static void scanForFilenames() {
        try {
            Files.list(Paths.get("src/test/java/com/expensive_pig/carin/evaluator/" +
                            "input/correct_grammar"))
                    .forEach(correctGrammarTests::add);
            Files.list(Paths.get("src/test/java/com/expensive_pig/carin/evaluator/" +
                            "expected/correct_grammar"))
                    .forEach(expectedCorrectGrammarTests::add);

            Files.list(Paths.get("src/test/java/com/expensive_pig/carin/evaluator/" +
                            "input/incorrect_grammar"))
                    .forEach(incorrectGrammarTests::add);

            Files.list(Paths.get("src/test/java/com/expensive_pig/carin/evaluator/" +
                            "input/many_times_eval"))
                    .forEach(manyTimesEvalTests::add);
            Files.list(Paths.get("src/test/java/com/expensive_pig/carin/evaluator/" +
                            "expected/many_times_eval"))
                    .forEach(expectedManyTimesEvalTests::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void TestParseAndEvaluate_CorrectGrammar_ShouldPass() {
        for (int i = 0; i < correctGrammarTests.size(); i++) {
            ReadGeneticCode readGeneticCode = new ReadGeneticCode();
            Path input = correctGrammarTests.get(i);
            Path expected = expectedCorrectGrammarTests.get(i);
            try {
                assertEquals(readFile(expected),
                        readGeneticCode.geneticEvaluateTest(
                                ReadGeneticCode.readFile(String.valueOf(input))),
                        "Expected output: " + expected + "\n" +
                                "Input: " + input);
            } catch (SyntaxError e) {
                System.out.println("FAILED------------------------");
                System.out.println("Expected output: " + expected + "\n" +
                        "Input: " + input);
                e.printStackTrace();
                fail();
            }
        }
    }

    @Test
    void TestParseAndEvaluate_IncorrectGrammar_ShouldThrowSyntaxErrorException() {
        for (int i = 0; i < incorrectGrammarTests.size(); i++) {
            ReadGeneticCode readGeneticCode = new ReadGeneticCode();
            Path input = incorrectGrammarTests.get(i);
            try {
                assertThrows(SyntaxError.class, () ->
                                readGeneticCode.geneticEvaluateTest(
                                        ReadGeneticCode.readFile(String.valueOf(input))),
                        "Expected output: throw SyntaxError exception \n" +
                                "Input: " + input);
            } catch (AssertionError e) {
                System.out.println("FAILED-----------------------------------------");
                System.out.println("Expected output: throw SyntaxError exception\n" +
                        "Input: " + input);
                e.printStackTrace();
                fail();
            }
        }
    }

    @Test
    void TestParseAndEvaluate_ManyTimesEvaluation_ShouldPass() {
        for (int i = 0; i < manyTimesEvalTests.size(); i++) {
            ReadGeneticCode readGeneticCode = new ReadGeneticCode();
            Path input = manyTimesEvalTests.get(i);
            Path expected = expectedManyTimesEvalTests.get(i);
            try {
                StringBuilder output = new StringBuilder();
                for (int j = 0; j < 5; j++) {
                    output.append(readGeneticCode.geneticEvaluateTest(
                            ReadGeneticCode.readFile(String.valueOf(input))));
                }

                assertEquals(readFile(expected), output.toString(),
                        "Expected output: " + expected + "\n" +
                                "Input: " + input);
            } catch (SyntaxError e) {
                System.out.println("FAILED------------------------");
                System.out.println("Expected output: " + expected + "\n" +
                        "Input: " + input);
                e.printStackTrace();
                fail();
            }
        }
    }

    private static String readFile(Path filePath) {
        StringBuilder data = new StringBuilder();
        Charset charset = StandardCharsets.UTF_8;

        try (BufferedReader reader = Files.newBufferedReader(filePath, charset)) {
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
