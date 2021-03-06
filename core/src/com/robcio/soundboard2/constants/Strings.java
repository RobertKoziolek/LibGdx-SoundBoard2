package com.robcio.soundboard2.constants;

import com.badlogic.gdx.math.MathUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Strings {

    //APP
    public static final String TITLE = "SoundBoard2";
    public static final String EMPTY_STRING = "";
    public static final String LIST_IS_EMPTY = "pusto here";

    //INSTUCTIONS
    public static final String INSTRUCTION_LOADING = "Kliknij by ładować w tle";
    public static final String INSTRUCTION_SHARING = "Trzymaj guzik ponad sekundę by wysłać";
    public static final String INSTRUCTION_RESTART_NEEDED = "Zmiany będą widoczne po ponownym uruchomieniu aplikacji";

    //SETTINGS
    public static final String SETTING_APP_NAME = "robcio_soundboard2_settings";
    public static final String SETTING_SEGMENT_SIZE = "setting_segment_size";
    public static final String SETTING_INDICATORS = "setting_indicator_on";
    public static final String SETTING_SORT_TYPE = "setting_sort_type";
    public static final String SETTING_SHARING = "setting_sharing_on";

    //SHARING_BOOLEAN
    public static final String SHARE_SUBJECT = "Cud natury";
    public static final String SHARE_QUESTION = "Dokąd wysłać ten cud natury?";

    //OPTIONS
    public static final String ALL_FILTERS_BUTTON = "Wszystko";
    public static final String SUITES_BUTTON = "Paczki";
    public static final String BACK_BUTTON = "Wróć";
    public static final String PACKETS_LABEL = "Pakiety:";
    public static final String PEOPLE_LABEL = "Ludzie:";
    public static final String FILTERS_LABEL = "Filtry:";
    public static final String OPTIONS_LABEL = "Opcje:";
    public static final String SHARING_LABEL = "Udostępnianie";
    public static final String INDICATOR_LABEL = "Wskaźniki";
    public static final String SORT_LABEL = "Sortowanie:  ";
    public static final String SEGMENT_SIZE_LABEL = "Rozmiar:";

    //SORTING
    public static final String SORT_PACKETS = "Po pakietach";
    public static final String SORT_RANDOM = "Losowo";
    public static final String SORT_ALPHABETICAL = "Alfabetycznie";
    public static final String SORT_ANALPHABETIC = "Analfabetycznie";

    //MAIN
    public static final String HUNDRED_PERCENT = "100.0%";
    public static final String OPTIONS_BUTTON = "Opcje";
    public static final String RESET_BUTTON = "Reset";
    public static final String SILENCE_BUTTON = "Cicho";
    public static final String SEARCH_STRING = "Szukaj";

    //EXIT DIALOG
    public static final String EXIT_QUESTION = "Już lecisz?";
    public static final String EXIT_YES = "Naaajak";
    public static final String EXIT_NO = "Newer";

    //INTERNAL
    private static final String PERCENTAGE_FORMAT = "%,.1f%%";
    private static final String DIVISION_SLASH = " / ";
    private static final String BLANK = "Nic";
    private static final String FULL = "Wszystko";

    //FUNCTION
    public static String counter(final int current, final int all) {
        if (current == 0) return BLANK;
        if (current == all) return FULL;
        return current + DIVISION_SLASH + all;
    }

    public static String percentage(final float value) {
        final float percentage = MathUtils.clamp(value, 0f, 1f) * 100;
        return String.format(Locale.ENGLISH, PERCENTAGE_FORMAT, percentage);
    }
}
