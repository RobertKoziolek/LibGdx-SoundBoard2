package com.robcio.soundboard2;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.robcio.soundboard2.utils.Settings;
import com.robcio.soundboard2.utils.ShareDispatcher;
import com.robcio.soundboard2.utils.ToastMessage;
import lombok.Getter;

import java.io.File;

import static com.robcio.soundboard2.constants.Strings.*;

public class AndroidShareDispatcher implements ShareDispatcher {
    private final String PACKAGE_PATH = "/robcio/soundboard2/startSharingIntent/";
    private final String AUDIO_FORMAT = "audio/mp3";
    private final int REQUEST_CODE = 9;

    @Getter
    private boolean enabled;

    final private AndroidApplication application;

    AndroidShareDispatcher(final AndroidApplication application) {
        this.application = application;

        askForSharingPermission();
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        Settings.putBoolean(SETTING_SHARING, enabled);
        if (enabled) {
            askForSharingPermission();
            ToastMessage.showText(INSTRUCTION_SHARING);
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

    private void askForSharingPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (application.checkSelfPermission(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                application.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }
    }

}
