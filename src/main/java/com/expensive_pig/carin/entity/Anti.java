package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.core.CreditSystem;
import com.expensive_pig.carin.core.EntityManager;
import com.expensive_pig.carin.core.WorldGame;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.event.OutputMoveEvent;
import com.expensive_pig.carin.game_data.GameConfiguration;
import lombok.Getter;

public class Anti extends Entity {
    @Getter
    private int infectedKind;
    private final CreditSystem creditSystem;

    private final int killCreditGain;
    private final int killHpGain;

    public Anti(int posX, int posY, int kind, Program program, GameConfiguration config,
                CreditSystem creditSystem, EntityManager entityManager, WorldGame world) {
        super(posX, posY, kind, program, entityManager, world);
        super.attackDamage = config.getAntibodyAttackDamage();
        super.maxHp = config.getInitialAntibodyHp();
        super.hp = super.maxHp;

        this.creditSystem = creditSystem;
        this.killHpGain = config.getAntibodyKillHpGain();
        this.killCreditGain = config.getAntibodyKillCreditGain();
    }

    @Override
    protected void attack(Entity target, int dmg) {
        if (target.isAlive) {
            target.receiveDmg(dmg, kind);
            if (target.getType().equals(EntityType.VIRUS)) {
                if(!target.isAlive) {
                    creditSystem.gainCredit(posX, posY, killCreditGain);
                    changeHp(killHpGain);
                    killCount++;
                }
            }
        }
    }

    @Override
    public void moveByUser(int toPosX, int toPosY, int hpCost) {
        int newHp = hp - hpCost;
        if (newHp > 0) {
            if (world.movePosEntity(posX, posY, toPosX, toPosY)) {
                changeHp(-hpCost);
                gameController.sendOutputEvent(entityManager.getSessionId(),
                        new OutputMoveEvent(posX, posY, toPosX, toPosY));
                posX = toPosX;
                posY = toPosY;
            }
        }
    }

    @Override
    protected void receiveDmg(int dmgReceive, int attackerKind) {
        if (isAlive) {
            changeHp(-dmgReceive);
            infectedKind = attackerKind;
            if (hp <= 0) {
                die();
                isAlive = false;
            }
        }
    }

    @Override
    public void die() {
        isAlive = false;
        entityManager.dieConvertToVirus(this);
    }

    @Override
    public EntityType getType() {
        return EntityType.ANTIBODY;
    }

}
