package com.isometric.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.HdpiMode;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.isometric.game.Database;

import java.awt.Point;
import java.util.HashMap;

public class Panel extends Clickable {
    FrameBuffer fbo;
    String[][] data;
    int width;
    int height;
    private String textureName = "panel";

    public Panel(String panelTexture, Point newSize) {
        this.size = newSize;
        this.textureName = panelTexture;
        super.updateBounds();

        width = this.size.x / 2;
        height = this.size.y / 2;

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

    @Override
    public void updateBounds() {
        this.bounds = new Rectangle(this.position.x, this.position.y, this.size.x, this.size.y);
    }


    public void render(SpriteBatch batch) {
        if (batch != null) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    TextureRegion region = Database.textureData.get(data[x][y]);
                    if (region != null) {
                        batch.draw(region, this.position.x + (x * 2), this.position.y + (y * 2));
                    }
                }
            }

        }
    }
}
