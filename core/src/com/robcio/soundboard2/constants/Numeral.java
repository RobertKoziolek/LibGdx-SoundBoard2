package com.robcio.soundboard2.constants;

import com.robcio.soundboard2.utils.helper.Maths;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Numeral {
    //SEGMENT
    private static final int DEFAULT_NUMBER_OF_SEGMENTS = 12;
    private static final int MIN_NUMBER_OF_SEGMENTS = 9;
    private static final int MAX_NUMBER_OF_SEGMENTS = 22;
    private static final int NUMBER_OF_STEPS = 15;

    public static final float MIN_SEGMENT_SIZE = 1f / MAX_NUMBER_OF_SEGMENTS;
    public static final float MAX_SEGMENT_SIZE = 1f / MIN_NUMBER_OF_SEGMENTS;
    public static final float SEGMENT_SIZE_STEP = (MAX_SEGMENT_SIZE - MIN_SEGMENT_SIZE) / NUMBER_OF_STEPS;
    static final float DEFAULT_SEGMENT_SIZE = 1f / DEFAULT_NUMBER_OF_SEGMENTS;

    //SIZE
    public static final int WIDTH = (int) (9 * Maths.PPM);
    public static final int HEIGHT = (int) (16 * Maths.PPM);
    public static final float MIN_KNOB_WIDTH = Maths.PPM / 2f;
    public static final float MIN_KNOB_HEIGHT = Maths.PPM;

    public static final float HALF_WIDTH = WIDTH / 2f;
    public static final float THIRD_WIDTH = WIDTH / 3f;
    public static final float QUATER_WIDTH = WIDTH / 4f;
    public static final float ALMOST_WIDTH = WIDTH * 5f / 6f;

    public static final float HALF_HEIGHT = HEIGHT / 2f;
    public static final float THIRD_HEIGHT = HEIGHT / 3f;
    public static final float OPTION_HEIGHT = HEIGHT / 18f;

    //STAGE ANIMATION
    public static final float SHORT_DURATION = 0.05f;
    public static final float NORMAL_DURATION = 0.25f;
    public static final float LONG_DURATION = 0.4f;
    public static final float SHAKE_OFFSET = Maths.PPM / 4f;

    //ACTOR ANIMATION
    public static final float ACTOR_IN_DURATION = 0.3f;
    public static final float ACTOR_OUT_DURATION = 0.3f;

    //INDICATOR
    public static final float X_POSITION_FRACTION_LEFT = -0.067f;
    public static final float Y_POSITION_FRACTION_LEFT = -0.059f;
    public static final float X_POSITION_FRACTION_CENTER = -0.067f;
    public static final float Y_POSITION_FRACTION_CENTER = -0.059f;
    public static final float X_POSITION_FRACTION_RIGHT = 0.81f;
    public static final float Y_POSITION_FRACTION_RIGHT = -0.027f;
    public static final float INDICATOR_SHORT_DURATION = 0.3f;
    public static final float INDICATOR_NORMAL_DURATION = 0.85f;
    public static final float INDICATOR_LONG_DURATION = 2.35f;
    public static final float INDICATOR_ANGLE = 90f;

    //SPLASH ANIMATION
    public static final float FADE_IN_DURATION = 2f;
    public static final float MOVE_IN_DURATION = 2f;
    public static final float SCALE_IN_DURATION = 2.5f;

}
