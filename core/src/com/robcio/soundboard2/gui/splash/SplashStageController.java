package com.robcio.soundboard2.gui.splash;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.robcio.soundboard2.enumeration.TextureId;
import com.robcio.soundboard2.utils.Assets;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.robcio.soundboard2.SoundBoard2.WIDTH;
import static com.robcio.soundboard2.SoundBoard2.HEIGHT;

public class SplashStageController extends Stage {

    private final Image logo;

    public SplashStageController(final Camera camera) {
        super(new FillViewport(WIDTH, HEIGHT, camera));
        logo = Assets.getImage(TextureId.LOGO);
        logo.setPosition(getWidth() / 2 - 70, getHeight());
        logo.addAction(sequence(alpha(0f), scaleTo(.1f, .1f),
                                       parallel(fadeIn(2f, Interpolation.pow2), scaleTo(1f, 1f, 2.5f, Interpolation.pow5),
                                                moveTo(getWidth() / 2 - 200, getHeight() / 2 + 205, 2f, Interpolation.swing))));
        addActor(logo);
    }
}
