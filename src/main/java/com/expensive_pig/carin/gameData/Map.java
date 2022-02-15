package com.expensive_pig.carin.gameData;

import com.expensive_pig.carin.entity.Entity;

public class Map {
    int m;
    int n;
    Entity[][] mapField;

    public void setMapSize(int m , int n){
        this.m = m;
        this.n = n;
        mapField = new Entity[m][n];

    }
    public void storePosEntity(int posX , int posY , Entity obj){
        mapField[posY][posX] = obj;
    }

    public void clearPosEntity(int posX , int posY){
        mapField[posY][posX] = null;
    }

    public Entity lineOfSign(int posX , int posY , String direction){
        int nowX = posX;
        int nowY = posY;
        Entity target = null;
//        ( (0 <= nowX && nowX <= n) && (0 <= nowY && nowY <= m)) {
            switch (direction) {
                case "left" -> nowX--;
                case "right" -> nowX++;
                case "up" -> nowY++;
                case "down" -> nowY--;
                case "downleft" -> {
                    nowY--;
                    nowX--;
                }
                case "downright" -> {
                    nowY--;
                    nowX++;
                }
                case "upleft" -> {
                    nowY++;
                    nowX--;
                }
                case "upright" -> {
                    nowY++;
                    nowX++;
                }
            }
            if((0 <= nowX && nowX <= n) && (0 <= nowY && nowY <= m)){
                if (mapField[nowY][nowX] != null){
                    target = mapField[nowY][nowX];
                }
            }
      return target;
    }
}
