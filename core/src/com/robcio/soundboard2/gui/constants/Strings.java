package com.robcio.soundboard2.gui.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Strings {

    public static final String EMPTY_STRING = "";

    //OPTIONS
    public static final String ALL_FILTERS_BUTTON = "Wszystko";
    public static final String BACK_BUTTON = "Wróć";
    public static final String PACKETS_LABEL = "Pakiety:";
    public static final String PEOPLE_LABEL = "Ludzie:";
    public static final String FILTERS_LABEL = "Filtry:";

    //MAIN
    public static final String OPTIONS_BUTTON = "Opcje";
    public static final String SILENCE_BUTTON = "Silentium";
    public static final String SEARCH_STRING = "Szukaj";

    //INTERNAL
    private static final String BLANK = "Nic";
    private static final String FULL = "Wszystko";


    public static String counter(final int current, final int all) {
        if (current == 0) return BLANK;
        if (current == all) return FULL;
        return current + " / " + all;
    }
}
