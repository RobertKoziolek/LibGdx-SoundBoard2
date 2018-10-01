package com.robcio.soundboard2.constants;

import com.robcio.soundboard2.utils.Maths;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.robcio.soundboard2.SoundBoard2.HEIGHT;
import static com.robcio.soundboard2.SoundBoard2.WIDTH;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Numeral {
    //SIZE
    public static final float HALF_WIDTH = WIDTH / 2f;
    public static final float THIRD_WIDTH = WIDTH / 3f;
    public static final float ALMOST_WIDTH = WIDTH * 5f / 6f;
    public static final float UNIT_WIDTH = HEIGHT / 12f;

    public static final float HALF_HEIGHT = HEIGHT / 2f;
    public static final float ALMOST_HEIGHT = HEIGHT * 5f / 6f;
    public static final float OPTION_HEIGHT = HEIGHT / 17f;
    public static final float UNIT_HEIGHT = HEIGHT / 12f;

    //ANIMATION
    public static final float SHORT_DURATION = 0.05f;
    public static final float NORMAL_DURATION = 0.25f;
    public static final float LONG_DURATION = 0.4f;
    public static final float SHAKE_OFFSET = Maths.PPM / 4f;

    //INDICATOR
    public static final float X_POSITION_FRACTION_LEFT = -0.067f;
    public static final float Y_POSITION_FRACTION_LEFT = -0.059f;
    public static final float X_POSITION_FRACTION_CENTER = -0.067f;
    public static final float Y_POSITION_FRACTION_CENTER = -0.059f;
    public static final float X_POSITION_FRACTION_RIGHT = 0.8f;
    public static final float Y_POSITION_FRACTION_RIGHT = -0.027f;
    public static final float INDICATOR_SHORT_DURATION = 0.3f;
    public static final float INDICATOR_NORMAL_DURATION = 0.85f;
    public static final float INDICATOR_LONG_DURATION = 2.35f;
    public static final float INDICATOR_ANGLE = 90f;



}
