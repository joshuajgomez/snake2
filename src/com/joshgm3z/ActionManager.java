package com.joshgm3z;

import java.util.List;
import java.util.Random;

public class ActionManager {

    private BodyBuilder mBodyBuilder;
    private int foodX = -1;
    private int foodY = -1;
    private OutputManager outputManager;
    private List<BodyPart> mSnake;

    public ActionManager() {
        outputManager = new OutputManagerImpl(true);
        mBodyBuilder = new BodyBuilder();
        mSnake = mBodyBuilder.init();
    }

    public void updateFood() {
        Random rand = new Random();
        foodX = rand.nextInt(Const.GRID_SIZE);
        foodY = rand.nextInt(Const.GRID_SIZE);
    }

    public void startAction() {
        updateFood();
        action(Const.Direction.RIGHT, 3);
        action(Const.Direction.DOWN, 2);
        grow(Const.Direction.DOWN);
        action(Const.Direction.DOWN, 2);
        action(Const.Direction.LEFT, 3);
        grow(Const.Direction.LEFT);
    }

    private void grow(int direction) {
        mBodyBuilder.grow(mSnake, direction);
        outputManager.printSnake(mSnake);
    }

    private void action(int direction, int times) {
        for (int i = 0; i < times; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mBodyBuilder.go(mSnake, direction);
            outputManager.printSnake(mSnake);
        }
    }
}
