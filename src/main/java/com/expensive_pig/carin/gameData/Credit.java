package com.expensive_pig.carin.gameData;

public class Credit {
    private  int money;

    public boolean canBuy(int cost){
        if(money-cost<0){
            return false;
        }else {
            return true;
        }
    }



    public void setMoney(int setMoney){
        money = setMoney;
    }
}
