package com.robcio.soundboard2;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;
import com.robcio.soundboard2.utils.dispatcher.ToastDispatcher;

import java.util.HashMap;
import java.util.Map;

public class AndroidToastMaker implements ToastDispatcher.ToastMaker {

    private final Handler handler;
    private final Context context;
    private final Map<String, Toast> toastMap;

    public AndroidToastMaker(final Handler handler, final Context context) {
        this.handler = handler;
        this.context = context;
        this.toastMap = new HashMap<>();
    }

    private Toast getToast(final String text) {
        if (toastMap.containsKey(text)) {
            return toastMap.get(text);
        }
        final Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toastMap.put(text, toast);
        return toast;
    }

    @Override
    public void show(final String text) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getToast(text).show();
            }
        });
    }
}
