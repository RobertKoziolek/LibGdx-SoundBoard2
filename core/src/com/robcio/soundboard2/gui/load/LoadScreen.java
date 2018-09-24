package com.robcio.soundboard2.gui.load;

import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.loader.VoiceLoader;
import com.robcio.soundboard2.voice.VoiceHolder;

public class LoadScreen extends AbstractScreen {

    public LoadScreen(final VoiceLoader voiceLoader, final VoiceHolder voiceHolder) {
        final LoadStageController stageController = new LoadStageController(voiceLoader, voiceHolder);

        setStage(stageController);
    }

    @Override
    public void show() {

    }
}
