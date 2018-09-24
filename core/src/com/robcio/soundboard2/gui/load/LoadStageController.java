package com.robcio.soundboard2.gui.load;

import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.gui.StageController;
import com.robcio.soundboard2.loader.VoiceLoader;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.Maths;
import com.robcio.soundboard2.voice.VoiceHolder;

import static com.robcio.soundboard2.gui.constants.Sizes.*;

class LoadStageController extends StageController {

    private final Slider slider;
    private final VoiceLoader voiceLoader;
    private final VoiceHolder voiceHolder;

    LoadStageController(final VoiceLoader voiceLoader,
                        final VoiceHolder voiceHolder) {
        super();
        this.voiceLoader = voiceLoader;
        this.voiceHolder = voiceHolder;

        slider = new Slider(0, 1, 0.01f, false, Assets.getSkin());
        slider.getStyle().knob.setMinWidth(Maths.PPM / 2);
        slider.getStyle().knob.setMinHeight(Maths.PPM);

        final Table table = new Table();
        table.setFillParent(true);
        table.add(slider)
             .width(ALMOST_WIDTH)
             .height(MENU_HEIGHT)
             .padBottom(HALF_HEIGHT);
        addActor(table);
    }

    @Override
    public void act(final float delta) {
        super.act(delta);
        updateProgress();
        loadAssets();
    }

    private void loadAssets() {
        if (Assets.update()) {
            voiceHolder.loadUp(voiceLoader);
//            changeScreen(ScreenId.SPLASH);
            changeScreen(ScreenId.MAIN);
        }
    }

    private void updateProgress() {
        slider.setValue(Assets.getProgress());
    }
}
