package com.expensive_pig.carin.core;

import com.expensive_pig.carin.entity.Entity;
import com.expensive_pig.carin.entity.EntityType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreditSystem {
    private int credit;
    private int cost;

    private EntityFactory entityFactory;

    public void buyAndPlace(int posX, int posY, int kind) {
        if (canBuy()) {
            Entity e = entityFactory.createEntity(EntityType.ANTIBODY, posX, posY, kind);
            if (e != null) {
                credit -= cost;
            }
        }
    }

    public void gainCredit(int gain) {
        credit += gain;
    }

    private boolean canBuy() {
        return credit - cost >= 0;
    }
}
