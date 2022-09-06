package com.isometric.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.isometric.game.Database;


public class Panel extends Clickable {
    String[][] data;
    int width;
    int height;
    private String textureName = "panel";

    public Panel(String panelTexture, Vector2 newSize) {
        this.size = newSize;
        this.textureName = panelTexture;

        width = (int)(this.size.x / 2);
        height = (int)(this.size.y / 2);

        data = new String[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                String newData = textureName + "_0,0";
                if (x == 0 && y == 0) {
                    newData = textureName + "_0,2";
                } else if (x == 0 && y < height - 1) {
                    newData = textureName + "_0,1";
                } else if (x == 0 && y == height - 1) {
                    newData = textureName + "_0,0";
                } else if (x < width - 1 && y == 0) {
                    newData = textureName + "_1,2";
                } else if (x < width - 1 && y < height - 1) {
                    newData = textureName + "_1,1";
                } else if (x < width - 1 && y == height - 1) {
                    newData = textureName + "_1,0";
                } else if (x == width - 1 && y == 0) {
                    newData = textureName + "_2,2";
                } else if (x == width - 1 && y < height - 1) {
                    newData = textureName + "_2,1";
                } else if (x == width - 1 && y == height - 1) {
                    newData = textureName + "_2,0";
                }
                data[x][y] = newData;
            }
        }
    }


    public void render(SpriteBatch batch) {
        if (batch != null) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    TextureRegion region = Database.textureData.get(data[x][y]);
                    if (region != null) {
                        batch.draw(region, this.bounds.x + (x * 2), this.bounds.y + (y * 2));
                    }
                }
            }

        }
    }
}
