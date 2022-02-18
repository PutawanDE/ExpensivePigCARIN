package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.evaluator.Program;

public class Virus extends Entity {

    public Virus(int posX, int posY, Program program) {
        super.posX = posX;
        super.posY = posY;
        super.program = program;
    }

    public void attack(Anti target){
        target.reduceHp(damage);
    }
    public EntityType getType(){return EntityType.VIRUS;}
 
}
