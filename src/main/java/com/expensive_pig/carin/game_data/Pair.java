package com.expensive_pig.carin.game_data;

public class Pair {
    protected int X;
    protected int Y;



    public Pair(int X, int Y) {
         this.X = X;
         this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if (o instanceof Pair) {
            Pair p = (Pair) o;
            return p.X == this.X && p.Y == this.Y;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return X*1000000+Y;
    }
}