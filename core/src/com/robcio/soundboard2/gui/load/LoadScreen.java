package com.robcio.soundboard2.gui.load;

import com.badlogic.gdx.graphics.Camera;
import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.utils.ScreenChanger;

public class LoadScreen extends AbstractScreen {
    public LoadScreen(final ScreenChanger screenChanger,
                      final Camera camera) {
        final LoadStageController stageController = new LoadStageController(screenChanger, camera);

        setStage(stageController);
    }

    @Override
    public void show() {

    }
}
