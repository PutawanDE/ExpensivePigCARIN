package com.expensive_pig.carin.core;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    UP("up"), UP_RIGHT("upright"), RIGHT("right"), DOWN_RIGHT("downright"),
    DOWN("down"), DOWN_LEFT("downleft"), LEFT("left"), UP_LEFT("upleft");

    private final String dirLabel;
    private static final Map<String, Direction> DIRECTION_MAP = new HashMap<>();

    Direction(String dirLabel) {
        this.dirLabel = dirLabel;
    }

    @Override
    public String toString() {
        return dirLabel;
    }

    static {
        DIRECTION_MAP.put(UP.dirLabel, UP);
        DIRECTION_MAP.put(UP_RIGHT.dirLabel, UP_RIGHT);
        DIRECTION_MAP.put(RIGHT.dirLabel, RIGHT);
        DIRECTION_MAP.put(DOWN_RIGHT.dirLabel, DOWN_RIGHT);
        DIRECTION_MAP.put(DOWN.dirLabel, DOWN);
        DIRECTION_MAP.put(DOWN_LEFT.dirLabel, DOWN_LEFT);
        DIRECTION_MAP.put(LEFT.dirLabel, LEFT);
        DIRECTION_MAP.put(UP_LEFT.dirLabel, UP_LEFT);
    }

    public static Direction getDirectionByString(String direction) {
        return DIRECTION_MAP.getOrDefault(direction, null);
    }
}
