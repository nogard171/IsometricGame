package com.isometric.core;

import com.badlogic.gdx.math.Vector2;

public class Object {
    private String texture = "air";
    private Vector2 tilePosition;

    public Vector2 getTilePosition() {
        return tilePosition;
    }

    public void setTilePosition(Vector2 tilePosition) {
        this.tilePosition = tilePosition;
    }


    public void setTexture(String newTexture) {
        this.texture = newTexture;
    }

    public String getTexture() {
        return this.texture;
    }
}
