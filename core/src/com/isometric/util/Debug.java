package com.isometric.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.isometric.game.GameLogic;

import java.util.HashMap;
import java.util.LinkedHashMap;

import jdk.internal.org.jline.utils.Display;

public class Debug {
    private static BitmapFont font;
    private static LinkedHashMap<String, String> data = new LinkedHashMap<>();
    private static GameLogic gameLogic;

    public static void setGame(GameLogic newGame) {
        gameLogic = newGame;
    }

    public static void setDebug(String name, String value) {
        data.put(name, value);
    }

    public static void render(SpriteBatch uiBatch) {
        if (font == null) {
            font = new BitmapFont();
            font.setColor(Color.WHITE);
        }
        int y = 0;
        for (String s : data.keySet()) {
            font.draw(uiBatch, s + ":" + data.get(s), 0, gameLogic.uiCamera.viewportHeight - (y * 15));
            y++;
        }
    }
}
