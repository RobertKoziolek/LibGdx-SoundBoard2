package com.robcio.soundboard2.gui.load;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.robcio.soundboard2.constants.Strings;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.gui.StageController;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.gui.assembler.LabelAssembler;
import com.robcio.soundboard2.gui.assembler.TableAssembler;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.AssetsLoader;
import com.robcio.soundboard2.utils.Maths;
import com.robcio.soundboard2.voice.VoiceContainer;

import static com.robcio.soundboard2.constants.Numeral.*;

class LoadStageController extends StageController {

    private final Texture loadingBackground;
    private final Slider slider;
    private final VoiceContainer voiceContainer;
    private final AssetsLoader assetsLoader;

    LoadStageController(final VoiceContainer voiceContainer) {
        super();
        this.loadingBackground = Assets.getLoadingBackground();
        this.voiceContainer = voiceContainer;
        this.assetsLoader = Assets.getAssetsLoader();

        slider = new Slider(0, 1, 0.001f, false, Assets.getSkin());
        slider.getStyle().knob.setMinWidth(Maths.PPM / 2);
        slider.getStyle().knob.setMinHeight(Maths.PPM);

        final Table rootTable = TableAssembler.table()
                                              .fillParent()
                                              .assemble();
        final Label progressLabel = LabelAssembler.labelOf(Strings.percentage(0f))
                                                  .observing(assetsLoader)
                                                  .assemble();

        rootTable.add(slider)
                 .width(ALMOST_WIDTH)
                 .height(UNIT_HEIGHT)
                 .padBottom(HALF_HEIGHT)
                 .row();
        rootTable.add(progressLabel)
                 .height(UNIT_HEIGHT);
        addActor(rootTable);

        assetsLoader.finishLoading(voiceContainer);
    }

    @Override
    protected void backKeyDown() {
        moveToSplashScreen();
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
        slider.setValue(assetsLoader.getProgress());
    }

    private void checkIfAssetsLoaded() {
        if (voiceContainer.isLoaded()) {
            moveToSplashScreen();
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        voiceContainer.partialLoadUp();
        moveToSplashScreen();
        return true;
    }

    private void moveToSplashScreen() {
        changeScreen(ScreenId.SPLASH, StageAnimation.exitFadeOut());
    }
}
