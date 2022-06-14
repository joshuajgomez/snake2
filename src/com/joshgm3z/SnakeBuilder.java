package com.joshgm3z;

import com.joshgm3z.worker.ActionManager;

import java.util.List;

public class SnakeBuilder {

    private ActionManager mActionManager;

    public SnakeBuilder(BuilderCallback callback) {
        mActionManager = new ActionManager(callback);
    }

    public void start() {
        mActionManager.startAction();
    }

    public interface BuilderCallback {
        void onMovement(List<BodyPart> snake, int foodX, int foodY);
    }

}
