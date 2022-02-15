package com.expensive_pig.carin.evaluator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadGeneticCode {
    public static void main(String[] args) throws SyntaxError {
        ReadGeneticCode readGenetic = new ReadGeneticCode();
        String data = readGenetic.readFile("src/main/java/com/expensive_pig/carin/dev_genetic/test.txt");
        readGenetic.evaluate(data);
        System.out.println(data);
    }

    public String evaluate(String data) throws SyntaxError {
        Parser_Expr parser = new Parser_Expr();
        Program p = parser.parse(data);
        GeneticCodeEvaluator evaluator = new GeneticCodeEvaluator();
        return evaluator.evaluateProgram(p, null);
    }

    /**
     * readfile
     */
    public String readFile(String filePath) {
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
