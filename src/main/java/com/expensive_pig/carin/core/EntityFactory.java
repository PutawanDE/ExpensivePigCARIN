package com.expensive_pig.carin.core;

import com.expensive_pig.carin.entity.Anti;
import com.expensive_pig.carin.entity.Entity;
import com.expensive_pig.carin.entity.EntityType;
import com.expensive_pig.carin.entity.Virus;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.game_data.Pair;
import com.expensive_pig.carin.game_data.WorldGame;

import java.util.Random;

public class EntityFactory {
    private WorldGame world;
    private Program[] virusGen;
    private Program[] antiGen;
    private final Random r = new Random();

    public void importGen(Program[] virusGen, Program[] antiGen) {
        this.virusGen = virusGen;
        this.antiGen = antiGen;
    }

    public void update(WorldGame _world) {
        world = _world;

    }

    public Entity converseAntiToVirus(int posX, int posY, int kind, Program rna) {
        Entity e = null;

        e = new Virus(posX, posY, kind, rna);
        e.connectWorld(world);

        if (world.slotIsFree(posX, posY)) {
            world.addNewEntity(posX, posY, e);
        }

        return e;
    }

    public Entity createEntity(EntityType _type, Integer antiKind) {
        Entity e = null;

        int posX = 0;
        int posY = 0;

        int size = world.freeField.size();
        int item = r.nextInt(size); // In real life, the Random object should be rather more shared than this
        int i = 0;
        for (Pair tile : world.freeField) {
            if (i == item) {
                posX = tile.getX();
                posY = tile.getY();
                break;
            }
            i++;
        }


        if (_type.equals(EntityType.VIRUS)) {
            int kind = r.nextInt(3);
            e = new Virus(posX, posY, kind, virusGen[kind]);
            e.connectWorld(world);
        } else if (_type.equals(EntityType.ANTIBODY)) {
            e = new Anti(posX, posY, antiKind, antiGen[antiKind]);
            e.connectWorld(world);
        }

        world.addNewEntity(posX, posY, e);


        return e;
    }
}
