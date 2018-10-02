package com.robcio.soundboard2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.utils.*;
import com.robcio.soundboard2.voice.loader.VoiceLoader;
import com.robcio.soundboard2.registrar.ScreenRegistrar;

public class SoundBoard2 extends Game implements ScreenChanger {
    public static final int WIDTH = (int) (9 * Maths.PPM);
    public static final int HEIGHT = (int) (16 * Maths.PPM);
    public static final String TITLE = "SoundBoard2";

    private final ShareDispatcher shareDispatcher;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ScreenRegistrar screenRegistrar;

    public SoundBoard2(final ShareDispatcher shareDispatcher){
        this.shareDispatcher = shareDispatcher;
    }

    @Override
    public void create() {
        Assets.initialize();
        initializeCamera();
        initializeBatch();

        final VoiceLoader voiceLoader = new VoiceLoader();
        voiceLoader.load();

        initializeRegistrars(voiceLoader);
        setScreen(ScreenId.LOAD);
    }

    @Override
    public void setScreen(final ScreenId screenId) {
        setScreen(screenRegistrar.get(screenId));
        setUpInput();
    }

    private void setUpInput() {
        final InputMultiplexer multiplexer = new InputMultiplexer(screenRegistrar.getCurrent()
                                                                                 .getInputs());
        Gdx.input.setInputProcessor(multiplexer);
    }

    private void initializeRegistrars(final VoiceLoader voiceLoader) {
        screenRegistrar = new ScreenRegistrar(this, camera, voiceLoader, shareDispatcher);
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
        Settings.flush();
    }
}
