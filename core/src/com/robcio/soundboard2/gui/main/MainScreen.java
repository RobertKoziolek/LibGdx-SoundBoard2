package com.robcio.soundboard2.gui.main;

import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.utils.ShareDispatcher;
import com.robcio.soundboard2.voice.VoiceContainer;

public class MainScreen extends AbstractScreen {
    private final VoiceContainer voiceContainer;
    private final ShareDispatcher shareDispatcher;

    private MainStageController stageController;

    public MainScreen(final VoiceContainer voiceContainer, final ShareDispatcher shareDispatcher) {
        this.voiceContainer = voiceContainer;
        this.shareDispatcher = shareDispatcher;
    }

    @Override
    public void show() {
        if (stageController == null) {
            stageController = new MainStageController(voiceContainer.getCurrentList(), shareDispatcher);
            setStage(stageController);
        } else {
            StageAnimation.enterFromTop(stageController);
            stageController.updateButtons();
        }
    }
}
