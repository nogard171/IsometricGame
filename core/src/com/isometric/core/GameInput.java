package com.isometric.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.awt.Point;

public class GameInput implements InputProcessor {
    public static int[] mouseButtonCount;
    private static Point mousePosition = new Point(0,0);
    private static boolean[] mouseButtons;
    private static boolean[] mouseDraggedButtons;
    private static int lastMouseButton = -1;

    private static boolean[] keys;
    private static int[] keysCount;

    public GameInput() {
        poll();
    }

    public static void poll() {
        if (mouseButtons == null) {
            mouseButtons = new boolean[5];
            mouseButtonCount = new int[5];
            mouseDraggedButtons = new boolean[5];
        }
        if (keys == null) {
            keys = new boolean[255];
            keysCount = new int[255];
        }
        for (int m = 0; m < mouseButtons.length; m++) {
            if (mouseButtons[m]) {
                mouseButtonCount[m]++;
            } else {
                mouseButtonCount[m] = 0;
            }
        }
        for (int k = 0; k < keys.length; k++) {
            if (keys[k]) {
                keysCount[k]++;
            } else {
                keysCount[k] = 0;
            }
        }
    }

    public static boolean isMouseDown(int mouseButton) {
        return mouseButtons[mouseButton];
    }

    public static boolean isMousePressed(int mouseButton) {
        return (mouseButtonCount[mouseButton] == 0 ? mouseButtons[mouseButton] : false);
    }

    public static boolean isMouseReleased(int mouseButton) {
        return (mouseButtonCount[mouseButton] > 0 & !mouseButtons[mouseButton] ? true : false);
    }

    public static boolean isMouseDragged(int mouseButton) {
        return mouseDraggedButtons[mouseButton];
    }

    public static int getButtonDown() {
        return lastMouseButton;
    }

    public static Point getMousePosition() {
        return new Point(Gdx.input.getX(),Gdx.input.getY());
    }

    public static Point getInvertMousePosition() {
        return new Point(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
    }

    public static boolean isKeyPressed(int k) {
        return (keysCount[k] == 0 ? keys[k] : false);
    }

    @Override
    public boolean keyDown(int keycode) {
        keys[keycode] = true;
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        keys[keycode] = false;
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mouseButtons[button] = true;
        lastMouseButton = button;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        mouseButtons[button] = false;
        if (lastMouseButton > -1) {
            mouseDraggedButtons[lastMouseButton] = false;
        }
        lastMouseButton = -1;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (lastMouseButton > -1) {
            if (screenX != 0 && screenY != 0) {
                mouseDraggedButtons[lastMouseButton] = true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mousePosition = new Point(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
