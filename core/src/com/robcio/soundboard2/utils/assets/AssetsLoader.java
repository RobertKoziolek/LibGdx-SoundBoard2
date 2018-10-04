package com.robcio.soundboard2.utils.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.robcio.soundboard2.constants.Strings;
import com.robcio.soundboard2.voice.VoiceContainer;

import java.util.Observable;

public class AssetsLoader extends Observable {

    final private AssetManager assetManager;
    private boolean done;

    AssetsLoader(final AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override
    public void notifyObservers() {
        if (!done) {
            setChanged();
            notifyObservers(Strings.percentage(assetManager.getProgress()));
        }
    }

    public float getProgress() {
        return assetManager.getProgress();
    }

    public void finishLoading(final VoiceContainer voiceContainer) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                assetManager.finishLoading();
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        voiceContainer.loadUp();
                        notifyObservers();
                        deleteObservers();
                        done = true;
                    }
                });
            }
        }).start();
    }
}
