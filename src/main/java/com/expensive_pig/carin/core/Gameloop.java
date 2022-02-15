package com.expensive_pig.carin.core;

import com.expensive_pig.carin.entity.Entity;

import java.util.LinkedList;

public class Gameloop {
    boolean gameIsruning = true;
    boolean setGame = true;
    boolean newPlayerInput = true;
    double counter = 0.0;
    double multiply = 0.8;
    boolean newStateChange = true;

    LinkedList<Entity> entity;

    public void start() {
        gameloop();
    }

    public void setspeed(double _speed){
        this.multiply = _speed;
    }

    public void gameloop(){
        for (int i = 0; i < 1000; i++) {


//        while (gameIsruning){
            if(setGame){
                ///
                System.out.println("setgame");
                setGame = false;
            }

            if(newPlayerInput){
                // maybe use interrupt
                System.out.println("new input");
                newPlayerInput = false;
            }
            if(counter*(multiply*10.0) > 100 ){
                System.out.println("evaluate");
                counter=0.0;
            }
            if(newStateChange){
                System.out.println(counter);
            }
            counter++;


        }

    }


}
