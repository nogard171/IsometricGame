package com.isometric.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.isometric.core.GameInput;
import com.isometric.core.Panel;

import java.awt.Point;

public class Inventory {
    private static BitmapFont font;
    public Vector2 position = new Vector2(0, 0);
    Panel background;
    Panel titleBackground;
    Panel closeBackground;
    Point dragMousePosition;
    Vector2 offset;

    public void setup() {
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        background = new Panel("user interface/panel_background.png", new Point(150, 300));
        titleBackground = new Panel("user interface/panel_background.png", new Point(100, 25));
        titleBackground.setPosition(new Vector2(position.x, position.y + background.getBounds().height - 1));
        closeBackground = new Panel("user interface/red_background.png", new Point(20, 20));
        closeBackground.setPosition(new Vector2(position.x + background.getBounds().width - 10, position.y + background.getBounds().height - 10));
    }

    public void update() {
        background.update();
        titleBackground.update();
        closeBackground.update();
        if (titleBackground.isMouseDragged(Input.Buttons.LEFT)) {
            System.out.println("Drag");
            Point mouse = GameInput.getInvertMousePosition();
            if (offset == null) {
                offset = new Vector2(position.x - mouse.x, position.y - mouse.y);
            }
            position = new Vector2(Math.max(0, Math.min(Gdx.graphics.getWidth() - background.getBounds().width - (closeBackground.getBounds().width / 2), mouse.x + offset.x)), Math.max(0, Math.min(Gdx.graphics.getHeight() - background.getBounds().height - titleBackground.getBounds().height, mouse.y + offset.y)));
        }
        background.setPosition(position);
        titleBackground.setPosition(new Vector2(position.x, position.y + background.getBounds().height - 1));
        closeBackground.setPosition(new Vector2(position.x + background.getBounds().width - 10, position.y + background.getBounds().height - 10));
    }

    public void render(SpriteBatch batch) {
        background.render(batch);
        titleBackground.render(batch);

        font.setColor(Color.WHITE);
        font.draw(batch, "Inventory", titleBackground.getPosition().x + 5, titleBackground.getPosition().y + 18);
        closeBackground.render(batch);
        font.draw(batch, "X", closeBackground.getPosition().x + 3, closeBackground.getPosition().y + 15);

    }
}
