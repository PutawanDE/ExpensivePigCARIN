package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.evaluator.Program;

public class Anti extends Entity {


    public Anti(Program antibodyType) {
        super.program = antibodyType;
    }

    public void attack(Virus target){
        target.reduceHp();
    }

    public void  die(){
        if(hp<=0) {
            dieTransferToVirus();
        }
    }

    public void dieTransferToVirus(){}

    public EntityType getType(){return EntityType.ANTIBODY;}

}
