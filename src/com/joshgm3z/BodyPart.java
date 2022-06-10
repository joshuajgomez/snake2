package com.joshgm3z;

public class BodyPart {
    public int x;
    public int y;

    public BodyPart(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Snake{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
