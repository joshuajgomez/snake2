package com.joshgm3z;

import java.util.ArrayList;
import java.util.List;

public class BodyBuilder {

    private BodyPart mNewTail;

    public BodyBuilder() {
    }

    public List<BodyPart> init() {
        List<BodyPart> snake = new ArrayList<>();
        snake = getInit();
        return snake;
    }

    public List<BodyPart> goRight(List<BodyPart> snake) {
        if (!snake.isEmpty()) {
            BodyPart head = snake.get(0);
            BodyPart neck = snake.get(1);
            // horizontal
            if (head.y >= neck.y) {
                // moving right. move one cell
                BodyPart prev = new BodyPart(head);
                head.y = head.y + 1;
                mNewTail = moveBody(snake, prev);
            } else {
                // moving left. do not move
            }
        }
        return snake;
    }

    public List<BodyPart> goDown(List<BodyPart> snake) {
        if (!snake.isEmpty()) {
            BodyPart head = snake.get(0);
            BodyPart neck = snake.get(1);
            if (head.x >= neck.x) {
                // moving down. move one cell
                BodyPart prev = new BodyPart(head);
                head.x = head.x + 1;
                mNewTail = moveBody(snake, prev);
            } else {
                // moving up. do not move
            }
        }
        return snake;
    }

    private List<BodyPart> goLeft(List<BodyPart> snake) {
        if (!snake.isEmpty()) {
            BodyPart head = snake.get(0);
            BodyPart neck = snake.get(1);
            // horizontal
            if (head.y <= neck.y) {
                // moving right. move one cell
                BodyPart prev = new BodyPart(head);
                head.y = head.y - 1;
                mNewTail = moveBody(snake, prev);
            } else {
                // moving left. do not move
            }
        }
        return snake;
    }

    private List<BodyPart> goUp(List<BodyPart> snake) {
        if (!snake.isEmpty()) {
            BodyPart head = snake.get(0);
            BodyPart neck = snake.get(1);
            if (head.x <= neck.x) {
                // moving down. move one cell
                BodyPart prev = new BodyPart(head);
                head.x = head.x - 1;
                mNewTail = moveBody(snake, prev);
            } else {
                // moving up. do not move
            }
        }
        return snake;
    }

    private BodyPart moveBody(List<BodyPart> snake, BodyPart prev) {
        for (int i = 1; i < snake.size(); i++) {
            BodyPart temp = snake.get(i);
            snake.set(i, prev);
            prev = temp;
        }
        return prev;
    }

    public void grow(List<BodyPart> snake, int direction) {
        if (direction == ActionManager.Action.EAT_UP) goUp(snake);
        else if (direction == ActionManager.Action.EAT_DOWN) goDown(snake);
        else if (direction == ActionManager.Action.EAT_RIGHT) goRight(snake);
        else if (direction == ActionManager.Action.EAT_LEFT) goLeft(snake);
        if (mNewTail != null) {
            snake.add(mNewTail);
        } else {
            // invalid tail
        }
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

    public List<BodyPart> go(List<BodyPart> snake, @Const.Direction int direction) {
        if (direction == ActionManager.Action.GO_UP) return goUp(snake);
        else if (direction == ActionManager.Action.GO_DOWN) return goDown(snake);
        else if (direction == ActionManager.Action.GO_RIGHT) return goRight(snake);
        else if (direction == ActionManager.Action.GO_LEFT) return goLeft(snake);
        return null;
    }

}
