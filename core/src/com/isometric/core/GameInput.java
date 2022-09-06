package com.isometric.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class GameInput implements InputProcessor {
    public static int[] mouseButtonCount;
    private static Vector2 mousePosition = new Vector2(0, 0);
    private static boolean[] mouseButtons;
    private static boolean[] mouseDraggedButtons;
    private static int lastMouseButton = -1;
    private static boolean[] keys;
    private static int[] keysCount;
    private static Vector2 mouseScroll = new Vector2(0, 0);
    private static int mouseScrollCount = 0;

    public GameInput() {
        poll();
    }

    public static void poll() {
        if (mouseButtons == null) {
            mouseButtons = new boolean[5];
            mouseButtonCount = new int[5];
            mouseDraggedButtons = new boolean[5];
        }
        if (!mouseScroll.equals(new Vector2(0, 0))) {
            mouseScrollCount = 0;
            mouseScroll = new Vector2(0, 0);
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

    public static Vector2 getMousePosition() {
        return new Vector2(Gdx.input.getX(), Gdx.input.getY());
    }

    public static Vector2 getInvertMousePosition() {
        return new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
    }

    public static boolean isKeyPressed(int k) {
        return (keysCount[k] == 0 ? keys[k] : false);
    }

    public static boolean isKeyDown(int k) {
        return keys[k];
    }

    public static Vector2 getMouseScroll() {
        return mouseScroll;
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
        mousePosition = new Vector2(screenX / 2, screenY / 2);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (mouseScrollCount == 0) {
            mouseScroll = new Vector2(amountX, amountY);
            mouseScrollCount++;
        }
        return false;
    }
}
