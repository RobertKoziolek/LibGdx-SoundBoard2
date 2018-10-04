package com.robcio.soundboard2.desktop;


import com.robcio.soundboard2.enumeration.Setting;
import com.robcio.soundboard2.utils.Settings;
import com.robcio.soundboard2.utils.helper.Log;
import com.robcio.soundboard2.utils.dispatcher.ShareDispatcher;
import lombok.Getter;

public class DesktopShareDispatcher implements ShareDispatcher {

    private final String SHARING_TAG = "Sharing";

    @Getter
    private boolean enabled;

    @Override
    public void share(final String filePath) {
        Log.i(SHARING_TAG, filePath);
    }

    @Override
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
        Settings.put(Setting.SHARING_BOOLEAN, enabled);
    }

}
