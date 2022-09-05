package com.isometric.core;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.awt.Point;

public class ItemSlot {
    Vector2 position = new Vector2(0, 0);
    Panel itemBackground;
    private String itemName = "";
    private int itemCount = 0;

    public ItemSlot() {
        itemBackground = new Panel("icon", new Point(32, 32));
    }

    public void setPosition(Vector2 newPosition) {
        this.position = newPosition;
    }

    public void setItem(String name, int count) {

    }

    public void update(Vector2 parentPosition) {
        itemBackground.setPosition(new Vector2(this.position.x + parentPosition.x, this.position.y + parentPosition.y));
        itemBackground.update();
        if (itemBackground.isMouseDown(Input.Buttons.LEFT)) {
            System.out.println("Slot Click");
        }
    }

    public void render(SpriteBatch batch) {
        itemBackground.render(batch);
    }
}
