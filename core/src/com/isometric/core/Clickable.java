package com.isometric.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.awt.Point;

public class Clickable {
    protected Vector2 size = new Vector2(0, 0);
    protected Rectangle bounds = new Rectangle(0, 0, 0, 0);
    protected boolean isHovered = false;
    protected boolean isMouseDragged = false;

    public Clickable() {}
    public Clickable(float x, float y, int w, int h) {
        this((int) x, (int) y, w, h);
    }

    public Clickable(int x, int y, int w, int h) {
        this.bounds = new Rectangle(x, y, w, h);
    }

    public void update() {
        isHovered = false;
        if (bounds != null) {
            Vector2 mouse = GameInput.getInvertMousePosition();
            if (mouse != null) {
                isHovered = this.bounds.contains(new Vector2(mouse.x, mouse.y));
            }
        }
    }

    public Boolean isMouseDown(int button) {
        return (this.isHovered && GameInput.isMouseDown(button) ? true : false);
    }

    public Boolean isMousePressed(int button) {
        return (this.isHovered && GameInput.isMousePressed(button) ? true : false);
    }

    public Boolean isMouseDragged(int button) {
        if (this.isHovered && GameInput.isMouseDragged(button)) {
            isMouseDragged = true;
        } else if (!GameInput.isMouseDragged(button)) {
            isMouseDragged = false;
        }
        return isMouseDragged;
    }

    public boolean isMouseHovered() {
        return this.isHovered;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public Vector2 getPosition() {
        return new Vector2(this.bounds.getX(), this.bounds.getY());
    }

    public void setPosition(Vector2 newPosition) {
        this.bounds.setPosition(newPosition);
    }

    public void setSize(int width, int height) {
        this.size = new Vector2(width, height);
    }
}
