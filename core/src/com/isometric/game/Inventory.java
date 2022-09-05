package com.isometric.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.isometric.core.GameInput;
import com.isometric.core.ItemSlot;
import com.isometric.core.Panel;

import java.awt.Point;
import java.util.HashMap;

public class Inventory {
    private static BitmapFont font;
    public Vector2 position = new Vector2(0, 0);
    Panel background;
    Panel titleBackground;
    Panel closeBackground;
    Point dragMousePosition;
    Vector2 offset;
    boolean moved = true;
    Texture tex;
    private HashMap<Point, ItemSlot> itemSlots = new HashMap<>();

    public void setup() {
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        background = new Panel("panel", new Point(256, 234));
        titleBackground = new Panel("panel", new Point(100, 25));
        titleBackground.setPosition(new Vector2(position.x, position.y + background.getBounds().height - 1));
        closeBackground = new Panel("red", new Point(20, 20));
        closeBackground.setPosition(new Vector2(position.x + background.getBounds().width , position.y + background.getBounds().height));

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                ItemSlot slot = new ItemSlot();
                slot.setPosition(
                        new Vector2(this.position.x
                                + (x * 37) + 10, this.position.y
                                + (y * 36) + 8)
                );
                itemSlots.put(new Point(x, y), slot);
            }
        }
        tex = new Texture("user interface/inventory_panel.png");
    }

    public void update() {
        //background.update();
        //titleBackground.update();
        //closeBackground.update();
        if (titleBackground.isMouseDragged(Input.Buttons.LEFT)) {
            System.out.println("Drag");
            Point mouse = GameInput.getInvertMousePosition();
            if (offset == null) {
                offset = new Vector2(position.x - mouse.x, position.y - mouse.y);
            }
            position = new Vector2(Math.max(0, Math.min(Gdx.graphics.getWidth() - tex.getWidth() - (closeBackground.getBounds().width / 2), mouse.x + offset.x)), Math.max(0, Math.min(Gdx.graphics.getHeight() - tex.getHeight()- titleBackground.getBounds().height, mouse.y + offset.y)));
            moved = true;
        } else {
            offset = null;
        }
        if (moved) {
            background.setPosition(position);
            titleBackground.setPosition(new Vector2(position.x, position.y + background.getBounds().height - 3));
            closeBackground.setPosition(new Vector2(position.x + background.getBounds().width - 10, position.y + background.getBounds().height - 10));
            for (ItemSlot slot : itemSlots.values()) {
                slot.update(position);
            }
            moved = false;
        }
    }

    public void render(SpriteBatch batch) {
        //background.render(batch);titleBackground.render(batch);
        batch.draw(tex, this.position.x, this.position.y);
        font.setColor(Color.WHITE);
        font.draw(batch, "Inventory", titleBackground.getPosition().x + 5, titleBackground.getPosition().y + 18);
        //closeBackground.render(batch);
        font.draw(batch, "X", closeBackground.getPosition().x + 3, closeBackground.getPosition().y + 15);
/*
        for (ItemSlot slot : itemSlots.values()) {
            slot.render(batch);
        }*/

    }
}
