package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.evaluator.Program;

public class Anti extends Entity {
    int virusKind;
    Program virusProgram;

    public Anti(int posX, int posY,int kind , Program program) {
        super.posX = posX;
        super.posY = posY;
        super.kind = kind;
        super.program = program;
    }

    private void antiInfected(int kind , Program program){
        virusKind = kind;
        virusProgram = program;
    }

    @Override
    public void reduceAntiHp(int damage,int kind, Program program) {
        antiInfected(kind,program);
        if (!isDie()) {
            hp -= damage;
            if (hp <= 0) {
                live = false;
            }
        }
    }
    @Override
    public boolean isDie() {
        if (hp <= 0) {
            System.out.println("die");
            if(!live) {
                super.world.clearPosEntity(super.posX, super.posY);
                super.dieTransferToVirus(virusKind , virusProgram);
            }
            return true;
        } else return false;
    }



    public EntityType getType(){return EntityType.ANTIBODY;}

}
