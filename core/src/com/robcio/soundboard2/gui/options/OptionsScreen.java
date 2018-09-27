package com.robcio.soundboard2.gui.options;

import com.robcio.soundboard2.filter.FilterMap;
import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.utils.ShareDispatcher;
import com.robcio.soundboard2.voice.VoiceContainer;

public class OptionsScreen extends AbstractScreen {
    final private OptionsStageController optionsStageController;

    public OptionsScreen(final VoiceContainer voiceContainer,
                         final ShareDispatcher shareDispatcher,
                         final FilterMap filterMap) {
        optionsStageController = new OptionsStageController(voiceContainer, shareDispatcher, filterMap);
        setStage(optionsStageController);
    }

    @Override
    public void show() {
        StageAnimation.enterFromTop(optionsStageController);
        final int size = optionsStageController.getActors().size;
        if (size == 0) {
            optionsStageController.buildStage();
        }
    }
}
