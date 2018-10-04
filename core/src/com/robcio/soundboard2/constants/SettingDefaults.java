package com.robcio.soundboard2.constants;

import com.robcio.soundboard2.enumeration.SortingType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.robcio.soundboard2.constants.Numeral.DEFAULT_SEGMENT_SIZE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SettingDefaults {

    public static final boolean INDICATORS_BOOLEAN_DEFAULT = true;
    public static final boolean SHARING_BOOLEAN_DEFAULT = false;
    public static final int SORT_INTEGER_DEFAULT = SortingType.PACKETS.getId();
    public static final float SEGMENT_SIZE_FLOAT_DEFAULT = DEFAULT_SEGMENT_SIZE;

}
