package com.robcio.soundboard2.gui;

import com.robcio.soundboard2.enumeration.Setting;
import com.robcio.soundboard2.utils.Settings;

import static com.robcio.soundboard2.constants.Numeral.HEIGHT;

public class SizeHolder {

    private final float SEGMENT_SIZE = Settings.get(Setting.SEGMENT_SIZE_FLOAT);
    private final float NUMBER_OF_SEGMENTS = 1f / SEGMENT_SIZE;
    private final float SEGMENTS_WITHOUT_TOP = NUMBER_OF_SEGMENTS - 1f;
    private final float SEGMENTS_WITHOUT_TOP_AND_BOTTOM = SEGMENTS_WITHOUT_TOP - 1f;

    public final float NO_TOP_HEIGHT = HEIGHT * SEGMENTS_WITHOUT_TOP / NUMBER_OF_SEGMENTS;
    public final float NO_TOP_AND_BOTTOM_HEIGHT = HEIGHT * SEGMENTS_WITHOUT_TOP_AND_BOTTOM / NUMBER_OF_SEGMENTS;
    public final float UNIT_HEIGHT = HEIGHT / NUMBER_OF_SEGMENTS;
}
