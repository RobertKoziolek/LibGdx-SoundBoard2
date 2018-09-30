package com.robcio.soundboard2.indicator;


import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.robcio.soundboard2.enumeration.IndicatorAlign;
import lombok.Getter;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.robcio.soundboard2.SoundBoard2.HEIGHT;
import static com.robcio.soundboard2.SoundBoard2.WIDTH;
import static com.robcio.soundboard2.constants.Numeral.*;

class Indicator {

    @Getter
    private final Image image;
    private final float resetX, resetY;

    Indicator(final Image image, final IndicatorAlign align) {
        this.image = image;

        this.image.addAction(alpha(0f));
        this.image.setTouchable(Touchable.disabled);

        switch (align) {
            case LEFT:
                resetX = X_POSITION_FRACTION_LEFT;
                resetY = Y_POSITION_FRACTION_LEFT;
                break;
            case CENTER:
                resetX = X_POSITION_FRACTION_CENTER;
                resetY = Y_POSITION_FRACTION_CENTER;
                break;
            case RIGHT:
                resetX = X_POSITION_FRACTION_RIGHT;
                resetY = Y_POSITION_FRACTION_RIGHT;
                break;
            default:
                throw new IllegalArgumentException("Could not recognize align type for indicator");
        }
    }

    private void reset() {
        image.clearActions();
        image.addAction(sequence(rotateTo(-INDICATOR_ANGLE),
                                 moveTo(WIDTH * resetX, HEIGHT * resetY),
                                 alpha(0f)));
    }

    void start() {
        reset();
        image.addAction(parallel(
                sequence(fadeIn(INDICATOR_SHORT_DURATION),
                         delay(INDICATOR_LONG_DURATION),
                         parallel(fadeOut(INDICATOR_SHORT_DURATION),
                                  moveBy(0, -HALF_HEIGHT, INDICATOR_SHORT_DURATION, Interpolation.pow5)
                                 )
                        ),
                rotateBy(INDICATOR_ANGLE, INDICATOR_SHORT_DURATION, Interpolation.pow3),
                rotateTo(-INDICATOR_ANGLE)));
    }

}
