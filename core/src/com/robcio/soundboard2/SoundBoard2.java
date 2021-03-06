package com.robcio.soundboard2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.gui.SizeHolder;
import com.robcio.soundboard2.gui.SoundboardScreen;
import com.robcio.soundboard2.gui.SoundboardStage;
import com.robcio.soundboard2.gui.animation.ActorAnimation;
import com.robcio.soundboard2.registrar.ScreenRegistrar;
import com.robcio.soundboard2.utils.ScreenChanger;
import com.robcio.soundboard2.utils.assets.Assets;
import com.robcio.soundboard2.utils.dispatcher.ShareDispatcher;
import com.robcio.soundboard2.voice.loader.VoiceLoader;

import static com.robcio.soundboard2.constants.Numeral.HEIGHT;
import static com.robcio.soundboard2.constants.Numeral.WIDTH;

public class SoundBoard2 extends Game implements ScreenChanger {

    private final ShareDispatcher shareDispatcher;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ScreenRegistrar screenRegistrar;

    public SoundBoard2(final ShareDispatcher shareDispatcher) {
        this.shareDispatcher = shareDispatcher;
    }

    @Override
    public void create() {
        Gdx.input.setCatchBackKey(true);
        Assets.initialize();
        initializeGuiSizes();
        initializeCamera();
        initializeBatch();

        final VoiceLoader voiceLoader = new VoiceLoader();
        voiceLoader.load();

        initializeRegistrars(voiceLoader);
        setScreen(ScreenId.LOAD);
    }

    private void initializeGuiSizes() {
        final SizeHolder sizeHolder = new SizeHolder();
        ActorAnimation.setSizeHolder(sizeHolder);
        SoundboardStage.setSizeHolder(sizeHolder);
    }

    @Override
    public void setScreen(final ScreenId screenId) {
        final SoundboardScreen screen = screenRegistrar.get(screenId);
        setScreen(screen);
        Gdx.input.setInputProcessor(new InputMultiplexer(screen.getInputs()));
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
    }
}
