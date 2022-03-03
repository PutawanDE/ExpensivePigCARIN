package com.expensive_pig.carin.core;

import com.expensive_pig.carin.entity.Anti;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreditSystem {
    private int credit;
    private int cost;

    private EntityManager entityManager;

    public void buyAndPlace(int posX, int posY, int kind) {
        if (canBuy()) {
            Anti anti = entityManager.createAntibody(posX, posY, kind, this);
            if (anti != null) {
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
