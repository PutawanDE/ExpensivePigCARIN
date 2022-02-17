package com.expensive_pig.carin;

import com.expensive_pig.carin.core.Gameloop;
import com.expensive_pig.carin.evaluator.SyntaxError;

public class Main {
    public static void main(String[] args) throws SyntaxError {
        Gameloop a = new Gameloop();
        a.setspeed(0.5);
        a.start();
    }
}
