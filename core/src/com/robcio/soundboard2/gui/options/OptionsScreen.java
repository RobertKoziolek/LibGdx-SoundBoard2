package com.robcio.soundboard2.gui.options;

import com.robcio.soundboard2.filter.FilterMap;
import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.utils.SharingManager;
import com.robcio.soundboard2.voice.VoiceHolder;

public class OptionsScreen extends AbstractScreen {
    final private OptionsStageController optionsStageController;

    public OptionsScreen(final VoiceHolder voiceHolder,
                         final SharingManager sharingManager,
                         final FilterMap filterMap) {
        optionsStageController = new OptionsStageController(voiceHolder, sharingManager, filterMap);
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
