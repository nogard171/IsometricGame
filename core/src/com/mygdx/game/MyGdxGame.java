package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.isometric.game.MainMenuScreen;

public class MyGdxGame extends Game {

	public Stage stage;
	public Stage uiStage;
	public SpriteBatch batch;
	public SpriteBatch uiBatch;
	public OrthographicCamera camera;
	public OrthographicCamera uiCamera;
	public BitmapFont font;

	public void create() {
		this.batch = new SpriteBatch();
		this.uiBatch = new SpriteBatch();

		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, 800, 600);

		stage = new Stage(new ScreenViewport(this.camera));
		this.uiCamera = new OrthographicCamera();
		this.uiCamera.setToOrtho(false, 800, 600);
		uiStage = new Stage(new ScreenViewport(this.uiCamera));


		this.font = new BitmapFont();
		this.font.setColor(Color.WHITE);

		//this.setScreen(new MainMenuScreen(this));

	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
	}}