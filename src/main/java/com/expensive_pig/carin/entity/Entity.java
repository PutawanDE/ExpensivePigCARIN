package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.evaluator.Program;

public class Entity {
    private Program program;

    int maxhp;
    int hp;
    int damage;
    int killcout;


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




}
