package com.expensive_pig.carin.core;

import com.expensive_pig.carin.entity.Anti;
import com.expensive_pig.carin.entity.Entity;
import com.expensive_pig.carin.entity.EntityType;
import com.expensive_pig.carin.entity.Virus;
import com.expensive_pig.carin.game_data.WorldGame;

import java.util.Random;

public class EntityFactory {
    private  WorldGame world;
    private Process[] virusGen;
    private Process[] antiGen;
    private final Random r = new Random();

    public void importGen(Process[] virusGen, Process[] antiGen){
        this.virusGen = virusGen;
        this.antiGen = antiGen;
    }

    public void update(WorldGame _world) {
        world = _world;

    }

    public Entity createEntity(EntityType _type) {
        Entity e = null;
        if(_type.equals(EntityType.VIRUS)){
            e = new Virus();
        }else if(_type.equals(EntityType.ANTIBODY)) {
            e = new Anti();
        }
        e.connectWorld(world);
        int max_X =  world.getMapSize()[1];
        int max_Y = world.getMapSize()[0];

        int posX = r.nextInt(max_X) ;
        int posY = r.nextInt(max_Y);
        if(world.slotIsFree(posX,posY)){
            world.addNewEntity(posX,posY,e.getType());
        }

        return e;
    }
}
