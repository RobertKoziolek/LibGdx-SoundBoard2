package com.robcio.soundboard2.gui.main;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.utils.ScreenChanger;
import com.robcio.soundboard2.voice.VoiceHolder;

public class MainScreen extends AbstractScreen {
    private final ScreenChanger screenChanger;
    private final Camera camera;
    private final VoiceHolder voiceHolder;

    public MainScreen(final ScreenChanger screenChanger,
                      final Camera camera,
                      final VoiceHolder voiceHolder) {
        this.screenChanger = screenChanger;
        this.camera = camera;
        this.voiceHolder = voiceHolder;
    }

    @Override
    public void show() {
        final Stage stage = getStage();
        if (stage == null) {
            final MainStageController stageController = new MainStageController(screenChanger, camera,
                                                                                voiceHolder.getCurrentList());

            setStage(stageController);
        } else {
            final MainStageController mainStageController = (MainStageController) stage;
            mainStageController.updateButtons();
        }
    }
}
