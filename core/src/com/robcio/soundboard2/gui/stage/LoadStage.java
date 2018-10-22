package com.robcio.soundboard2.gui.stage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.robcio.soundboard2.constants.Strings;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.gui.SoundboardStage;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.gui.assembler.LabelAssembler;
import com.robcio.soundboard2.gui.assembler.ProgressBarAssembler;
import com.robcio.soundboard2.gui.assembler.TableAssembler;
import com.robcio.soundboard2.utils.assets.Assets;
import com.robcio.soundboard2.utils.assets.AssetsLoader;
import com.robcio.soundboard2.utils.dispatcher.ToastDispatcher;
import com.robcio.soundboard2.voice.SuiteContainer;
import com.robcio.soundboard2.voice.VoiceContainer;

import static com.robcio.soundboard2.constants.Numeral.*;
import static com.robcio.soundboard2.constants.Strings.INSTRUCTION_LOADING;

public class LoadStage extends SoundboardStage {

    private final Texture loadingBackground;
    private final ProgressBar progressBar;
    private final VoiceContainer voiceContainer;
    private final AssetsLoader assetsLoader;

    public LoadStage(final VoiceContainer voiceContainer, final SuiteContainer suiteContainer) {
        super();
        this.loadingBackground = Assets.getLoadingBackground();
        this.voiceContainer = voiceContainer;
        this.assetsLoader = Assets.getAssetsLoader();

        progressBar = ProgressBarAssembler.barOf(0f, 1f, 0.001f)
                                          .assemble();

        buildStage();

        assetsLoader.finishLoading(voiceContainer, suiteContainer);
    }

    @Override
    protected void show() {
        ToastDispatcher.showText(INSTRUCTION_LOADING);
    }

    @Override
    protected void buildStage() {
        final Table rootTable = TableAssembler.table()
                                              .fillParent()
                                              .assemble();
        final Label progressLabel = LabelAssembler.labelOf(Strings.percentage(0f))
                                                  .observing(assetsLoader)
                                                  .assemble();

        rootTable.add(progressBar)
                 .width(ALMOST_WIDTH)
                 .height(UNIT_HEIGHT)
                 .padBottom(HALF_HEIGHT)
                 .row();
        rootTable.add(progressLabel)
                 .height(UNIT_HEIGHT);
        addActor(rootTable);
    }

    @Override
    protected void backKeyDown() {
        changeScreenToSplash();
    }

    private void changeScreenToSplash() {
        changeScreen(ScreenId.SPLASH, StageAnimation.exitFadeOut());
    }

    @Override
    public void act(final float delta) {
        super.act(delta);
        drawBackground();
        updateProgress();
        checkIfAssetsLoaded();
    }

    private void drawBackground() {
        final Batch batch = getBatch();
        if (batch.isDrawing()) batch.end();
        batch.begin();
        batch.draw(loadingBackground, 0f, 0f, getWidth(), getHeight());
        batch.end();
    }

    private void updateProgress() {
        progressBar.setValue(assetsLoader.getProgress());
    }

    private void checkIfAssetsLoaded() {
        if (voiceContainer.isLoaded()) {
            changeScreenToSplash();
        }
    }

    @Override
    protected void touchDown() {
        voiceContainer.partialLoadUp();
        changeScreenToSplash();
    }
}
