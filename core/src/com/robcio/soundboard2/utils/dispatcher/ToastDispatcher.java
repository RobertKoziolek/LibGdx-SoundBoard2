package com.robcio.soundboard2.utils.dispatcher;

import com.robcio.soundboard2.utils.helper.Log;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ToastDispatcher {

    private static final String TOAST_TAG = "ToastDispatcher";
    private static ToastMaker toastMaker;

    public static void setToastMaker(final ToastMaker toastMaker){
        ToastDispatcher.toastMaker = toastMaker;
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
