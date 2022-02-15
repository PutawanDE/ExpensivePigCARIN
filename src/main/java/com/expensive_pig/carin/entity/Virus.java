package com.expensive_pig.carin.entity;

public class Virus extends Entity {
    public void attack(Anti target){
        target.reduceHp();
    }

 
}
