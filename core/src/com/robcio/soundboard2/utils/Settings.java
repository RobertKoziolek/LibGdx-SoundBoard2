package com.robcio.soundboard2.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.robcio.soundboard2.enumeration.Setting;
import com.robcio.soundboard2.utils.helper.Log;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.robcio.soundboard2.constants.Strings.SETTING_APP_NAME;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Settings {
    private static final Preferences preferences = Gdx.app.getPreferences(SETTING_APP_NAME);

    public static void put(final Setting setting, final Object newValue) {
        final Class typeClass = setting.getTypeClass();
        final Class<?> newValueClass = newValue.getClass();
        if (!typeClass.equals(newValueClass)) {
            throw new IllegalArgumentException("New value for setting must match setting type");
        }
        final String key = setting.getKey();
        Log.i(key, String.valueOf(newValue));
        if (typeClass.equals(Integer.class)) {
            preferences.putInteger(key, (Integer) newValue);
            preferences.flush();
            return;
        } else if (typeClass.equals(Boolean.class)) {
            preferences.putBoolean(key, (Boolean) newValue);
            preferences.flush();
            return;
        } else if (typeClass.equals(Float.class)) {
            preferences.putFloat(key, (Float) newValue);
            preferences.flush();
            return;
        }
        throw new IllegalArgumentException("Setting type not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable> T get(final Setting setting) {
        final String key = setting.getKey();
        final Class typeClass = setting.getTypeClass();
        final Object defaultValue = setting.getDefaultValue();
        if (typeClass.equals(Integer.class)) {
            return (T) (Integer) preferences.getInteger(key, (Integer) defaultValue);
        }
        if (typeClass.equals(Boolean.class)) {
            return (T) (Boolean) preferences.getBoolean(key, (Boolean) defaultValue);
        }
        if (typeClass.equals(Float.class)) {
            return (T) (Float) preferences.getFloat(key, (Float) defaultValue);
        }
        throw new IllegalArgumentException("Expecting wrong value type from a setting");
    }
}
