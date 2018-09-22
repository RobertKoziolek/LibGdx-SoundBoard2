package com.robcio.soundboard2.registrar;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.gui.main.MainScreen;
import com.robcio.soundboard2.gui.splash.SplashScreen;
import com.robcio.soundboard2.voice.Voice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenRegistrar {
    private final Map<ScreenId, AbstractScreen> map;
    private AbstractScreen current;

    public ScreenRegistrar(final OrthographicCamera camera,
                           final List<Voice> voiceModelList) {
        map = new HashMap<>();
        map.put(ScreenId.SPLASH, new SplashScreen(camera));
        map.put(ScreenId.MAIN, new MainScreen(camera, voiceModelList));
//        map.put(ScreenId.LOADING, new LoadingScreen(camera));
//        map.put(ScreenId.OPTIONS, new OptionsScreen(camera));
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
        for (final AbstractScreen screen : map.values()) {
            screen.dispose();
        }
    }
}
