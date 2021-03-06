package com.robcio.soundboard2.utils.helper;

import com.badlogic.gdx.Gdx;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Log {
    private static String DEFAULT_PREFIX = "Log";

    public static void e(final String msg) {
        e(DEFAULT_PREFIX, msg);
    }

    public static void d(final String msg) {
        d(DEFAULT_PREFIX, msg);
    }

    public static void i(final String msg) {
        i(DEFAULT_PREFIX, msg);
    }

    public static void e(final String tag, final String msg) {
        Gdx.app.error(tag, msg);
    }

    public static void d(final String tag, final String msg) {
        Gdx.app.debug(tag, msg);
    }

    public static void i(final String tag, final String msg) {
        Gdx.app.log(tag, msg);
    }
}
