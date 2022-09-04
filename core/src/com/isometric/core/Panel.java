package com.isometric.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.isometric.game.Database;

import java.awt.Point;
import java.util.HashMap;

public class Panel extends Clickable {
    public  Texture panelMap;
    public  HashMap<Point, TextureRegion> panelTextureData = new HashMap<Point, TextureRegion>();


    public Panel(String panelTexture, Point newSize) {
        this.size = newSize;
        panelMap = new Texture(panelTexture);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                panelTextureData.put(new Point(x, y), new TextureRegion(panelMap, x * 3, y * 3, 3, 3));
            }
        }
        super.updateBounds();
    }

    @Override
    public void updateBounds() {
        this.bounds = new Rectangle(this.position.x, this.position.y, this.size.x, this.size.y);
    }


    public void render(SpriteBatch batch) {
        super.update();
        if (batch != null) {
            if (panelTextureData != null) {
                int width = this.size.x / 3;
                int height = this.size.y / 3;
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {

                        TextureRegion region = panelTextureData.get(new Point(0, 0));
                        if (x == 0 && y == 0) {
                            region = panelTextureData.get(new Point(0, 2));
                        } else if (x == 0 && y < height - 1) {
                            region = panelTextureData.get(new Point(0, 1));
                        } else if (x == 0 && y == height - 1) {
                            region = panelTextureData.get(new Point(0, 0));
                        } else if (x < width - 1 && y == 0) {
                            region = panelTextureData.get(new Point(1, 2));
                        } else if (x < width - 1 && y < height - 1) {
                            region = panelTextureData.get(new Point(1, 1));
                        } else if (x < width - 1 && y == height - 1) {
                            region = panelTextureData.get(new Point(1, 0));
                        } else if (x == width - 1 && y == 0) {
                            region = panelTextureData.get(new Point(2, 2));
                        } else if (x == width - 1 && y < height - 1) {
                            region = panelTextureData.get(new Point(2, 1));
                        } else if (x == width - 1 && y == height - 1) {
                            region = panelTextureData.get(new Point(2, 0));
                        }
                        if (region != null) {
                            batch.draw(region, super.position.x + (x * 3), this.position.y + (y * 3));
                        }
                    }
                }
            }
        }
    }
}
