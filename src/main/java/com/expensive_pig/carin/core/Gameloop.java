package com.expensive_pig.carin.core;

import com.expensive_pig.carin.entity.Entity;
import com.expensive_pig.carin.evaluator.SyntaxError;
import com.expensive_pig.carin.game_data.WorldGame;

import java.util.LinkedList;

public class Gameloop {
    private  WorldGame world;
    private LinkedList<Entity> entities;
    boolean gameIsruning = true;
    boolean setGame = true;
    boolean newPlayerInput = true;
    double counter = 0.0;
    double multiply = 0.8;
    boolean newStateChange = true;
    private EntityFactory newEntity;


    public void start() throws SyntaxError {
        gameloop();
    }

    public void setspeed(double _speed){
        this.multiply = _speed;
    }

    public void gameloop() throws SyntaxError {

        for (int i = 0; i < 1000; i++) {//        while (gameIsruning){
            if(setGame){
                ///
                world = new WorldGame();
                entities = new LinkedList<>();
                newEntity = new EntityFactory();
                // form config
                world.setMapSize(5,6);
                System.out.println("setgame");
                setGame = false;
            }

            if(newPlayerInput){
                // maybe use interrupt
                System.out.println("new input");
                newPlayerInput = false;
            }
            if(counter*(multiply*10.0) > 100 ){
                newEntity.update(world);



                Entity newV = newEntity.createEntity("Virus");
                entities.add(newV);
                for (Entity entity : entities) {
                    System.out.println("evaluate");
                    entity.ealuated();
                }

                counter=0.0;
            }
            if(newStateChange){
                System.out.println(counter);
            }
            counter++;

        }

    }


}
