package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.game_data.GameConfiguration;

public class Virus extends Entity {
    private final int attackHpGain;

    public Virus(int posX, int posY, int kind, Program program, GameConfiguration config) {
        super(posX, posY, kind, program);
        super.attackDamage = config.getVirusAttackDamage();
        super.maxHp = config.getInitialVirusHp();
        super.hp = maxHp;

        this.attackHpGain = config.getVirusAttackGain();
    }

    @Override
    protected void attack(Entity target, int dmg) {
        target.reduceHp(dmg);
        if (!target.getType().equals(EntityType.VIRUS)) {
            earnHp(attackHpGain);
            target.infected(kind, program);
            if (target.dead()) {
                killCount++;
            }
        }
    }

    @Override
    public boolean dead() {
        if (hp <= 0) {
            System.out.println("die");
            if (!live) {
                world.killPosEntity(posX, posY, this);
                live = false;
            }
            return true;
        } else return false;
    }

    @Override
    public EntityType getType() {
        return EntityType.VIRUS;
    }

}
