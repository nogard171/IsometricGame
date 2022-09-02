package com.isometric.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.isometric.core.GameInput;
import com.isometric.util.Debug;

public class UserInterface {
    private boolean showDebug = false;

    public void setup() {

    }

    public void update() {
        if (GameInput.isKeyPressed(Input.Keys.F1)) {
            showDebug = !showDebug;
        }
    }

    public void render(SpriteBatch batch) {
        if (showDebug) {
            Debug.render(batch);
        }
    }
}
