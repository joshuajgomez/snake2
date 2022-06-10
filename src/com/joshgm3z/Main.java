package com.joshgm3z;

public class Main {

    public static void main(String[] args) {
        // write your code here
        BodyBuilder bodyBuilder = new BodyBuilder(new OutputManagerImpl(true));
        bodyBuilder.init();

        ActionManager actionManager = new ActionManager(bodyBuilder);
        actionManager.startAction();
    }

}
