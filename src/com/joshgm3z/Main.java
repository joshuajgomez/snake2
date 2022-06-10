package com.joshgm3z;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here
        OutputManager outputManager = new OutputManager(true);
        outputManager.printSnake(getSampleSnake());
    }

    private static List<BodyPart> getSampleSnake() {
        List<BodyPart> bodyPart = new ArrayList<>();
        bodyPart.add(new BodyPart(2, 3));
        bodyPart.add(new BodyPart(2, 4));
        bodyPart.add(new BodyPart(2, 5));
        bodyPart.add(new BodyPart(5, 5));
        bodyPart.add(new BodyPart(6, 5));
        bodyPart.add(new BodyPart(6, 5));
        return bodyPart;
    }
}
