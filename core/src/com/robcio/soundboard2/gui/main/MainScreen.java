package com.robcio.soundboard2.gui.main;

import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.indicator.IndicatorContainer;
import com.robcio.soundboard2.utils.ShareDispatcher;
import com.robcio.soundboard2.voice.VoiceContainer;
import com.robcio.soundboard2.voice.VoiceSorter;

public class MainScreen extends AbstractScreen {
    private final VoiceContainer voiceContainer;
    private final VoiceSorter voiceSorter;
    private final ShareDispatcher shareDispatcher;
    private final IndicatorContainer indicatorContainer;

    private MainStageController stageController;

    public MainScreen(final VoiceContainer voiceContainer,
                      final VoiceSorter voiceSorter,
                      final ShareDispatcher shareDispatcher,
                      final IndicatorContainer indicatorContainer) {
        this.voiceContainer = voiceContainer;
        this.voiceSorter = voiceSorter;
        this.shareDispatcher = shareDispatcher;
        this.indicatorContainer = indicatorContainer;
    }

    @Override
    public void show() {
        if (stageController == null) {
            stageController = new MainStageController(voiceContainer,
                                                      voiceSorter,
                                                      shareDispatcher,
                                                      indicatorContainer);
            indicatorContainer.loadUp(stageController);
            setStage(stageController);
            StageAnimation.enterFadeIn(stageController);
        } else {
            StageAnimation.enterFromTop(stageController);
            stageController.updateButtons();
        }
    }
}
