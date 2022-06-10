package com.joshgm3z;

import java.util.List;
import java.util.Random;

public class ActionManager {

    private BodyBuilder mBodyBuilder;
    private int foodX = -1;
    private int foodY = -1;
    private OutputManager mOutputManager;
    private List<BodyPart> mSnake;

    @interface Action {
        int GO_LEFT = 0;
        int GO_RIGHT = 1;
        int GO_DOWN = 2;
        int GO_UP = 3;
        int GO_UP_RIGHT = 4;
        int GO_UP_LEFT = 5;
        int GO_DOWN_RIGHT = 6;
        int GO_DOWN_LEFT = 7;
        int GO_RIGHT_UP = 8;
        int GO_RIGHT_DOWN = 9;
        int GO_LEFT_UP = 10;
        int GO_LEFT_DOWN = 11;
        int EAT_LEFT = 12;
        int EAT_RIGHT = 13;
        int EAT_DOWN = 14;
        int EAT_UP = 15;
    }

    public ActionManager() {
        mOutputManager = new OutputManagerImpl(true);
        mBodyBuilder = new BodyBuilder();
        mSnake = mBodyBuilder.init();
        printSnake();

    }

    public void updateFood() {
        Random rand = new Random();
//        foodX = rand.nextInt(Const.GRID_SIZE);
//        foodY = rand.nextInt(Const.GRID_SIZE);
        foodX = 5;
        foodY = 5;
    }

    public void startAction() {
        updateFood();
        int count = 10;
        while (count-- > 0) {
            int direction = getNextAction();
            action(direction, 1);
        }
    }

    public void mockAction() {
        action(Const.Direction.RIGHT, 3);
        action(Const.Direction.DOWN, 2);
        grow(Const.Direction.DOWN);
        action(Const.Direction.DOWN, 2);
        action(Const.Direction.LEFT, 3);
        grow(Const.Direction.LEFT);


    }

    private int getNextAction() {
        BodyPart head = mSnake.get(0);
        int direction = -1;
        if (head.x == foodX) {
            // same horizontal line
            if (head.y - 1 == foodY) {
                // eat left
                direction = Action.EAT_LEFT;
            } else if (head.y + 1 == foodY) {
                // eat right
                direction = Action.EAT_RIGHT;
            } else if (head.y < foodY) {
                // food is ahead. keep moving right
                direction = Const.Direction.RIGHT;
            } else if (head.y > foodY) {
                // food is behind
                if (head.x == Const.GRID_SIZE - 1) {
                    // head is at top. go down
                    direction = Const.Direction.DOWN;
                } else {
                    direction = Const.Direction.UP;
                }
            }
        } else if (head.y == foodY) {
            // same vertical line
            if (head.x - 1 == foodX) {
                // eat up
                direction = Action.EAT_UP;
            } else if (head.x + 1 == foodX) {
                // eat down
                direction = Action.EAT_DOWN;
            } else if (head.x < foodX) {
                // food is straight below. go down
                direction = Const.Direction.DOWN;
            } else if (head.x > foodX) {
                // food is behind
                if (head.x == Const.GRID_SIZE - 1) {
                    // head is at top. go down
                    direction = Const.Direction.DOWN;
                } else {
                    direction = Const.Direction.UP;
                }
            }
        } else if (head.x > foodX) {
            // head is below food
            if (head.y < foodY) {
                // food is at top right. keep moving right
                direction = Const.Direction.RIGHT;
            } else if (head.y > foodY) {
                // food is at top left. move left
                if (head.y == Const.GRID_SIZE - 1) {
                    // head is at top. go down
                    direction = Const.Direction.RIGHT;
                } else {
                    direction = Const.Direction.LEFT;
                }
            }
        } else if (head.x < foodX) {
            // head is above food
            if (head.y < foodY) {
                // head is behind food. go right
                direction = Const.Direction.RIGHT;
            } else if (head.y > foodY) {
                // head is ahead of food. go left
                direction = Const.Direction.LEFT;
            }
        }
        return direction;
    }

    private void grow(int direction) {
        mBodyBuilder.grow(mSnake, direction);
        printSnake();
    }

    private void action(int direction, int times) {
        for (int i = 0; i < times; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mBodyBuilder.go(mSnake, direction);
            printSnake();
        }
    }

    private void printSnake() {
        mOutputManager.printSnake(mSnake, foodX, foodY);
    }
}
