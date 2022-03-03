package com.expensive_pig.carin.core;

import com.expensive_pig.carin.entity.Entity;
import com.expensive_pig.carin.entity.EntityType;
import com.expensive_pig.carin.game_data.Pair;

import java.util.*;

public class WorldGame {
    private int m;
    private int n;
    private Entity[][] mapField;

    public Set<Pair> freeField = new HashSet<>();

    public WorldGame(int m, int n) {
        this.m = m;
        this.n = n;
        setMapSize();
    }

    private void setMapSize() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                freeField.add(new Pair(n, m));
            }
        }
        mapField = new Entity[m][n];
    }


    public int[] getMapSize() {
        return new int[]{m, n};
    }

    public boolean slotIsFree(int posX, int posY) {
        return mapField[posY][posX] == null;
    }

    public Entity getTarget(int posX, int posY) {
        return mapField[posY][posX];
    }

    public void addNewEntity(int posX, int posY, Entity e) {
        mapField[posY][posX] = e;
        freeField.remove(new Pair(posX, posY));
    }

    public void movePosEntity(int posX, int posY, int toposX, int toposY) {
        if (mapField[toposY][toposX] == null) {
            mapField[toposY][toposX] = mapField[posY][posX];
            clearPosEntity(posX, posY);
            freeField.remove(new Pair(posX, posY));
        }
    }

    public void clearPosEntity(int posX, int posY) {
        mapField[posY][posX] = null;
        freeField.add(new Pair(posX, posY));
    }

    public int searchNearby(int posX, int posY, EntityType entity, Direction direction) {
        return lineOfSight(posX, posY, direction, entity);
    }

    public int search(int posX, int posY, EntityType type) {
        List<Integer> list = new ArrayList<>(8);
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
        while ((0 <= nowX && nowX < n) && (0 <= nowY && nowY < m)) {
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

            if ((0 <= nowX && nowX < n) && (0 <= nowY && nowY < m)) {
                if (mapField[nowY][nowX] != null) {
                    if (_type.equals(EntityType.ENTITY)) {
                        break;
                    } else if (mapField[nowY][nowX].getType().equals(_type)) {
                        break;
                    }
                }
            } else {
                return 0;
            }
        }
        return distance * 10 + indicateDirection;
    }
}
