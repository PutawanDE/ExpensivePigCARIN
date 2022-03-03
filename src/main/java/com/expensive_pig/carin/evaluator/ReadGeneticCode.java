package com.expensive_pig.carin.evaluator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ReadGeneticCode {
    Parser_Expr parser = new Parser_Expr();
    GeneticCodeEvaluator evaluator = new GeneticCodeEvaluator();

    public  Program getProgrambyPath(String path) throws SyntaxError {
        String data = readFile(path);
        return parser.parse(data);  // Program
    }
    public  Program getProgrambyString(String data) throws SyntaxError {
        return parser.parse(data); // Program
    }
    // for test genetic dont use
    public String geneticEvaluateTest(String data) throws SyntaxError {
        Map<String, Integer> variableMap = new HashMap<>();
        Program p = parser.parse(data);
        return evaluator.evaluateProgram(p, null, variableMap);
    }


    /**
     * readfile
     */
    public static String readFile(String filePath) {
        Path file = Paths.get(filePath);  // path string
        StringBuilder data = new StringBuilder();
        Charset charset = StandardCharsets.UTF_8;

        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String text;
            while ((text = reader.readLine()) != null) {
                String ignoreComment = text.split("#", text.length())[0];
                data.append(ignoreComment).append(" ");
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return data.toString();
    }
}
