package com.expensive_pig.carin.core;

import com.expensive_pig.carin.entity.Entity;
import com.expensive_pig.carin.entity.Virus;
import com.expensive_pig.carin.gameData.WorldGame;

public class GenerateEntity {
    private  WorldGame world;
    public GenerateEntity(){


    }

    public void update(WorldGame _world) {
        world = _world;

    }

    public Entity create() {
        Entity virus1 = new Virus();
        virus1.connectWorld(world);
        int posX =  2;
        int posY = 3;
        if(world.slootIsFree(posX,posY)){
            world.addNewEntity(posX,posY,virus1.getType());
        }

        return virus1;
    }
}
