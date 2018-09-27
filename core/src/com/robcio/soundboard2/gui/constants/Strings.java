package com.robcio.soundboard2.gui.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Strings {

    public static final String EMPTY_STRING = "";

    //SHARING
    public static final String SHARE_SUBJECT = "Cud natury";
    public static final String SHARE_QUESTION = "Gdzie wysłać ten cud natury";

    //OPTIONS
    public static final String ALL_FILTERS_BUTTON = "Wszystko";
    public static final String BACK_BUTTON = "Wróć";
    public static final String PACKETS_LABEL = "Pakiety:";
    public static final String PEOPLE_LABEL = "Ludzie:";
    public static final String FILTERS_LABEL = "Filtry:";
    public static final String OPTIONS_LABEL = "Opcje:";
    public static final String SHARING_LABEL = "Udostępnianie";
    public static final String SORT_LABEL = "Sortowanie:  ";

    //SORTING
    public static final String SORT_PACKETS = "Po pakietach";
    public static final String SORT_RANDOM = "Losowo";
    public static final String SORT_ALPHABETICAL  = "Alfabetycznie";
    public static final String SORT_ANALPHABETIC = "Analfabetycznie";


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
