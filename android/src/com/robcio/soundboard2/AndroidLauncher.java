package com.robcio.soundboard2;

import android.os.Bundle;
import android.widget.Toast;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.robcio.soundboard2.utils.Settings;
import com.robcio.soundboard2.utils.ToastMessage;

import static com.robcio.soundboard2.constants.Strings.SETTING_SHARING;

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = false;
        config.useCompass = false;
        final AndroidShareDispatcher androidShareDispatcher = new AndroidShareDispatcher(this);
        initialize(new SoundBoard2(androidShareDispatcher), config);
        androidShareDispatcher.setEnabled(Settings.getBoolean(SETTING_SHARING, false));
        ToastMessage.setToastMaker(new ToastMessage.ToastMaker() {
            @Override
            public void show(final String text) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AndroidLauncher.this, text, Toast.LENGTH_LONG)
                             .show();
                    }
                });
            }
        });
    }
}
