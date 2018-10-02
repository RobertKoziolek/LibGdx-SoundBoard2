package com.robcio.soundboard2.gui.load;

import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.utils.ToastMessage;
import com.robcio.soundboard2.voice.VoiceContainer;

import static com.robcio.soundboard2.constants.Strings.INSTRUCTION_LOADING;

public class LoadScreen extends AbstractScreen {

    public LoadScreen(final VoiceContainer voiceContainer) {
        final LoadStageController stageController = new LoadStageController(voiceContainer);

        setStage(stageController);
    }

    @Override
    public void show() {
        ToastMessage.showText(INSTRUCTION_LOADING);
    }
}
