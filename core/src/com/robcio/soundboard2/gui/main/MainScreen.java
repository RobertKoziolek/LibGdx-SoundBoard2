package com.robcio.soundboard2.gui.main;

import com.badlogic.gdx.graphics.Camera;
import com.robcio.soundboard2.gui.AbstractScreen;

public class MainScreen extends AbstractScreen {
    public MainScreen(final Camera camera) {
        final MainStageController stageController = new MainStageController(camera);

        setStage(stageController);
    }

    @Override
    public void show() {

    }
}
