package com.isometric.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.isometric.game.Database;

import java.awt.Point;
import java.util.HashMap;

public class Panel {
    public static Texture panelMap;
    public static HashMap<Point, TextureRegion> panelTextureData = new HashMap<Point, TextureRegion>();
    private Vector2 position;
    private Point size = new Point(0, 0);

    public void Panel(String panelTexture, Point newSize) {
        this.size = newSize;
        panelMap = new Texture(panelTexture);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                panelTextureData.put(new Point(x, y), new TextureRegion(panelMap, x * 3, y * 3, 3, 3));
            }
        }
    }

    public void setPosition(Vector2 newPosition) {
        this.position = newPosition;
    }

    public void render(SpriteBatch batch) {
        //for (int x = 0; x < 10; x++) {
            //for (int y = 0; y < 10; y++) {
                batch.draw(panelTextureData.get(new Point(0, 0)), this.position.x, this.position.y);
            //}
       // }
    }
}
