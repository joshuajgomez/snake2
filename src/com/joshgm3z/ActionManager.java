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
        foodX = rand.nextInt(Const.GRID_SIZE);
        foodY = rand.nextInt(Const.GRID_SIZE);
    }

    public void initFood() {
        foodX = 9;
        foodY = 9;
    }

    public void startAction() {
        updateFood();
        int count = 20;
        while (true) {
            int direction = getNextAction();
            action(direction, 1);
        }
    }

    private int getNextAction() {
        BodyPart head = mSnake.get(0);
        printLog(head);
        int action = -1;
        if (head.x == foodX) {
            action = sameHorizontalLine(head);
        } else if (head.y == foodY) {
            action = sameVerticalLine(head);
        } else if (head.x > foodX) {
            action = headBelowFood(head);
        } else {
            action = headAboveFood(head);
        }
        return action;
    }

    // head.x < foodX
    private int headAboveFood(BodyPart head) {
        int action = -1;
        // head is above food
        if (head.y < foodY) {
            // head is top left of food. go right
            if (neck().x == head.x) {
                action = Action.GO_DOWN;
            } else {
                action = Action.GO_RIGHT;
            }
        } else if (head.y > foodY) {
            // head is ahead of food. go left
            BodyPart neck = neck();
            if (neck.x == head.x) {
                action = Action.GO_DOWN;
            } else {
                action = Action.GO_LEFT;
            }
        }
        return action;
    }

    private BodyPart neck() {
        return mSnake.get(1);
    }

    // head.x > foodX
    private int headBelowFood(BodyPart head) {
        int action = -1;
        // head is below food
        if (head.y < foodY) {
            // food is at top right. keep moving right
            if (head.y == 0) {
                action = Action.GO_UP;
            } else {
                action = Action.GO_RIGHT;
            }
        } else if (head.y > foodY) {
            // food is at top left. move left
            if (head.x == Const.GRID_SIZE - 1) {
                if (head.y == Const.GRID_SIZE - 1) {
                    // head is at below right corner.
                    BodyPart neck = neck();
                    if (neck.x == head.x) {
                        action = Action.GO_UP;
                    } else {
                        action = Action.GO_LEFT;
                    }
                } else {
                    action = Action.GO_LEFT;
                }
            } else {
                BodyPart neck = neck();
                if (neck.x == head.x) {
                    action = Action.GO_UP;
                } else {
                    action = Action.GO_LEFT;
                }
            }
        } else {
            // same vertical line
            action = Action.GO_UP;
        }
        return action;
    }

    // head.y == foodY
    private int sameVerticalLine(BodyPart head) {
        int action = -1;
        // same vertical line
        if (head.x - 1 == foodX) {
            // eat up
            action = Action.EAT_UP;
        } else if (head.x + 1 == foodX) {
            // eat down
            action = Action.EAT_DOWN;
        } else if (head.x < foodX) {
            // food is straight below. go down
            action = Action.GO_DOWN;
        } else if (head.x > foodX) {
            // food is behind
            if (head.x == 0) {
                // head is at top
                action = Action.GO_DOWN;
            } else {
                action = Action.GO_UP;
            }
        }
        return action;
    }

    // head.x == foodX
    private int sameHorizontalLine(BodyPart head) {
        int action = -1;
        // same horizontal line
        if (head.y - 1 == foodY) {
            // eat left
            action = Action.EAT_LEFT;
        } else if (head.y + 1 == foodY) {
            // eat right
            action = Action.EAT_RIGHT;
        } else if (head.y < foodY) {
            // food is ahead. keep moving right
            action = Action.GO_RIGHT;
        } else if (head.y > foodY) {
            // food is behind
            if (head.x == Const.GRID_SIZE - 1) {
                // head is at top. go down
                action = Action.GO_DOWN;
            } else {
                action = Action.GO_LEFT;
            }
        }
        return action;
    }

    private void printLog(BodyPart head) {
        System.out.println("head: " + head + ", foodX: " + foodX + ", foodY: " + foodY);
    }

    private void action(int direction, int times) {
        for (int i = 0; i < times; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direction) {
                case Action.EAT_RIGHT:
                    mBodyBuilder.grow(mSnake, Const.Direction.RIGHT);
                    updateFood();
                    break;
                case Action.EAT_LEFT:
                    mBodyBuilder.grow(mSnake, Const.Direction.LEFT);
                    updateFood();
                    break;
                case Action.EAT_DOWN:
                    mBodyBuilder.grow(mSnake, Const.Direction.DOWN);
                    updateFood();
                    break;
                case Action.EAT_UP:
                    mBodyBuilder.grow(mSnake, Const.Direction.UP);
                    updateFood();
                    break;
                case Action.GO_DOWN:
                    mBodyBuilder.go(mSnake, Const.Direction.DOWN);
                    break;
                case Action.GO_UP:
                    mBodyBuilder.go(mSnake, Const.Direction.UP);
                    break;
                case Action.GO_LEFT:
                    mBodyBuilder.go(mSnake, Const.Direction.LEFT);
                    break;
                case Action.GO_RIGHT:
                    mBodyBuilder.go(mSnake, Const.Direction.RIGHT);
                    break;
            }
            printSnake();
        }
    }

    private void printSnake() {
        mOutputManager.printSnake(mSnake, foodX, foodY);
    }
}
