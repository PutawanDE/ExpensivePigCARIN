package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.core.Direction;
import com.expensive_pig.carin.evaluator.GeneticCodeEvaluator;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.evaluator.SyntaxError;
import com.expensive_pig.carin.core.WorldGame;

import java.util.HashMap;
import java.util.Map;

public class Entity {
    protected Program program;
    protected int kind; // get form config
    private GeneticCodeEvaluator evaluator;
    protected WorldGame world;

    int maxhp; // get form config
    int hp;  // get form config
    int damage; // get form config
    boolean live = true;
    int killCount;
    int posX;
    int posY;

    protected final Map<String, Integer> variableMap = new HashMap<>();

    public Entity(int posX, int posY, int kind, Program program) {
        this.posX = posX;
        this.posY = posY;
        this.kind = kind;
        this.program = program;
    }

    public void connectWorld(WorldGame _world) {
        world = _world;
    }

    public void evaluate() throws SyntaxError {
        evaluator = new GeneticCodeEvaluator();
        evaluator.evaluateProgram(program, this, variableMap);
    }


    /**
     * - move()
     * - attack()
     * - status()
     *
     * @param damage
     */

    public void reduceVirusHp(int damage) {
        if (!isDie()) {
            hp -= damage;
            if (hp <= 0) {
                live = false;
            }
        }
    }


    public void reduceAntiHp(int damage, int kind, Program program) {

    }


    public void earnHp(int damage) {
        hp += damage;
    }

    public void status() {
        System.out.println(maxhp + " " + hp + " " + damage + " " + killCount);
    }

    public boolean isDie() {
        if (hp <= 0) {
            System.out.println("die");
            if (!live) {
                world.killPosEntity(posX, posY, this);
            }
            return true;
        } else return false;
    }

    public void dieTransferToVirus(int virusKind, Program virusProgram) {
        // add new virus in die pos of this!
        world.converseEntity(posX, posY, virusKind, virusProgram);
    }

    public void move(Direction direction) throws SyntaxError {
        int tempposX = posX;
        int tempposY = posY;
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
        world.movePosEntity(tempposX, tempposY, posX, posY);
    }

    public void moveByUser(int toposX, int toposY, int hpCost) {
        if (this.getType().equals(EntityType.ANTIBODY)) {
            int newHp = hp - hpCost;
            if (newHp > 0) {
                hp = newHp;
                world.movePosEntity(posX, posY, toposX, toposY);
            }
        }
    }

    public void shoot(Direction direction) {
        Entity taget = null;
        switch (direction) {
            case UP -> taget = world.getTarget(posX, posY + 1);
            case UP_RIGHT -> taget = world.getTarget(posX + 1, posY + 1);
            case RIGHT -> taget = world.getTarget(posX + 1, posY);
            case DOWN_RIGHT -> taget = world.getTarget(posX + 1, posY - 1);
            case DOWN -> taget = world.getTarget(posX, posY - 1);
            case DOWN_LEFT -> taget = world.getTarget(posX - 1, posY - 1);
            case LEFT -> taget = world.getTarget(posX - 1, posY);
            case UP_LEFT -> taget = world.getTarget(posX - 1, posY + 1);
        }

        if (world.getTarget(posX, posY).getType().equals(EntityType.VIRUS)) {  // cheack who shoot
            taget.reduceAntiHp(damage, kind, program);
        } else {
            taget.reduceVirusHp(damage);
        }

        earnHp(damage);

    }

    public EntityType getType() {
        return EntityType.ENTITY;
    }

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
