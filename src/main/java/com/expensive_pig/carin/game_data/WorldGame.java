package com.expensive_pig.carin.game_data;

import com.expensive_pig.carin.entity.Entity;
import com.expensive_pig.carin.evaluator.SyntaxError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorldGame {
    int m;
    int n;
    String[][] mapField;

    public void setMapSize(int m, int n) {
        this.m = m;
        this.n = n;
        mapField = new String[m][n];

    }
    public int[] getMapSize() {
        return  new int[] {m,n};
    }
    public boolean slootIsFree(int posX, int posY) {
        return mapField[posY][posX] == null;
    }
//    public Entity getTarget(int posX,int posY){
////        return mapField[posY][posX];
//    }
    public void addNewEntity(int posX, int posY, String type) {
        mapField[posY][posX] = type;
    }
    public void movePosEntity(int posX, int posY, int toposX,int toposY) throws SyntaxError {
        if (mapField[posY][posX] != null) {
            System.out.println("มีตัวอยู๋");
            throw new SyntaxError();
        }
        mapField[toposY][toposX] = mapField[posY][posX];
        clearPosEntity(posX,posY);

    }
    public void clearPosEntity(int posX, int posY) {
        mapField[posY][posX] = null;
    }

    public int searchNearby(int posX, int posY, String entity, String direction) {
        return lineOfSign(posX,posY,direction ,entity);
    }
    public int search(int posX , int posY , String type){
        List<Integer> list = new ArrayList<>();
        list.add(  lineOfSign(posX,posY,"up" ,type));
        list.add(  lineOfSign(posX,posY,"upright" ,type));
        list.add(   lineOfSign(posX,posY,"right",type));
        list.add(  lineOfSign(posX,posY,"downright",type));
        list.add(   lineOfSign(posX,posY,"down",type));
        list.add(  lineOfSign(posX,posY,"downleft",type));
        list.add(  lineOfSign(posX,posY,"left",type));
        list.add(  lineOfSign(posX,posY,"upleft",type));

        return Collections.min(list);
    }
    public int lineOfSign(int posX, int posY, String direction,String _type) {
        int nowX = posX;
        int nowY = posY;
        int indicatDirection = 0;
        Entity target = null;
        int distance = 0;
        while ((0 <= nowX && nowX <= n) && (0 <= nowY && nowY <= m)) {
            switch (direction) {
                case "up" -> {
                    nowY++;
                    indicatDirection = 1;
                    distance++;
                }
                case "upright" -> {
                    nowY++;
                    nowX++;
                    indicatDirection = 2;
                    distance++;
                }
                case "right" -> {
                    nowX++;
                    indicatDirection = 3;
                    distance++;
                }
                case "downright" -> {
                    nowY--;
                    nowX++;
                    indicatDirection = 4;
                    distance++;
                }
                case "down" -> {
                    nowY--;
                    indicatDirection = 5;
                    distance++;
                }
                case "downleft" -> {
                    nowY--;
                    nowX--;
                    indicatDirection = 6;
                    distance++;
                }
                case "left" -> {
                    nowX--;
                    indicatDirection = 7;
                    distance++;
                }
                case "upleft" -> {
                    nowY++;
                    nowX--;
                    indicatDirection = 8;
                    distance++;
                }
            }
            if ((0 <= nowX && nowX <= n) && (0 <= nowY && nowY <= m)) {
                if (mapField[nowY][nowX] != null) {
                    if(_type.equals("Entity")){
                        break;
                    }
                    else if(mapField[nowY][nowX].equals(_type)){
                        break;
                    }
                }
            }else break;
        }
        return distance*10 + indicatDirection;
    }
}
