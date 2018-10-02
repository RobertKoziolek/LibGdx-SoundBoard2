package com.robcio.soundboard2.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.robcio.soundboard2.constants.Strings;
import lombok.AllArgsConstructor;

import java.util.Observable;

@AllArgsConstructor
public class AssetsLoader extends Observable {

    final private AssetManager assetManager;

    public boolean update() {
        final boolean isAllLoaded = assetManager.update();
        setChanged();
        notifyObservers(Strings.percentage(assetManager.getProgress()));
        return isAllLoaded;
    }

    public float getProgress() {
        return assetManager.getProgress();
    }
}
