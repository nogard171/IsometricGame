package com.isometric.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.isometric.core.ChunkManager;
import com.isometric.core.GameInput;
import com.isometric.core.Panel;
import com.isometric.util.Debug;

import java.awt.Point;

public class GameScreen implements Screen {
    final GameLogic game;
    private final BitmapFont font;
    public Vector2 hoverIndex;
    public Vector2 hoverChunkIndex;
    public Vector2 mousePosition;
    public Vector2 viewChunkIndex = new Vector2(15, 29);
    boolean initChunkIndex = false;
    ChunkManager chunkMgr;
    ShapeRenderer shape;
    private UserInterface ui;

    public GameScreen(final GameLogic game) {
        this.game = game;
        Debug.setGame(this.game);
        GameInput inputProcessor = new GameInput();
        Gdx.input.setInputProcessor(inputProcessor);

        ui = new UserInterface();
        ui.setup();

        Database.load();


        font = new BitmapFont();
        font.setColor(Color.WHITE);

        chunkMgr = new ChunkManager();
        chunkMgr.setup();
    }

    @Override
    public void resize(int width, int height) {
        game.camera.viewportWidth = width;
        game.camera.viewportHeight = height;
        checkInitIndex();
        game.camera.update();
        game.uiCamera.viewportWidth = width;
        game.uiCamera.viewportHeight = height;
        game.uiCamera.position.x = width / 2;
        game.uiCamera.position.y = height / 2;
        game.uiCamera.update();
        checkInitIndex();
    }

    public void update(float delta) {
        float speed = 1 * delta;
        speed *= 1000;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.game.camera.translate(new Vector2(-speed, 0));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.game.camera.translate(new Vector2(speed, 0));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.game.camera.translate(new Vector2(0, speed));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.game.camera.translate(new Vector2(0, -speed));
        }
        game.camera.update();
        chunkMgr.update(viewChunkIndex);
        ui.update();


        Debug.setDebug("FPS", Gdx.graphics.getFramesPerSecond() + "");
        Debug.setDebug("Mouse", getMousePosInGameWorld() + "");
        Debug.setDebug("Index", hoverIndex + "");
        Debug.setDebug("Chunk Index", hoverChunkIndex + "");
        GameInput.poll();
    }


    @Override
    public void render(float delta) {
        getHover();

        update(delta);
        ScreenUtils.clear(0.4f, 0.6f, 0.9f, 1);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        chunkMgr.render(game.batch, viewChunkIndex);
        if (hoverIndex != null) {
            float posX = (((hoverIndex.x - hoverIndex.y) * 32));
            float posY = ((hoverIndex.y + hoverIndex.x) * 16);
            game.batch.draw(Database.textures.get("hover"), posX - 32, posY);
        }
        game.batch.end();
        game.uiBatch.setProjectionMatrix(game.uiCamera.combined);
        game.uiBatch.begin();
        ui.render(game.uiBatch);
        game.uiBatch.end();

    }


    @Override
    public void show() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        Database.cleanup();
    }

    Vector3 getMousePosInGameWorld() {
        Point mouse = GameInput.getMousePosition();
        if(mouse!=null) {
            return game.camera.unproject(new Vector3(mouse.x, mouse.y, 0));
        }
        return new Vector3(-1,-1,-1);
    }

    private void getHover() {
        mousePosition = new Vector2(getMousePosInGameWorld().x, getMousePosInGameWorld().y);
        int cartX = (int) mousePosition.x;
        int cartY = (int) mousePosition.y;
        int isoX = ((cartX / 2) + (cartY));
        int isoY = (cartY - cartX / 2);

        int indexX = (int) Math.floor((float) isoX / (float) 32);
        int indexY = (int) Math.floor((float) isoY / (float) 32);
        hoverIndex = new Vector2(indexX, indexY);
        hoverChunkIndex = new Vector2((int) Math.floor(indexX / 16), (int) Math.floor(indexY / 16));
        getViewIndex();

    }

    private void checkInitIndex() {
        if (viewChunkIndex != null && !initChunkIndex) {
            Vector2 size = new Vector2((16) * viewChunkIndex.x, (16) * viewChunkIndex.y);
            float posX = (((size.x - size.y) * 32));
            float posY = ((size.y + size.x) * 16);
            game.camera.position.x = posX;
            game.camera.position.y = posY;
            initChunkIndex = true;
        }
    }

    private void getViewIndex() {
        checkInitIndex();
        Vector2 tempPosition = new Vector2(game.camera.position.x / 16, game.camera.position.y / 16);
        int cartX = (int) tempPosition.x;
        int cartY = (int) tempPosition.y;
        int isoX = ((cartX / 2) + (cartY));
        int isoY = (cartY - cartX / 2);

        int indexX = (int) Math.floor((float) isoX / (float) 32);
        int indexY = (int) Math.floor((float) isoY / (float) 32);
        viewChunkIndex = new Vector2((int) Math.floor(indexX), (int) Math.floor(indexY));
    }
}