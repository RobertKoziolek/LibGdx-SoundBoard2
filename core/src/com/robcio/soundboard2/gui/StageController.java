package com.robcio.soundboard2.gui;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.utils.ScreenChanger;

import static com.robcio.soundboard2.SoundBoard2.HEIGHT;
import static com.robcio.soundboard2.SoundBoard2.WIDTH;

public abstract class StageController extends Stage {
    static private ScreenChanger screenChanger;
    static private Camera camera;

    public StageController() {
        super(new FillViewport(WIDTH, HEIGHT, camera));
    }

    final protected void changeScreen(final ScreenId screenId) {
        screenChanger.setScreen(screenId);
    }

    public static void setScreenChangerAndCamera(final ScreenChanger screenChanger, final OrthographicCamera camera) {
        if (StageController.screenChanger != null || StageController.camera != null) {
            throw new IllegalStateException("Stage controller components cannot be set more than once");
        }
        StageController.screenChanger = screenChanger;
        StageController.camera = camera;
    }
}
