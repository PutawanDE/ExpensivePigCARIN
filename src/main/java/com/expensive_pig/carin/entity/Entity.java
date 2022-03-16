package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.SpringContext;
import com.expensive_pig.carin.controller.GameController;
import com.expensive_pig.carin.core.Direction;
import com.expensive_pig.carin.core.EntityManager;
import com.expensive_pig.carin.evaluator.GeneticCodeEvaluator;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.evaluator.SyntaxError;
import com.expensive_pig.carin.core.WorldGame;
import com.expensive_pig.carin.event.HpEvent;
import com.expensive_pig.carin.event.OutputMoveEvent;
import com.expensive_pig.carin.event.ShootEvent;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public abstract class Entity {
    private GeneticCodeEvaluator evaluator;
    protected EntityManager entityManager;

    protected GameController gameController;

    protected WorldGame world;

    private final Map<String, Integer> variableMap = new HashMap<>();

    @Getter
    protected boolean isAlive = true;

    protected int killCount;

    protected Program program;

    // from config
    @Getter
    protected int kind;
    protected int maxHp;
    protected int hp;
    protected int attackDamage;

    @Getter
    protected int posX;
    @Getter
    protected int posY;

    public Entity(int posX, int posY, int kind, Program program,
                  EntityManager entityManager, WorldGame world) {
        this.posX = posX;
        this.posY = posY;
        this.kind = kind;
        this.program = program;
        this.entityManager = entityManager;
        this.world = world;
        gameController = SpringContext.getBean(GameController.class);
    }

    public void evaluate() throws SyntaxError {
        if (isAlive) {
            evaluator = new GeneticCodeEvaluator();
            evaluator.evaluateProgram(program, this, variableMap);
        }
    }


    /**
     * - move()
     * - attack()
     * - status()
     */

    protected abstract void attack(Entity target, int dmg);

    protected abstract void receiveDmg(int dmgReceive, Entity attacker);

    protected void changeHp(int hpChange) {
        if (hp + hpChange <= 0) {
            hpChange = -hp;
        }
        hp += hpChange;
        gameController.sendOutputEvent(entityManager.getSessionId(),
                new HpEvent(posX, posY, hpChange));
    }

    public void status() {
        System.out.println(maxHp + " " + hp + " " + attackDamage + " " + killCount);
    }

    public abstract void die();

    public void move(Direction direction) {
        int[] toPos = getPosFromDirection(direction);
        int oldPosX = posX;
        int oldPosY = posY;
        int toPosX = toPos[0];
        int toPosY = toPos[1];

        if (world.movePosEntity(posX, posY, toPosX, toPosY)) {
            posX = toPosX;
            posY = toPosY;
            gameController.sendOutputEvent(entityManager.getSessionId(),
                    new OutputMoveEvent(oldPosX, oldPosY, toPosX, toPosY));
        }
    }

    public void moveByUser(int toPosX, int toPosY, int hpCost) {
    }

    public void shoot(Direction direction) {
        int[] toPos = getPosFromDirection(direction);
        int toPosX = toPos[0];
        int toPosY = toPos[1];
        gameController.sendOutputEvent(entityManager.getSessionId(),
                new ShootEvent(posX, posY, direction));

        Entity target = world.getTarget(toPosX, toPosY);

        if (target != null) {
            attack(target, attackDamage);
        }
    }

    private int[] getPosFromDirection(Direction direction) {
        int toPosX = posX;
        int toPosY = posY;
        switch (direction) {
            case UP -> toPosY--;
            case UP_RIGHT -> {
                toPosY--;
                toPosX++;
            }
            case RIGHT -> toPosX++;
            case DOWN_RIGHT -> {
                toPosY++;
                toPosX++;
            }
            case DOWN -> toPosY++;
            case DOWN_LEFT -> {
                toPosY++;
                toPosX--;
            }
            case LEFT -> toPosX--;
            case UP_LEFT -> {
                toPosY--;
                toPosX--;
            }
        }
        return new int[]{toPosX, toPosY};
    }

    public abstract EntityType getType();

    //Sensor ability
    public int getAntibody() {
        return world.search(posX, posY, EntityType.ANTIBODY);
    }

    public int getVirus() {
        return world.search(posX, posY, EntityType.VIRUS);
    }

    public int nearby(Direction direction) {
        return world.searchNearby(posX, posY, EntityType.ENTITY, direction);
    }
}
