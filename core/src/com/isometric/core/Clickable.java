package com.isometric.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.awt.Point;

public class Clickable {
    protected Vector2 position = new Vector2(0, 0);
    protected Point size = new Point(0, 0);
    protected Rectangle bounds;
    protected boolean isHovered = false;
    protected boolean isMouseDragged = false;

    public void update() {
        isHovered = false;
        if (bounds != null) {
            Point mouse = GameInput.getInvertMousePosition();
            if (mouse != null) {
                isHovered = this.bounds.contains(new Vector2(mouse.x, mouse.y));
            }
        }
    }

    public Boolean isMouseDown(int button) {
        return (this.isHovered && GameInput.isMouseDown(button) ? true : false);
    }

    public Boolean isMouseDragged(int button) {
        if(this.isHovered && GameInput.isMouseDragged(button)) {
            isMouseDragged=true;
        }
        else if(!GameInput.isMouseDragged(button)) {
            isMouseDragged=false;
        }
        return isMouseDragged;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void setPosition(Vector2 newPosition) {
        this.position = newPosition;
        updateBounds();
    }

    public void updateBounds() {
        this.bounds = new Rectangle(this.position.x, this.position.y, this.size.x, this.size.y);
    }

    public void setSize(int width, int height) {
        this.size = new Point(width, height);
        updateBounds();
    }
}
