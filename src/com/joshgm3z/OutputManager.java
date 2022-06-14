package com.joshgm3z;

import java.io.IOException;
import java.util.List;

public class OutputManager implements SnakeBuilder.BuilderCallback {

    private final String SPACE = " ";
    private final String GRID_SNAKE = SPACE + "O" + SPACE;
    private final String GRID_SNAKE_HEAD = SPACE + "#" + SPACE;
    private final String GRID_EMPTY = SPACE + " " + SPACE;
    private final String GRID_FOOD = SPACE + "X" + SPACE;
    private int GRID_SIZE = Const.GRID_SIZE;
    private boolean isPrintRuler = false;
    private boolean mIsClear = true;

    public OutputManager() {
    }

    @Override
    public void onMovement(List<BodyPart> snake, int foodX, int foodY) {
        clearScreen();
        if (isPrintRuler) printRulerTop();
        for (int x = 0; x < GRID_SIZE; x++) {
            if (isPrintRuler) printRulerSide(x);
            for (int y = 0; y < GRID_SIZE; y++) {
                boolean isBody = false;
                int counter = 0;
                for (BodyPart bodyPart : snake) {
                    if (bodyPart.x == x && bodyPart.y == y) {
                        isBody = true;
                        break;
                    } else {
                        // ignore
                    }
                    counter++;
                }
                if (x == foodX && y == foodY) {
                    // food
                    printCell(GRID_FOOD);
                } else if (isBody) {
                    // snake part found
                    if (counter == 0) {
                        printCell(GRID_SNAKE_HEAD);
                    } else {
                        printCell(GRID_SNAKE);
                    }
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
        System.out.print(SPACE + i + SPACE);
    }

    private void printRulerTop() {
        System.out.print(SPACE + SPACE + SPACE);
        for (int i = 0; i < GRID_SIZE; i++) {
            System.out.print(SPACE + i + SPACE);
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
