package com.joshgm3z;

import java.io.IOException;
import java.util.List;

public class OutputManagerImpl implements OutputManager {

    private final String GRID_SNAKE = " O ";
    private final String GRID_EMPTY = " - ";
    private int GRID_SIZE = Const.GRID_SIZE;
    private boolean isPrintRuler;
    private boolean mIsClear = true;

    public OutputManagerImpl(boolean isPrintRuler) {
        this.isPrintRuler = isPrintRuler;
    }

    public void setIsClear(boolean isClear) {
        mIsClear = isClear;
    }

    public void printGrid(List<BodyPart> snake) {
        clearScreen();
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
