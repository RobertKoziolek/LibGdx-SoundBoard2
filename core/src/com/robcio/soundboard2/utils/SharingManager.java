package com.robcio.soundboard2.utils;

public interface SharingManager {
    void share(String filePath);
    void askForSharingPermission();
    boolean isSharingAllowed();
    void setSharingAllowed(final boolean isSharingAllowed);
}
