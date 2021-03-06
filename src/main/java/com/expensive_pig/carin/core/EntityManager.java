package com.expensive_pig.carin.core;

import com.expensive_pig.carin.SpringContext;
import com.expensive_pig.carin.controller.GameController;
import com.expensive_pig.carin.entity.Anti;
import com.expensive_pig.carin.entity.Entity;
import com.expensive_pig.carin.entity.Virus;
import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.event.DieEvent;
import com.expensive_pig.carin.event.InfectEvent;
import com.expensive_pig.carin.event.SpawnEvent;
import com.expensive_pig.carin.game_data.GameConfiguration;
import com.expensive_pig.carin.game_data.Pair;
import lombok.Getter;

import java.util.LinkedList;
import java.util.Random;

public class EntityManager {
    private static final int NUM_VIRUS_KINDS = 3;

    public final LinkedList<Entity> entities = new LinkedList<>();
    private final LinkedList<Anti> infectedAnti = new LinkedList<>();

    private WorldGame world;
    private GameConfiguration config;

    private GameController gameController;

    @Getter
    private int numberAnti = 0;
    @Getter
    private int numberVirus = 0;

    @Getter
    private int antiDeadCount = 0;
    @Getter
    private int virusDeadCount = 0;

    @Getter
    private boolean isFirstVirusSpawn = false;

    @Getter
    private final String sessionId;

    private final Program[] virusGene;
    private final Program[] antiGene;
    private final Random r = new Random();

    public EntityManager(Program[] virusGene, Program[] antiGene, GameConfiguration config,
                         WorldGame world, String sessionId) {
        this.virusGene = virusGene;
        this.antiGene = antiGene;
        this.config = config;
        this.world = world;
        this.sessionId = sessionId;
        gameController = SpringContext.getBean(GameController.class);
    }

    public Anti createAntibody(int posX, int posY, int kind, CreditSystem creditSystem) {
        if (world.slotIsFree(posX, posY)) {
            Anti e = new Anti(posX, posY, kind, antiGene[kind], config,
                    creditSystem, this, world);
            entities.add(e);
            numberAnti++;
            world.addNewEntity(posX, posY, e);
            gameController.sendOutputEvent(sessionId, new SpawnEvent(posX, posY, "A" + (kind + 1), config.getInitialAntibodyHp()));
            return e;
        } else {
            return null;
        }
    }

    public void createVirus(int posX, int posY, int kind) {
        if (world.slotIsFree(posX, posY)) {
            Virus e = new Virus(posX, posY, kind, virusGene[kind], config, this, world);
            entities.add(e);
            numberVirus++;
            world.addNewEntity(posX, posY, e);
            gameController.sendOutputEvent(sessionId, new SpawnEvent(posX, posY, "V" + (kind + 1),config.getInitialVirusHp()));
        }
    }

    public void spawnVirus() {
        int posX = 0;
        int posY = 0;

        float spawnOrNot = r.nextFloat();

        if (spawnOrNot <= config.getVirusSpawnRate()) {
            int size = world.freeField.size();
            if (size <= 0) return;

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

            int randKind = r.nextInt(0, NUM_VIRUS_KINDS);
            createVirus(posX, posY, randKind);

            isFirstVirusSpawn = true;
        }
    }

    public void die(Virus virus) {
        virusDeadCount++;
        numberVirus--;
        gameController.sendOutputEvent(sessionId, new DieEvent(virus.getPosX(), virus.getPosY()));
        world.clearPosEntity(virus.getPosX(), virus.getPosY());
    }

    public void die(Anti anti) {
        antiDeadCount++;
        numberAnti--;
        gameController.sendOutputEvent(sessionId, new DieEvent(anti.getPosX(), anti.getPosY()));
        world.clearPosEntity(anti.getPosX(), anti.getPosY());
    }

    public void dieConvertToVirus(Anti anti, int infectedKind) {
        die(anti);
        gameController.sendOutputEvent(sessionId, new InfectEvent(anti.getPosX(), anti.getPosY(),
                "V" + (infectedKind + 1)));
        infectedAnti.add(anti);
    }

    public void spawnInfected() {
        for (Anti a : infectedAnti) {
            createVirus(a.getPosX(), a.getPosY(), a.getInfectedKind().getKind());
        }
        infectedAnti.clear();
    }
}
