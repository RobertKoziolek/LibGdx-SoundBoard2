package com.robcio.soundboard2.gui.splash;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.enumeration.TextureId;
import com.robcio.soundboard2.gui.StageController;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.Maths;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.robcio.soundboard2.constants.Numeral.*;

class SplashStageController extends StageController {

    private final Image robcioLogo, soundboardLogo;

    SplashStageController() {
        super();
        robcioLogo = Assets.getImage(TextureId.ROBCIO_LOGO);
        soundboardLogo = Assets.getImage(TextureId.SOUNDBOARD_LOGO);

        setUpRobcioLogoAnimation();
        setUpSoundboardLogoAnimation();

        addActor(robcioLogo);
        addActor(soundboardLogo);
    }

    @Override
    protected void backKeyDown() {
        moveToMainScreen();
    }

    private void setUpRobcioLogoAnimation() {
        robcioLogo.setPosition(HALF_WIDTH, ALMOST_HEIGHT);
        final float centerX = HALF_WIDTH - robcioLogo.getWidth() / 2f;
        final float centerY = HALF_HEIGHT + Maths.PPM * 2f;

        robcioLogo.addAction(sequence(alpha(0f), scaleTo(.1f, .1f),
                                      parallel(fadeIn(FADE_IN_DURATION, Interpolation.pow2),
                                               scaleTo(1f, 1f, SCALE_IN_DURATION, Interpolation.pow5),
                                               moveTo(centerX, centerY, MOVE_IN_DURATION, Interpolation.swing))));
    }

    private void setUpSoundboardLogoAnimation() {
        soundboardLogo.setPosition(0, 0);
        final float centerX = HALF_WIDTH - soundboardLogo.getWidth() / 2f;
        final float centerY = HALF_HEIGHT - soundboardLogo.getHeight();

        soundboardLogo.addAction(sequence(alpha(0f), scaleTo(.1f, .1f),
                                          parallel(fadeIn(FADE_IN_DURATION, Interpolation.pow2),
                                                   scaleTo(1f, 1f, SCALE_IN_DURATION, Interpolation.pow5),
                                                   moveTo(centerX, centerY, MOVE_IN_DURATION))));
    }

    private void moveToMainScreen() {
        changeScreen(ScreenId.MAIN, StageAnimation.exitFadeOut());
    }

    @Override
    public void act(final float delta) {
        super.act(delta);
        if (!robcioLogo.hasActions() && !soundboardLogo.hasActions()) {
            moveToMainScreen();
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        moveToMainScreen();
        return true;
    }
}
