package com.robcio.soundboard2.registrar;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.gui.splash.SplashScreen;

import java.util.HashMap;
import java.util.Map;

public class ScreenRegistrar {
    private final Map<ScreenId, AbstractScreen> map;
    private AbstractScreen current;

    public ScreenRegistrar(final OrthographicCamera camera) {
        map = new HashMap<>();
        map.put(ScreenId.SPLASH, new SplashScreen(camera));
//        map.put(ScreenId.LOADING, );
//        map.put(ScreenId.MAIN, );
//        map.put(ScreenId.OPTIONS, );
    }

    public AbstractScreen get(final ScreenId screenId) {
        if (!map.containsKey(screenId))
            throw new IllegalArgumentException("ScreenId " + screenId + " not implemented yet");
        current = map.get(screenId);
        return current;
    }

    public AbstractScreen getCurrent() {
        if (current == null) throw new IllegalStateException("Current screen cannot be null");
        return current;
    }

    public void dispose() {
        for (final AbstractScreen screen : map.values()){
            screen.dispose();
        }
    }
}
