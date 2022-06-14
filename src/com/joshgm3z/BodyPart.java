package com.joshgm3z;

public class BodyPart {
    public int x;
    public int y;

    public BodyPart(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public BodyPart(BodyPart bodyPart) {
        this.x = bodyPart.x;
        this.y = bodyPart.y;
    }

    @Override
    public String toString() {
        return "BodyPart{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
