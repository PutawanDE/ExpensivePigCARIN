package com.expensive_pig.carin.core;

import com.expensive_pig.carin.entity.Anti;
import com.expensive_pig.carin.entity.Entity;
import com.expensive_pig.carin.entity.Virus;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.game_data.GameConfiguration;
import com.expensive_pig.carin.game_data.Pair;

import java.util.LinkedList;
import java.util.Random;

public class EntityManager {
    private static final int NUM_VIRUS_KINDS = 3;

    public final LinkedList<Entity> entities = new LinkedList<>();
    private final LinkedList<Entity> deathEntities = new LinkedList<>();
    private final LinkedList<Anti> infectedAnti = new LinkedList<>();

    private WorldGame world;
    private GameConfiguration config;
    private final Program[] virusGene;
    private final Program[] antiGene;
    private final Random r = new Random();

    public EntityManager(Program[] virusGene, Program[] antiGene, GameConfiguration config,
                         WorldGame world) {
        this.virusGene = virusGene;
        this.antiGene = antiGene;
        this.config = config;
        this.world = world;
    }

    public Anti createAntibody(int posX, int posY, int kind, CreditSystem creditSystem) {
        if (world.slotIsFree(posX, posY)) {
            Anti e = new Anti(posX, posY, kind, antiGene[kind], config,
                    creditSystem, this, world);
            entities.add(e);
            world.addNewEntity(posX, posY, e);
            return e;
        } else {
            return null;
        }
    }

    public void createVirus(int posX, int posY, int kind) {
        if (world.slotIsFree(posX, posY)) {
            Virus e = new Virus(posX, posY, kind, virusGene[kind], config, this, world);
            entities.add(e);
            world.addNewEntity(posX, posY, e);
        }
    }

    public void spawnVirus() {
        int posX = 0;
        int posY = 0;

        float spawnOrNot = r.nextFloat();

        if (spawnOrNot <= config.getVirusSpawnRate()) {
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
            createVirus(posX, posY, randKind);
        }
    }

    public void dead(Virus virus) {
        world.clearPosEntity(virus.getPosX(), virus.getPosY());
        deathEntities.add(virus);
    }

    public void dieConvertToVirus(Anti anti) {
        world.clearPosEntity(anti.getPosX(), anti.getPosY());
        deathEntities.add(anti);
        infectedAnti.add(anti);
    }

    public void clearDeadAndSpawnInfected() {
        entities.removeAll(deathEntities);
        for (Anti a : infectedAnti) {
            createVirus(a.getPosX(), a.getPosY(), a.getInfectedKind());
        }
        deathEntities.clear();
        infectedAnti.clear();
    }
}