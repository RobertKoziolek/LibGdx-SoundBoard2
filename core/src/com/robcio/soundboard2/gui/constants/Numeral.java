package com.robcio.soundboard2.gui.constants;

import com.robcio.soundboard2.utils.Maths;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.robcio.soundboard2.SoundBoard2.HEIGHT;
import static com.robcio.soundboard2.SoundBoard2.WIDTH;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Numeral {
    //SIZES
    public static final float HALF_WIDTH = WIDTH / 2f;
    public static final float THIRD_WIDTH = WIDTH / 3f;
    public static final float ALMOST_WIDTH = WIDTH * 4f / 5f;

    public static final float HALF_HEIGHT = HEIGHT / 2f;
    public static final float ALMOST_HEIGHT = HEIGHT * 4f / 5f;
    public static final float OPTION_HEIGHT = HEIGHT / 17f;
    public static final float MENU_HEIGHT = HEIGHT / 12f;

    //ANIMATION
    public static final float SHORT_DURATION = 0.05f;
    public static final float NORMAL_DURATION = 0.25f;
    public static final float LONG_DURATION = 0.4f;
    public static final float SHAKE_OFFSET = Maths.PPM / 4f;


}
