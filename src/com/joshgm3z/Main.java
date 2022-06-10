package com.joshgm3z;

public class Main {

    public static void main(String[] args) {
        // write your code here
        BodyBuilder bodyBuilder = new BodyBuilder(
                new OutputManagerImpl(true));
        bodyBuilder.init();

        startAction(bodyBuilder);
    }

    private static void startAction(BodyBuilder bodyBuilder) {
        move(bodyBuilder, Const.Direction.RIGHT, 3);
        move(bodyBuilder, Const.Direction.DOWN, 4);
        move(bodyBuilder, Const.Direction.RIGHT, 2);
        move(bodyBuilder, Const.Direction.DOWN, 3);
        move(bodyBuilder, Const.Direction.LEFT, 5);
        move(bodyBuilder, Const.Direction.UP, 4);
    }

    private static void move(BodyBuilder bodyBuilder, int direction, int times) {
        for (int i = 0; i < times; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bodyBuilder.go(direction);
        }
    }


}
