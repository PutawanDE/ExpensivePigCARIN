package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.evaluator.Program;

public class Anti extends Entity {


    public Anti(int posX, int posY, Program program) {
        super.posX = posX;
        super.posY = posY;
        super.program = program;
    }

    public void attack(Virus target){
        target.reduceHp(damage);
    }

    public void  die(){
        if(hp<=0) {
            dieTransferToVirus();
        }
    }

    public void dieTransferToVirus(){}

    public EntityType getType(){return EntityType.ANTIBODY;}

}
