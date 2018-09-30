package com.robcio.soundboard2.desktop;


import com.robcio.soundboard2.utils.Log;
import com.robcio.soundboard2.utils.ShareDispatcher;
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
        Log.i(SHARING_TAG, String.valueOf(enabled));
        this.enabled = enabled;
    }

}
