package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.core.CreditSystem;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.game_data.GameConfiguration;

public class Anti extends Entity {
    private int infectedKind;
    private Program infectedProgram;
    private final CreditSystem creditSystem;

    private final int killCreditGain;
    private final int killHpGain;

    public Anti(int posX, int posY, int kind, Program program, GameConfiguration config,
                CreditSystem creditSystem) {
        super(posX, posY, kind, program);
        super.attackDamage = config.getAntibodyAttackDamage();
        super.maxHp = config.getInitialAntibodyHp();
        super.hp = super.maxHp;

        this.creditSystem = creditSystem;
        this.killHpGain = config.getAntibodyKillHpGain();
        this.killCreditGain = config.getAntibodyKillCreditGain();
    }

    @Override
    protected void infected(int kind, Program program) {
        infectedKind = kind;
        infectedProgram = program;
    }

    @Override
    protected void attack(Entity target, int dmg) {
        target.reduceHp(dmg);
        if (target.dead() && target.getType().equals(EntityType.VIRUS)) {
            creditSystem.gainCredit(killCreditGain);
            earnHp(killHpGain);
            killCount++;
        }
    }

    @Override
    public void moveByUser(int toPosX, int toPosY, int hpCost) {
        int newHp = hp - hpCost;
        if (newHp > 0) {
            hp = newHp;
            world.movePosEntity(posX, posY, toPosX, toPosY);
        }
    }

    @Override
    public boolean dead() {
        if (hp <= 0) {
            System.out.println("die");
            if (!live) {
                super.world.killPosEntity(super.posX, super.posY, this);
                super.dieTransferToVirus(infectedKind, infectedProgram);
            }
            return true;
        } else return false;
    }

    @Override
    public EntityType getType() {
        return EntityType.ANTIBODY;
    }

}
