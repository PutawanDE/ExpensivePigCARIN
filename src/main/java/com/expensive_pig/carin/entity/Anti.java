package com.expensive_pig.carin.entity;

public class Anti extends Entity {



    public void attack(Virus target){
        target.reduceHp();
    }

    public void  die(){
        if(hp<=0) {
            dieTransferToVirus();
        }
    }

    public void dieTransferToVirus(){}

    public EntityType getType(){return EntityType.ANTIBODY;}

}
