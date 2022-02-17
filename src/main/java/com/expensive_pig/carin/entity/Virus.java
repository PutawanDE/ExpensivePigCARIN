package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.evaluator.Program;

public class Virus extends Entity {
    public Virus(Program antibodyType) {
        super.program = antibodyType;
    }

    public void attack(Anti target){
        target.reduceHp();
    }
    public EntityType getType(){return EntityType.VIRUS;}
 
}
