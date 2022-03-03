package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.core.EntityManager;
import com.expensive_pig.carin.core.WorldGame;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.game_data.GameConfiguration;

public class Virus extends Entity {
    private final int attackHpGain;

    public Virus(int posX, int posY, int kind, Program program, GameConfiguration config,
                 EntityManager entityManager, WorldGame world) {
        super(posX, posY, kind, program, entityManager, world);
        super.attackDamage = config.getVirusAttackDamage();
        super.maxHp = config.getInitialVirusHp();
        super.hp = maxHp;

        this.attackHpGain = config.getVirusAttackGain();
    }

    @Override
    protected void attack(Entity target, int dmg) {
        if (target.live) {
            target.receiveDmg(dmg, kind);
            if (!target.getType().equals(EntityType.VIRUS)) {
                changeHp(attackHpGain);
                killCount++;
            }
        }
    }

    @Override
    protected void receiveDmg(int dmgReceive, int attackerKind) {
        if (live) {
            changeHp(dmgReceive);
            if (hp <= 0) {
                dead();
            }
        }
    }

    @Override
    public void dead() {
        live = false;
        entityManager.dead(this);
    }

    @Override
    public EntityType getType() {
        return EntityType.VIRUS;
    }
}
