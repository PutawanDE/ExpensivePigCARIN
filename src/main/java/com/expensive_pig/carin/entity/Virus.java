package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.evaluator.Program;

public class Virus extends Entity {

    public Virus(int posX, int posY,int kind, Program program) {
        super.posX = posX;
        super.posY = posY;
        super.kind = kind;
        super.program = program;
    }


    public EntityType getType(){return EntityType.VIRUS;}

}
