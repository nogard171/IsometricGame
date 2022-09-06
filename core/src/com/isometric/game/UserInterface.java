package com.isometric.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.isometric.core.GameInput;
import com.isometric.util.Debug;

public class UserInterface {
    private boolean showDebug = true;
    public Inventory inventory;

    public void setup() {
        inventory = new Inventory();
        inventory.setup();
    }

    public void update() {
        if (GameInput.isKeyPressed(Input.Keys.F1)) {
            showDebug = !showDebug;
        }
        if (GameInput.isKeyPressed(Input.Keys.I)) {
            inventory.toggle();
        }
        if (GameInput.isKeyDown(Input.Keys.NUM_1)) {
            PlayerDatabase.addCopper(123);
        }
        inventory.update();
    }

    public void render(SpriteBatch batch) {
        inventory.render(batch);
        if (showDebug) {
            Debug.render(batch);
        }
    }
}
