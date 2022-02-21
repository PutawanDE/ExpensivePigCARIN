package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.core.Direction;
import com.expensive_pig.carin.core.EntityManager;
import com.expensive_pig.carin.evaluator.GeneticCodeEvaluator;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.evaluator.SyntaxError;
import com.expensive_pig.carin.core.WorldGame;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public abstract class Entity {
    private GeneticCodeEvaluator evaluator;
    protected EntityManager entityManager;

    protected WorldGame world;

    private final Map<String, Integer> variableMap = new HashMap<>();
    protected boolean live = true;
    protected int killCount;

    protected Program program;

    // from config
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
    }

    public void evaluate() throws SyntaxError {
        if (live) {
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

    protected abstract void receiveDmg(int dmgReceive, int attackerKind);

    protected void earnHp(int hpGain) {
        hp += hpGain;
    }

    public void status() {
        System.out.println(maxHp + " " + hp + " " + attackDamage + " " + killCount);
    }

    public abstract void dead();

    public void move(Direction direction) {
        int tempPosX = posX;
        int tempPosY = posY;
        switch (direction) {
            case UP -> posY++;
            case UP_RIGHT -> {
                posY++;
                posX++;
            }
            case RIGHT -> posX++;
            case DOWN_RIGHT -> {
                posY--;
                posX++;
            }
            case DOWN -> posY--;
            case DOWN_LEFT -> {
                posY--;
                posX--;
            }
            case LEFT -> posX--;
            case UP_LEFT -> {
                posY++;
                posX--;
            }
        }

        world.movePosEntity(tempPosX, tempPosY, posX, posY);
    }

    public void moveByUser(int toPosX, int toPosY, int hpCost) {
    }

    public void shoot(Direction direction) {
        Entity target = null;
        switch (direction) {
            case UP -> target = world.getTarget(posX, posY + 1);
            case UP_RIGHT -> target = world.getTarget(posX + 1, posY + 1);
            case RIGHT -> target = world.getTarget(posX + 1, posY);
            case DOWN_RIGHT -> target = world.getTarget(posX + 1, posY - 1);
            case DOWN -> target = world.getTarget(posX, posY - 1);
            case DOWN_LEFT -> target = world.getTarget(posX - 1, posY - 1);
            case LEFT -> target = world.getTarget(posX - 1, posY);
            case UP_LEFT -> target = world.getTarget(posX - 1, posY + 1);
        }

        if (target != null) {
            attack(target, attackDamage);
        }
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
