package com.robcio.soundboard2.gui.main;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.voice.VoiceHolder;

public class MainScreen extends AbstractScreen {
    private final VoiceHolder voiceHolder;

    public MainScreen(final VoiceHolder voiceHolder) {
        this.voiceHolder = voiceHolder;
    }

    @Override
    public void show() {
        final Stage stage = getStage();
        if (stage == null) {
            final MainStageController stageController = new MainStageController(voiceHolder.getCurrentList());

            setStage(stageController);
        } else {
            final MainStageController mainStageController = (MainStageController) stage;
            mainStageController.updateButtons();
        }
    }
}
