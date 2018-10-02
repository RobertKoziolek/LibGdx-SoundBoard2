package com.robcio.soundboard2.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import static com.robcio.soundboard2.constants.Strings.SETTING_APP_NAME;

public class Settings {
    static {

    }
    private static final Preferences preferences = Gdx.app.getPreferences(SETTING_APP_NAME);

    public static void flush() {
        preferences.flush();
    }

    public static void putInteger(final String key, final int value) {
        preferences.putInteger(key, value);
    }

    public static void putBoolean(final String key, final boolean value) {
        preferences.putBoolean(key, value);
    }

    public static int getInteger(final String key, final int defaultValue) {
        return preferences.getInteger(key, defaultValue);
    }

    public static boolean getBoolean(final String key, final boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }
}
