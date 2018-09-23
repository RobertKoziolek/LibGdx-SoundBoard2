package com.robcio.soundboard2.gui;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.utils.ScreenChanger;

import static com.robcio.soundboard2.SoundBoard2.HEIGHT;
import static com.robcio.soundboard2.SoundBoard2.WIDTH;

public abstract class StageController extends Stage {
    final private ScreenChanger screenChanger;

    public StageController(final ScreenChanger screenChanger, final Camera camera){
        super(new FillViewport(WIDTH, HEIGHT, camera));
        this.screenChanger = screenChanger;
    }

    final protected void changeScreen(final ScreenId screenId){
        screenChanger.setScreen(screenId);
    }
}
