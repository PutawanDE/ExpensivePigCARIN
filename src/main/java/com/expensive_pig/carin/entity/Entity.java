package com.expensive_pig.carin.entity;

import com.expensive_pig.carin.core.Direction;
import com.expensive_pig.carin.evaluator.GeneticCodeEvaluator;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.evaluator.SyntaxError;
import com.expensive_pig.carin.game_data.WorldGame;

public class Entity {
    protected Program program;
    private GeneticCodeEvaluator evaluator;
    private WorldGame world;

    int maxhp;
    int hp;
    int damage;
    int killcout;
    int posX;
    int posY;

    public void connectWorld(WorldGame _world) {
        world = _world;
    }

    public void ealuated() throws SyntaxError {
        evaluator = new GeneticCodeEvaluator();
        evaluator.evaluateProgram(program, this);
    }


    /**
     * - move()
     * - attack()
     * - status()
     */

    public void reduceHp() {
        hp--;
    }

    public void earnHp() {
        hp++;
    }

    public void status() {
        System.out.println(maxhp + " " + hp + " " + damage + " " + killcout);
    }

    public boolean isDie() {
        if (hp <= 0) {
            System.out.println("die");
            return true;
        } else return false;
    }


    public void move(String direction) {
        switch (direction) {
            case "left" -> posX--;
            case "right" -> posX++;
            case "up" -> posY++;
            case "down" -> posY--;
            case "downleft" -> {
                posY--;
                posX--;
            }
            case "downright" -> {
                posY--;
                posX++;
            }
            case "upleft" -> {
                posY++;
                posX--;
            }
            case "upright" -> {
                posY++;
                posX++;
            }

        }
    }

    public void shoot(String direction) {
//        Entity target = lineOfSign();
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

    public Entity getTarget() {
//        world[][]
        return null;
    }

    public int nearby(Direction direction) {
        return world.searchNearby(posX, posY, EntityType.ENTITY, direction);
    }
}
