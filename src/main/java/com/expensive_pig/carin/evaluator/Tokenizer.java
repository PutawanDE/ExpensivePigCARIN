package com.expensive_pig.carin.evaluator;

import java.util.LinkedList;

public class Tokenizer  {

    /**
     * E → E + T | E - T | T
     * T → T * F | T / F | T % F | F
     * F → n | ( E )
     */


    private String stream;
    private LinkedList<String> tokens;

    public Tokenizer() {
    }


    public void cutter(String stream) throws SyntaxError {
        this.stream = stream;
        tokens = new LinkedList<>();
        String O_temp = stream.replaceAll("([\\s]++)|(?<=[+-\\-*/%()])|(?=[+\\-*/%()])", " ");
        O_temp = O_temp.replaceAll("( )+", " ");
        String[] separated = O_temp.split(" ");
        StringBuilder logic = new StringBuilder();
            for (String s : separated) {
                    tokens.add(s);
            }

        System.out.println(tokens);
    }


    public String peek() throws SyntaxError {
        if (!tokens.isEmpty()) {
            return tokens.getFirst();
        } else throw new SyntaxError("Invalid Line: " + stream);
    }


    public String consume() {
        String temp = tokens.getFirst();
        tokens.removeFirst();
        return temp;
    }

    public boolean peek_check(String now) throws SyntaxError {
        if (tokens.isEmpty()) return false;
        return peek().equals(now);
    }

    public void consume_check(String s) throws SyntaxError {
        if (peek_check(s)){
            consume();
        }

        else throw new SyntaxError("Invalid Line: " + stream);
    }

    public boolean empty(){
        return tokens.isEmpty();
    }

}
