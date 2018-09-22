package com.robcio.soundboard2.gui.main;

import com.badlogic.gdx.graphics.Camera;
import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.voice.Voice;

import java.util.List;

public class MainScreen extends AbstractScreen {
    public MainScreen(final Camera camera,
                      final List<Voice> voiceModelList) {
        final MainStageController stageController = new MainStageController(camera, voiceModelList);

        setStage(stageController);
    }

    @Override
    public void show() {

    }
}
