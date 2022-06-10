package com.joshgm3z;

import java.util.List;

public class OutputManager {

    private final String GRID_SNAKE = " O ";
    private final String GRID_EMPTY = " - ";
    private int GRID_SIZE = Const.GRID_SIZE;
    private boolean isPrintRuler;

    public OutputManager(boolean isPrintRuler) {
        this.isPrintRuler = isPrintRuler;
    }

    public void printSnake(List<BodyPart> snake) {
        if (isPrintRuler) printRulerTop();
        for (int i = 0; i < GRID_SIZE; i++) {
            if (isPrintRuler) printRulerSide(i);
            for (int j = 0; j < GRID_SIZE; j++) {
                boolean isBody = false;
                for (BodyPart bodyPart : snake) {
                    if (bodyPart.x == i && bodyPart.y == j) {
                        isBody = true;
                        break;
                    } else {
                        // ignore
                    }
                }
                if (isBody) {
                    // snake part found
                    printCell(GRID_SNAKE);
                } else {
                    // empty grid
                    printCell(GRID_EMPTY);
                }
            }
            nextLine();
        }
    }

    private void printRulerSide(int i) {
        System.out.print(" " + i + " ");
    }

    private void printRulerTop() {
        System.out.print("   ");
        for (int i = 0; i < GRID_SIZE; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
    }

    private void nextLine() {
        System.out.println();
    }

    private void printCell(String value) {
        System.out.print(value);
    }
}
