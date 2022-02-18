package com.expensive_pig.carin.core;

import com.expensive_pig.carin.entity.Entity;
import com.expensive_pig.carin.entity.EntityType;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.evaluator.SyntaxError;
import com.expensive_pig.carin.game_data.WorldGame;

public class Gameloop {



    boolean gameIsruning = true;
    boolean setGame = true;
    boolean APIhavenewPlayerInput = true;
    double counter = 0.0;
    double multiply = 0.8;
    boolean newStateChange = true;
    private final EntityFactory entityFactory = new EntityFactory();
    private final InitGame init = new InitGame();
    private final WorldGame world = new WorldGame();



    public void start() throws SyntaxError {
        gameloop();
    }

    public void setspeed(double _speed) {
        this.multiply = _speed;
    }

    public void gameloop() throws SyntaxError {

        if (setGame) {

            Program[] anti = init.InitAnti();
            Program[] virus = init.InitVirus();

            // form config
            world.setMapSize(5, 6);
            world.connect(entityFactory);
            entityFactory.connect(world);

            entityFactory.importGen(virus, anti);
            System.out.println("setgame");
            setGame = false;
        }

        for (int i = 0; i < 1000; i++) {//        while (gameIsruning){
            String playerInput = null;

            //==================================================
            if (APIhavenewPlayerInput) {
                // maybe use interrupt
                playerInput = "1";
                System.out.println("new input");
                APIhavenewPlayerInput = false;
            }
            //==================================================
            if (counter * (multiply * 10.0) > 100) {
                if (playerInput != null) {
                    entityFactory.createEntity(EntityType.ANTIBODY, 1);
                }
                 entityFactory.createEntity(EntityType.VIRUS, null);

                for (Entity entity : entityFactory.entities) {
                    System.out.println("evaluate");
                    entity.ealuated();
                }
                counter = 0.0;
            }
            //==================================================
            if (newStateChange) {
                System.out.println(counter);
            }


            counter++;

        }
    }


}
