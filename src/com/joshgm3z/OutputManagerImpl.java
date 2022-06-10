package com.joshgm3z;

import java.io.IOException;
import java.util.List;

public class OutputManagerImpl implements OutputManager {

    private final String GRID_SNAKE = " O ";
    private final String GRID_EMPTY = " - ";
    private final String GRID_FOOD = " X ";
    private int GRID_SIZE = Const.GRID_SIZE;
    private boolean isPrintRuler;
    private boolean mIsClear = true;

    public OutputManagerImpl(boolean isPrintRuler) {
        this.isPrintRuler = isPrintRuler;
    }

    public void setIsClear(boolean isClear) {
        mIsClear = isClear;
    }

    public void printSnake(List<BodyPart> snake, int foodX, int foodY) {
        clearScreen();
        if (isPrintRuler) printRulerTop();
        for (int x = 0; x < GRID_SIZE; x++) {
            if (isPrintRuler) printRulerSide(x);
            for (int y = 0; y < GRID_SIZE; y++) {
                boolean isBody = false;
                for (BodyPart bodyPart : snake) {
                    if (bodyPart.x == x && bodyPart.y == y) {
                        isBody = true;
                        break;
                    } else {
                        // ignore
                    }
                }
                if (x == foodX && y == foodY) {
                    // food
                    printCell(GRID_FOOD);
                } else if (isBody) {
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

    private void clearScreen() {
        if (mIsClear) {
            try {
                if (System.getProperty("os.name").contains("Windows"))
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                else
                    Runtime.getRuntime().exec("clear");
                System.out.println();
            } catch (IOException | InterruptedException ex) {

            }
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
