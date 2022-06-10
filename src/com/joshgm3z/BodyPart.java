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
        return "Snake{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
