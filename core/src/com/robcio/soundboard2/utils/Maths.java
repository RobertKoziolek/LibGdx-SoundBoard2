package com.robcio.soundboard2.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Maths {
    public static final float PPM = 64f;
    public static final int SEARCH_RATIO = 65;

    public static boolean containsBit(final int first, final int second){
        return (first & second) > 0;
    }
}
