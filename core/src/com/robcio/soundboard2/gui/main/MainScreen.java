package com.robcio.soundboard2.gui.main;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.utils.SharingManager;
import com.robcio.soundboard2.voice.VoiceHolder;

public class MainScreen extends AbstractScreen {
    private final VoiceHolder voiceHolder;
    private final SharingManager sharingManager;

    public MainScreen(final VoiceHolder voiceHolder, final SharingManager sharingManager) {
        this.voiceHolder = voiceHolder;
        this.sharingManager = sharingManager;
    }

    @Override
    public void show() {
        final Stage stage = getStage();
        if (stage == null) {
            final MainStageController stageController = new MainStageController(voiceHolder.getCurrentList(), sharingManager);

            setStage(stageController);
        } else {
            StageAnimation.enterFromTop(stage);
            final MainStageController mainStageController = (MainStageController) stage;
            mainStageController.updateButtons();
        }
    }
}
