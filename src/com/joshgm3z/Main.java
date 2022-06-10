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
        int times = 3;
        for (int i = 0; i < times; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bodyBuilder.goRight();
        }
    }


}
