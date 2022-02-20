package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.game_data.GameConfiguration;

public class Anti extends Entity {
    int infectedKind;
    Program infectedProgram;

    public Anti(int posX, int posY, int kind, Program program, GameConfiguration config) {
        super(posX, posY, kind, program);
        super.damage = config.getAntibodyAttackDamage();
        super.maxhp = config.getInitialAntibodyHp();
        super.hp = super.maxhp;
    }

    private void antiInfected(int kind, Program program) {
        infectedKind = kind;
        infectedProgram = program;
    }

    @Override
    public void reduceAntiHp(int damage, int kind, Program program) {
        antiInfected(kind, program);
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
            if (!live) {

                super.world.killPosEntity(super.posX, super.posY, this);
                super.dieTransferToVirus(infectedKind, infectedProgram);
            }
            return true;
        } else return false;
    }


    public EntityType getType() {
        return EntityType.ANTIBODY;
    }

}
