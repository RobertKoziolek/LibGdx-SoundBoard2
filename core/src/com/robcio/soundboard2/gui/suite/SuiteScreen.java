package com.robcio.soundboard2.gui.suite;

import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.voice.SuiteContainer;
import com.robcio.soundboard2.voice.VoiceContainer;

public class SuiteScreen extends AbstractScreen {
    final private SuiteStageController suiteStageController;

    public SuiteScreen(final VoiceContainer voiceContainer,
                       final SuiteContainer suiteContainer) {
        suiteStageController = new SuiteStageController(voiceContainer,
                                                          suiteContainer);
        setStage(suiteStageController);
    }

    @Override
    public void show() {
        StageAnimation.enterFromRight(suiteStageController);
        final int size = suiteStageController.getActors().size;
        if (size == 0) {
            suiteStageController.buildStage();
        }
    }
}
