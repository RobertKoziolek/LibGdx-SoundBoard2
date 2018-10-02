package com.robcio.soundboard2.gui.load;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.robcio.soundboard2.constants.Strings;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.gui.StageController;
import com.robcio.soundboard2.gui.assembler.LabelAssembler;
import com.robcio.soundboard2.gui.assembler.TableAssembler;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.AssetsLoader;
import com.robcio.soundboard2.utils.Maths;
import com.robcio.soundboard2.voice.VoiceContainer;
import com.robcio.soundboard2.voice.loader.VoiceLoader;

import static com.robcio.soundboard2.constants.Numeral.*;

class LoadStageController extends StageController {

    private final Texture loadingBackground;
    private final Slider slider;
    private final VoiceLoader voiceLoader;
    private final VoiceContainer voiceContainer;
    private final AssetsLoader assetsLoader;

    LoadStageController(final VoiceLoader voiceLoader,
                        final VoiceContainer voiceContainer) {
        super();
        this.loadingBackground = Assets.getLoadingBackground();
        this.voiceLoader = voiceLoader;
        this.voiceContainer = voiceContainer;
        this.assetsLoader = Assets.getLoader();

        slider = new Slider(0, 1, 0.001f, false, Assets.getSkin());
        slider.getStyle().knob.setMinWidth(Maths.PPM / 2);
        slider.getStyle().knob.setMinHeight(Maths.PPM);

        final Table rootTable = TableAssembler.table()
                                              .fillParent()
                                              .assemble();
        final Label tibia = LabelAssembler.labelOf(Strings.percentage(0f))
                                          .observing(assetsLoader)
                                          .assemble();

        rootTable.add(slider)
                 .width(ALMOST_WIDTH)
                 .height(UNIT_HEIGHT)
                 .padBottom(HALF_HEIGHT)
                 .row();
        rootTable.add(tibia)
                 .height(UNIT_HEIGHT);
        addActor(rootTable);
    }

    @Override
    public void act(final float delta) {
        super.act(delta);
        drawBackground();
        updateProgress();
        loadAssets();
    }

    private void drawBackground() {
        getBatch().begin();
        getBatch().draw(loadingBackground, 0f, 0f, getWidth(), getHeight());
        getBatch().end();
    }

    private void loadAssets() {
        if (assetsLoader.update()) {
            voiceContainer.loadUp(voiceLoader);
            changeScreen(ScreenId.SPLASH);
        }
    }

    private void updateProgress() {
        slider.setValue(assetsLoader.getProgress());
    }
}
