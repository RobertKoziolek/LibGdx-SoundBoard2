package com.robcio.soundboard2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.registrar.ScreenRegistrar;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.Maths;

public class SoundBoard2 extends Game {
    public static final int WIDTH = (int) (9 * Maths.PPM);
    public static final int HEIGHT = (int) (16 * Maths.PPM);
    public static final String TITLE = "SoundBoard2";

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ScreenRegistrar screenRegistrar;

    @Override
    public void create() {
        Assets.initialize();
        initializeCamera();
        initializeBatch();
        initializeRegistars();

        setScreen(ScreenId.SPLASH);
    }

    public void setScreen(final ScreenId screenId) {
        setScreen(screenRegistrar.get(screenId));
        setUpInput();
    }

    private void setUpInput() {
        final InputMultiplexer multiplexer = new InputMultiplexer(screenRegistrar.getCurrent()
                                                                                 .getInputs());
        Gdx.input.setInputProcessor(multiplexer);
    }

    private void initializeRegistars() {
        screenRegistrar = new ScreenRegistrar(camera);
    }

    private void initializeCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
    }

    private void initializeBatch() {
        batch = new SpriteBatch();
        batch.enableBlending();
        batch.setProjectionMatrix(camera.combined);
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        screenRegistrar.dispose();
        Assets.dispose();
    }
}
