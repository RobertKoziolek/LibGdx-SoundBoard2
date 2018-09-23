package com.robcio.soundboard2.gui.options;

import com.badlogic.gdx.graphics.Camera;
import com.robcio.soundboard2.filter.FilterMap;
import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.utils.ScreenChanger;
import com.robcio.soundboard2.voice.VoiceHolder;

public class OptionsScreen extends AbstractScreen {
    final private OptionsStageController optionsStageController;

    public OptionsScreen(final ScreenChanger screenChanger,
                         final Camera camera,
                         final VoiceHolder voiceHolder,
                         final FilterMap filterMap) {
        optionsStageController = new OptionsStageController(screenChanger,
                                                            camera,
                                                            voiceHolder,
                                                            filterMap);
    }

    @Override
    public void show() {
        final int size = optionsStageController.getActors().size;
        if (size < 1) {
            optionsStageController.buildStage();
            setStage(optionsStageController);
        }
    }
}