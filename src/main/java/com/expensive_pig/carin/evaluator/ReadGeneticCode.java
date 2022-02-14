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
    public static void main(String[] args) throws SyntaxError {

        ReadGeneticCode readGenetic = new ReadGeneticCode();
        String data = readGenetic.readfile();
        evaluate(data);
        System.out.println(data);



    }

    public static String evaluate(String data) throws SyntaxError {
        Parser_Expr parse = new Parser_Expr();
         parse.parse(data)  ;
        //

        return null;
    }

    /**
     * readfile
     */
    public String readfile() {
        Path file = Paths.get("src/test/java/com/expensive_pig/carin/evaluator/test.txt");  // path string
        HashMap<Integer, String> line = new HashMap<>();
        Charset charset = StandardCharsets.UTF_8;
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            int lines = 0;
            String text;

            while ((text = reader.readLine()) != null) {
                line.put(lines, text);
                lines++;
            }

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }


        StringBuilder data = new StringBuilder();
        for (Map.Entry<Integer, String> number_lines : line.entrySet()) {
            String stream = number_lines.getValue();
            data.append(stream).append(" ");
        }
        return data.toString();
    }

    }




