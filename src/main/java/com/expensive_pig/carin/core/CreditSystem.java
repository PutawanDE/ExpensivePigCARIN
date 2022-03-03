package com.expensive_pig.carin.core;

import com.expensive_pig.carin.controller.GameController;
import com.expensive_pig.carin.entity.Anti;
import com.expensive_pig.carin.event.CreditEvent;
import org.springframework.beans.factory.annotation.Autowired;

public class CreditSystem {
    private int credit;
    private int cost;
    private final String sessionId;

    private EntityManager entityManager;

    @Autowired
    private GameController gameController;

    public CreditSystem(String sessionId, int credit, int cost, EntityManager entityManager) {
        this.sessionId = sessionId;
        this.credit = credit;
        this.cost = cost;
        this.entityManager = entityManager;
    }

    public void buyAndPlace(int posX, int posY, int kind) {
        if (canBuy()) {
            Anti anti = entityManager.createAntibody(posX, posY, kind, this);
            if (anti != null) {
                credit -= cost;
                gameController.sendOutputEvent(sessionId, new CreditEvent(posX, posY, -cost));
            }
        }
    }

    public void gainCredit(int posX, int posY, int gain) {
        credit += gain;
        gameController.sendOutputEvent(sessionId, new CreditEvent(posX, posY, gain));
    }

    private boolean canBuy() {
        return credit - cost >= 0;
    }
}
