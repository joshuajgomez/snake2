package com.joshgm3z;

import java.util.List;
import java.util.Random;

public class ActionManager {

    private BodyBuilder mBodyBuilder;
    private int foodX = -1;
    private int foodY = -1;
    private OutputManager mOutputManager;
    private List<BodyPart> mSnake;
    private final long ANIMATION_DELAY = 500;
    private boolean isInfinite = true;
    private final int LOOP_COUNT = 10;

    public @interface Action {
        int GO_LEFT = 0;
        int GO_RIGHT = 1;
        int GO_DOWN = 2;
        int GO_UP = 3;
        int EAT_LEFT = 4;
        int EAT_RIGHT = 5;
        int EAT_DOWN = 6;
        int EAT_UP = 7;
    }

    public ActionManager() {
        mOutputManager = new OutputManagerImpl();
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
        foodX = 7;
        foodY = 4;
    }

    public void startAction() {
        initFood();
        int count = LOOP_COUNT;
        while (isInfinite || count-- > 0) {
            int direction = getNextAction();
            action(direction);
        }
    }

    private int getNextAction() {
        BodyPart head = head();
        printLog(head);
        int action;
        if (head.x == foodX) {
            action = sameHorizontalLine();
        } else if (head.y == foodY) {
            action = sameVerticalLine();
        } else if (head.x > foodX) {
            action = headBelowFood();
        } else {
            action = headAboveFood();
        }
        return action;
    }

    // head.x < foodX
    private int headAboveFood() {
        BodyPart head = head();
        int action = -1;
        // head is above food
        if (head.y < foodY) {
            // head is top left of food. go right
            if (neck().x == head.x && neck().y > head.y) {
                action = Action.GO_DOWN;
            } else {
                action = Action.GO_RIGHT;
            }
        } else if (head.y > foodY) {
            // head is ahead of food. go left
            BodyPart neck = neck();
            if (neck.x == head.x && neck().y < head.y) {
                action = Action.GO_DOWN;
            } else {
                action = Action.GO_LEFT;
            }
        }
        return action;
    }

    private BodyPart head() {
        return mSnake.get(0);
    }

    private BodyPart neck() {
        return mSnake.get(1);
    }

    // head.x > foodX
    private int headBelowFood() {
        BodyPart head = head();
        int action = -1;
        // head is below food
        if (head.y < foodY) {
            // food is at top right. keep moving right
            if (head.y == 0) {
                action = Action.GO_UP;
            } else {
                if (neck().x == head.x && neck().y > head.y) {
                    action = Action.GO_UP;
                } else {
                    action = Action.GO_RIGHT;
                }
            }
        } else if (head.y > foodY) {
            // food is at top left. move left
            if (head.x == Const.GRID_SIZE - 1) {
                if (head.y == Const.GRID_SIZE - 1) {
                    // head is at below right corner.
                    BodyPart neck = neck();
                    if (neck.x == head.x && neck.y < head.y) {
                        action = Action.GO_UP;
                    } else {
                        action = Action.GO_LEFT;
                    }
                } else {
                    action = Action.GO_LEFT;
                }
            } else {
                BodyPart neck = neck();
                if (neck.x == head.x && neck.y < head.y) {
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
    private int sameVerticalLine() {
        int action = -1;
        BodyPart head = head();
        // same vertical line
        if (head.x - 1 == foodX) {
            // eat up
            action = Action.EAT_UP;
        } else if (head.x + 1 == foodX) {
            // eat down
            action = Action.EAT_DOWN;
        } else if (head.x < foodX) {
            // food is straight below. go down
            if (neck().y == head.y && neck().x > head.x) {
                if (head.y == 0) {
                    action = Action.GO_RIGHT;
                } else {
                    action = Action.GO_LEFT;
                }
            } else {
                action = Action.GO_DOWN;
            }
        } else if (head.x > foodX) {
            if (head.x == 0) {
                // head is at top
                action = Action.GO_DOWN;
            } else {
                if (neck().y == head.y && neck().x < head.x) {
                    if (head.y == 0) {
                        action = Action.GO_RIGHT;
                    } else {
                        action = Action.GO_LEFT;
                    }
                } else {
                    action = Action.GO_UP;
                }
            }
        }
        return action;
    }

    // head.x == foodX
    private int sameHorizontalLine() {
        int action = -1;
        BodyPart head = head();
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
        System.out.println("Snake length: " + mSnake.size() + "\t\thead: " + head + "\t\tfoodX: " + foodX + "\tfoodY: " + foodY);
    }

    private void action(int direction) {
        try {
            Thread.sleep(ANIMATION_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (direction) {
            case Action.EAT_RIGHT:
            case Action.EAT_LEFT:
            case Action.EAT_UP:
            case Action.EAT_DOWN:
                mBodyBuilder.grow(mSnake, direction);
                updateFood();
                break;
            case Action.GO_DOWN:
            case Action.GO_UP:
            case Action.GO_LEFT:
            case Action.GO_RIGHT:
                mBodyBuilder.go(mSnake, direction);
                break;
        }
        printSnake();
    }

    private void printSnake() {
        mOutputManager.printSnake(mSnake, foodX, foodY);
    }
}
