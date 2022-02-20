package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.game_data.GameConfiguration;

public class Virus extends Entity {

    public Virus(int posX, int posY, int kind, Program program, GameConfiguration config) {
        super(posX, posY, kind, program);
        super.damage = config.getAntibodyAttackDamage();
        super.maxhp = config.getInitialAntibodyHp();
        super.hp = super.maxhp;
    }

    public EntityType getType() {
        return EntityType.VIRUS;
    }

}
