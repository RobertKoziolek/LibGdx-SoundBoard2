package com.robcio.soundboard2;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.robcio.soundboard2.enumeration.Setting;
import com.robcio.soundboard2.utils.Settings;
import com.robcio.soundboard2.utils.dispatcher.ShareDispatcher;
import com.robcio.soundboard2.utils.dispatcher.ToastDispatcher;

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = false;
        config.useCompass = false;

        final ShareDispatcher shareDispatcher = new AndroidShareDispatcher(this);
        initialize(new SoundBoard2(shareDispatcher), config);

        final Boolean sharingEnabled = Settings.get(Setting.SHARING_BOOLEAN);
        shareDispatcher.setEnabled(sharingEnabled);

        ToastDispatcher.setToastMaker(new AndroidToastMaker(handler, this));
    }
}
