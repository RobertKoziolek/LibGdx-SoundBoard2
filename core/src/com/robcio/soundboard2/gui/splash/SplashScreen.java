package com.robcio.soundboard2.gui.splash;

import com.badlogic.gdx.graphics.Camera;
import com.robcio.soundboard2.gui.AbstractScreen;

public class SplashScreen extends AbstractScreen {

    public SplashScreen(final Camera camera){
        final SplashStageController stageController = new SplashStageController(camera);

        setStage(stageController);
    }

    @Override
    public void show() {
    }
}
