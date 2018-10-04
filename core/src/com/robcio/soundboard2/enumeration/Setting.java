package com.robcio.soundboard2.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.robcio.soundboard2.constants.SettingDefaults.*;
import static com.robcio.soundboard2.constants.Strings.*;

@Getter
@AllArgsConstructor
public enum Setting {
    INDICATORS_BOOLEAN(SETTING_INDICATORS, Boolean.class, INDICATORS_BOOLEAN_DEFAULT),
    SHARING_BOOLEAN(SETTING_SHARING, Boolean.class, SHARING_BOOLEAN_DEFAULT),
    SORT_TYPE_INTEGER(SETTING_SORT_TYPE, Integer.class, SORT_INTEGER_DEFAULT),
    SEGMENT_SIZE_FLOAT(SETTING_SEGMENT_SIZE, Float.class, SEGMENT_SIZE_FLOAT_DEFAULT);

    private final String key;
    private final Class typeClass;
    private final Object defaultValue;
}
