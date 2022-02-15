package com.expensive_pig.carin.entity;

public class Anti extends Entity {



    public void attack(Virus target){
        target.reduceHp();
    }

    @Override
    public void  isDie(){
        if(hp<=0) {
            dieTransferToVirus();
        }
    }

    public void dieTransferToVirus(){}

}
