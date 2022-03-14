package com.expensive_pig.carin.core;

import com.expensive_pig.carin.SpringContext;
import com.expensive_pig.carin.controller.GameController;
import com.expensive_pig.carin.entity.Anti;
import com.expensive_pig.carin.event.CreditEvent;

public class CreditSystem {
    private int credit;
    private int cost;
    private final String sessionId;

    private EntityManager entityManager;

    private GameController gameController;

    public CreditSystem(String sessionId, int credit, int cost, EntityManager entityManager) {
        this.sessionId = sessionId;
        this.credit = credit;
        this.cost = cost;
        this.entityManager = entityManager;
        gameController = SpringContext.getBean(GameController.class);
    }

    public void buyAndPlace(int posX, int posY, int kind) {
        if (canBuy()) {
            Anti anti = entityManager.createAntibody(posX, posY, kind, this);
            if (anti != null) {
                credit -= cost;
                gameController.sendOutputEvent(sessionId, new CreditEvent(posX, posY, credit));
            }
        }
    }

    public void gainCredit(int posX, int posY, int gain) {
        credit += gain;
        gameController.sendOutputEvent(sessionId, new CreditEvent(posX, posY, credit));
    }

    private boolean canBuy() {
        return credit - cost >= 0;
    }
}
