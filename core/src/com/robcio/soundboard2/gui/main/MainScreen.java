package com.robcio.soundboard2.gui.main;

import com.badlogic.gdx.graphics.Camera;
import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.loader.VoiceLoader;
import com.robcio.soundboard2.utils.ScreenChanger;

public class MainScreen extends AbstractScreen {
    private final Camera camera;
    private final VoiceLoader voiceLoader;

    public MainScreen(final ScreenChanger screenChanger,
                      final Camera camera,
                      final VoiceLoader voiceLoader) {
        this.camera = camera;
        this.voiceLoader = voiceLoader;
    }

    @Override
    public void show() {
        if (getStage() == null) {
            final MainStageController stageController = new MainStageController(camera, voiceLoader.getVoiceList());

            setStage(stageController);
        }
    }
}
