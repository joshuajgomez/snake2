package com.joshgm3z;

public class ActionManager {

    private BodyBuilder mBodyBuilder;

    public ActionManager(BodyBuilder bodyBuilder) {
        this.mBodyBuilder = bodyBuilder;
    }

    public void startAction() {
        action(mBodyBuilder, Const.Direction.RIGHT, 3);
        action(mBodyBuilder, Const.Direction.DOWN, 2);
        mBodyBuilder.grow(Const.Direction.DOWN);
        action(mBodyBuilder, Const.Direction.DOWN, 2);
        action(mBodyBuilder, Const.Direction.LEFT, 3);
        mBodyBuilder.grow(Const.Direction.LEFT);
//        action(mBodyBuilder, Const.Direction.RIGHT, 2);
//        action(mBodyBuilder, Const.Direction.DOWN, 3);
//        action(mBodyBuilder, Const.Direction.LEFT, 5);
//        action(mBodyBuilder, Const.Direction.UP, 4);
    }

    private void action(BodyBuilder bodyBuilder, int direction, int times) {
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
