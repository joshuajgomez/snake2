package com.joshgm3z;

import java.util.ArrayList;
import java.util.List;

public class BodyBuilder {

    private List<BodyPart> mSnake;
    private OutputManager mOutputManager;

    public BodyBuilder(OutputManager outputManager) {
        this.mOutputManager = outputManager;
    }

    public void init() {
        mSnake = new ArrayList<>();
        mSnake = getInit();
        printSnake();
    }

    public BodyPart goRight() {
        BodyPart newTail = null;
        if (!mSnake.isEmpty()) {
            BodyPart head = mSnake.get(0);
            BodyPart neck = mSnake.get(1);
            // horizontal
            if (head.y >= neck.y) {
                // moving right. move one cell
                BodyPart prev = new BodyPart(head);
                head.y = head.y + 1;
                newTail = moveBody(prev);
            } else {
                // moving left. do not move
            }
        }
        return newTail;
    }

    public BodyPart goDown() {
        BodyPart newTail = null;
        if (!mSnake.isEmpty()) {
            BodyPart head = mSnake.get(0);
            BodyPart neck = mSnake.get(1);
            if (head.x >= neck.x) {
                // moving down. move one cell
                BodyPart prev = new BodyPart(head);
                head.x = head.x + 1;
                newTail = moveBody(prev);
            } else {
                // moving up. do not move
            }
        }
        return newTail;
    }

    private BodyPart goLeft() {
        BodyPart newTail = null;
        if (!mSnake.isEmpty()) {
            BodyPart head = mSnake.get(0);
            BodyPart neck = mSnake.get(1);
            // horizontal
            if (head.y <= neck.y) {
                // moving right. move one cell
                BodyPart prev = new BodyPart(head);
                head.y = head.y - 1;
                newTail = moveBody(prev);
            } else {
                // moving left. do not move
            }
        }
        return newTail;
    }

    private BodyPart goUp() {
        BodyPart newTail = null;
        if (!mSnake.isEmpty()) {
            BodyPart head = mSnake.get(0);
            BodyPart neck = mSnake.get(1);
            if (head.x <= neck.x) {
                // moving down. move one cell
                BodyPart prev = new BodyPart(head);
                head.x = head.x - 1;
                newTail = moveBody(prev);
            } else {
                // moving up. do not move
            }
        }
        return newTail;
    }

    private BodyPart moveBody(BodyPart prev) {
        for (int i = 1; i < mSnake.size(); i++) {
            BodyPart temp = mSnake.get(i);
            mSnake.set(i, prev);
            prev = temp;
        }
        return prev;
    }

    public void grow(int direction) {
        BodyPart tail = null;
        if (direction == Const.Direction.UP) tail = goUp();
        else if (direction == Const.Direction.DOWN) tail = goDown();
        else if (direction == Const.Direction.RIGHT) tail = goRight();
        else if (direction == Const.Direction.LEFT) tail = goLeft();
        if (tail != null) {
            mSnake.add(tail);
        } else {
            // invalid tail
        }
        printSnake();
    }

    private void printSnake() {
        mOutputManager.printGrid(mSnake);
        System.out.println("snake : " + mSnake);
    }

    private List<BodyPart> getInit() {
        List<BodyPart> bodyPart = new ArrayList<>();
        bodyPart.add(new BodyPart(1, 3));
        bodyPart.add(new BodyPart(1, 2));
        bodyPart.add(new BodyPart(1, 1));
        return bodyPart;
    }

    public List<BodyPart> getSample() {
        List<BodyPart> bodyPart = new ArrayList<>();
        bodyPart.add(new BodyPart(2, 3));
        bodyPart.add(new BodyPart(2, 4));
        bodyPart.add(new BodyPart(2, 5));
        bodyPart.add(new BodyPart(3, 5));
        bodyPart.add(new BodyPart(4, 5));
        bodyPart.add(new BodyPart(5, 5));
        bodyPart.add(new BodyPart(5, 6));
        bodyPart.add(new BodyPart(5, 7));
        bodyPart.add(new BodyPart(4, 7));
        return bodyPart;
    }

    public void go(@Const.Direction int direction) {
        if (direction == Const.Direction.UP) goUp();
        else if (direction == Const.Direction.DOWN) goDown();
        else if (direction == Const.Direction.RIGHT) goRight();
        else if (direction == Const.Direction.LEFT) goLeft();
        printSnake();
    }

}
