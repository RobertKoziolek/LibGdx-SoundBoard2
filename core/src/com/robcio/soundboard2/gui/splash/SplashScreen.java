package com.robcio.soundboard2.gui.splash;

import com.robcio.soundboard2.gui.AbstractScreen;

public class SplashScreen extends AbstractScreen {

    public SplashScreen(){
        final SplashStageController stageController = new SplashStageController();

        setStage(stageController);
    }

    @Override
    public void show() {
    }
}
