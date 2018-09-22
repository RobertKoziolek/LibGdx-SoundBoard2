package com.robcio.soundboard2.gui.splash;

import com.badlogic.gdx.graphics.Camera;
import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.utils.ScreenChanger;

public class SplashScreen extends AbstractScreen {

    public SplashScreen(final ScreenChanger screenChanger, final Camera camera){
        final SplashStageController stageController = new SplashStageController(camera);

        setStage(stageController);
    }

    @Override
    public void show() {
    }
}
