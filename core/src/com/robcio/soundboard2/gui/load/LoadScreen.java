package com.robcio.soundboard2.gui.load;

import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.utils.dispatcher.ToastDispatcher;
import com.robcio.soundboard2.voice.SuiteContainer;
import com.robcio.soundboard2.voice.VoiceContainer;

import static com.robcio.soundboard2.constants.Strings.INSTRUCTION_LOADING;

public class LoadScreen extends AbstractScreen {

    public LoadScreen(final VoiceContainer voiceContainer, final SuiteContainer suiteContainer) {
        final LoadStageController stageController = new LoadStageController(voiceContainer, suiteContainer);

        setStage(stageController);
    }

    @Override
    public void show() {
        ToastDispatcher.showText(INSTRUCTION_LOADING);
    }
}
