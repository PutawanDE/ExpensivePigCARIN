package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.gameData.Map;

public class Entity {
    private Program program;

    int maxhp;
    int hp;
    int damage;
    int killcout;
    int posX;
    int posY;


    /**
     *  move()
     *    - attack()
     *    - isVirus()
     *    - isAntiBody()
     *    - status()
     *
     */

    public void reduceHp(){
        hp--;
    }

    public void  earnHp(){
        hp++;
    }

    public void status(){
        System.out.println(maxhp + " " + hp + " " + damage + " " + killcout);
    }

    public int nearby(){

        return 0;

    }
    public void  isDie(){
        if(hp<=0) {
            System.out.println("die");
        }
    }


    public void move(String direction) {
        switch (direction) {
            case "left" -> posX--;
            case "right" -> posX++;
            case "up" -> posY++;
            case "down" -> posY--;
            case "downleft" -> {
                posY--;
                posX--;
            }
            case "downright" -> {
                posY--;
                posX++;
            }
            case "upleft" -> {
                posY++;
                posX--;
            }
            case "upright" -> {
                posY++;
                posX++;
            }

        }
    }

    public void shoot(String direction) {
//        Entity target = lineOfSign();
        }

}
