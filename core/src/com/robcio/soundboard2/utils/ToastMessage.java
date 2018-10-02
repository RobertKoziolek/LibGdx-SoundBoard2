package com.robcio.soundboard2.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ToastMessage {

    private static final String TOAST_TAG = "ToastMessage";
    private static ToastMaker toastMaker;

    public static void setToastMaker(final ToastMaker toastMaker){
        ToastMessage.toastMaker = toastMaker;
    }

    public static void showText(final String text) {
        if (toastMaker != null) {
            toastMaker.show(text);
        }
        Log.i(TOAST_TAG, text);
    }

    public interface ToastMaker {
        void show(final String text);
    }
}
