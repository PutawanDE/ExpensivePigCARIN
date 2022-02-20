package com.expensive_pig.carin.core;

import com.expensive_pig.carin.entity.Anti;
import com.expensive_pig.carin.entity.Entity;
import com.expensive_pig.carin.entity.EntityType;
import com.expensive_pig.carin.entity.Virus;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.game_data.GameConfiguration;
import com.expensive_pig.carin.game_data.Pair;

import java.util.LinkedList;
import java.util.Random;

public class EntityFactory {
    private static final int NUM_VIRUS_KINDS = 3;
    public final LinkedList<Entity> entities = new LinkedList<>();

    private WorldGame world;
    private GameConfiguration config;
    private final Program[] virusGene;
    private final Program[] antiGene;
    private final Random r = new Random();

    public EntityFactory(Program[] virusGene, Program[] antiGene, GameConfiguration config) {
        this.virusGene = virusGene;
        this.antiGene = antiGene;
        this.config = config;
    }

    public void converseAntiToVirus(int posX, int posY, int kind, Program rna) {
        Entity e;
        e = new Virus(posX, posY, kind, rna, config);
        e.connectWorld(world);
        if (world.slotIsFree(posX, posY)) {
            world.addNewEntity(posX, posY, e);
        }
        entities.add(e);
    }

    public void injectWorld(WorldGame _world) {
        world = _world;
    }

    public Entity createEntity(EntityType type, int posX, int posY, int kind) {
        if (world.slotIsFree(posX, posY) &&
                (type.equals(EntityType.ANTIBODY) || type.equals(EntityType.VIRUS))) {

            Entity e;
            if (type.equals(EntityType.ANTIBODY)) {
                e = new Anti(posX, posY, kind, antiGene[kind], config);
            } else {
                e = new Virus(posX, posY, kind, virusGene[kind], config);
            }

            e.connectWorld(world);
            world.addNewEntity(posX, posY, e);
            return e;
        } else {
            return null;
        }
    }

    public void spawnVirus(float spawnRate) {
        int posX = 0;
        int posY = 0;

        float spawnOrNot = r.nextFloat();

        if (spawnOrNot <= spawnRate) {
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

            int randKind = r.nextInt(NUM_VIRUS_KINDS);

            createEntity(EntityType.VIRUS, posX, posY, randKind);
        }
    }
}
