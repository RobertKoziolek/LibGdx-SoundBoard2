package com.robcio.soundboard2;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.robcio.soundboard2.utils.SharingManager;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

import static com.robcio.soundboard2.gui.constants.Strings.SHARE_QUESTION;
import static com.robcio.soundboard2.gui.constants.Strings.SHARE_SUBJECT;

public class AndroidSharingManager implements SharingManager {
    private final String PACKAGE_PATH = "/robcio/soundboard2/startSharingIntent/";
    private final String AUDIO_FORMAT = "audio/mp3";
    private final int REQUEST_CODE = 9;

    @Getter
    @Setter
    private boolean isSharingAllowed;

    final private AndroidApplication application;

    AndroidSharingManager(final AndroidApplication application) {
        this.application = application;

        askForSharingPermission();
    }

    public void askForSharingPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (application.checkSelfPermission(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                application.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }
    }

    @Override
    public void share(final String filePath) {
        Gdx.files.internal(filePath)
                 .copyTo(Gdx.files.external(PACKAGE_PATH + filePath));
        final File file = new File(Environment.getExternalStorageDirectory()
                                              .getPath() + PACKAGE_PATH + filePath);
        startSharingIntent(Uri.fromFile(file));
    }

    private void startSharingIntent(final Uri uri) {
        final Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, SHARE_SUBJECT);
        sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
        sendIntent.setType(AUDIO_FORMAT);
        application.startActivity(Intent.createChooser(sendIntent, SHARE_QUESTION));
    }

}
