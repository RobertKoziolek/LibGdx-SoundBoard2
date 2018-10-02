package com.robcio.soundboard2.gui.load;

import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.voice.VoiceContainer;
import com.robcio.soundboard2.voice.loader.VoiceLoader;

public class LoadScreen extends AbstractScreen {

    public LoadScreen(final VoiceLoader voiceLoader, final VoiceContainer voiceContainer) {
        final LoadStageController stageController = new LoadStageController(voiceLoader, voiceContainer);

        setStage(stageController);
    }

    @Override
    public void show() {
    }
}
