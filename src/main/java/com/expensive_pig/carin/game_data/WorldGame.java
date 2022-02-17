package com.expensive_pig.carin.game_data;

import com.expensive_pig.carin.core.Direction;

import com.expensive_pig.carin.entity.EntityType;
import com.expensive_pig.carin.evaluator.SyntaxError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorldGame {
    int m;
    int n;
    EntityType[][] mapField;

    public void setMapSize(int m, int n) {
        this.m = m;
        this.n = n;
        mapField = new EntityType[m][n];
    }

    public boolean slotIsFree(int posX, int posY) {
        return mapField[posY][posX] == null;
    }

    //    public Entity getTarget(int posX,int posY){
////        return mapField[posY][posX];
//    }
    public void addNewEntity(int posX, int posY, EntityType type) {
        mapField[posY][posX] = type;
    }

    public void movePosEntity(int posX, int posY, int toposX, int toposY) throws SyntaxError {
        if (mapField[posY][posX] != null) {
            System.out.println("มีตัวอยู๋");
            throw new SyntaxError();
        }
        mapField[toposY][toposX] = mapField[posY][posX];
        clearPosEntity(posX, posY);
    }

    public void clearPosEntity(int posX, int posY) {
        mapField[posY][posX] = null;
    }

    public int searchNearby(int posX, int posY, EntityType entity, Direction direction) {
        return lineOfSight(posX, posY, direction, entity);
    }

    public int search(int posX, int posY, EntityType type) {
        List<Integer> list = new ArrayList<>();
        list.add(lineOfSight(posX, posY, Direction.UP, type));
        list.add(lineOfSight(posX, posY, Direction.UP_RIGHT, type));
        list.add(lineOfSight(posX, posY, Direction.RIGHT, type));
        list.add(lineOfSight(posX, posY, Direction.DOWN_RIGHT, type));
        list.add(lineOfSight(posX, posY, Direction.DOWN, type));
        list.add(lineOfSight(posX, posY, Direction.DOWN_LEFT, type));
        list.add(lineOfSight(posX, posY, Direction.LEFT, type));
        list.add(lineOfSight(posX, posY, Direction.UP_LEFT, type));

        return Collections.min(list);
    }

    public int lineOfSight(int posX, int posY, Direction direction, EntityType _type) {
        int nowX = posX;
        int nowY = posY;
        int indicateDirection = 0;
        int distance = 0;
        while ((0 <= nowX && nowX <= n) && (0 <= nowY && nowY <= m)) {
            switch (direction) {
                case UP -> {
                    nowY++;
                    indicateDirection = 1;
                    distance++;
                }
                case UP_RIGHT -> {
                    nowY++;
                    nowX++;
                    indicateDirection = 2;
                    distance++;
                }
                case RIGHT -> {
                    nowX++;
                    indicateDirection = 3;
                    distance++;
                }
                case DOWN_RIGHT -> {
                    nowY--;
                    nowX++;
                    indicateDirection = 4;
                    distance++;
                }
                case DOWN -> {
                    nowY--;
                    indicateDirection = 5;
                    distance++;
                }
                case DOWN_LEFT -> {
                    nowY--;
                    nowX--;
                    indicateDirection = 6;
                    distance++;
                }
                case LEFT -> {
                    nowX--;
                    indicateDirection = 7;
                    distance++;
                }
                case UP_LEFT -> {
                    nowY++;
                    nowX--;
                    indicateDirection = 8;
                    distance++;
                }
            }

            if ((0 <= nowX && nowX <= n) && (0 <= nowY && nowY <= m)) {
                if (mapField[nowY][nowX] != null) {
                    if (_type.equals(EntityType.ENTITY)) {
                        break;
                    } else if (mapField[nowY][nowX].equals(_type)) {
                        break;
                    }
                }
            } else break;
        }
        return distance * 10 + indicateDirection;
    }
}
