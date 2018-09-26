package com.robcio.soundboard2.desktop;


import com.robcio.soundboard2.utils.Log;
import com.robcio.soundboard2.utils.SharingManager;
import lombok.Getter;

public class DesktopSharingManager implements SharingManager {

    private final String SHARING_TAG = "Sharing";
    private final String SHARE_PERMISSION = "Asking for permission to share";

    @Getter
    private boolean isSharingAllowed;

    @Override
    public void share(final String filePath) {
        Log.i(SHARING_TAG, filePath);
    }

    @Override
    public void askForSharingPermission() {
        Log.i(SHARING_TAG, SHARE_PERMISSION);
    }

    @Override
    public void setSharingAllowed(final boolean isSharingAllowed) {
        Log.i(SHARING_TAG, String.valueOf(isSharingAllowed));
        this.isSharingAllowed = isSharingAllowed;
    }

}
