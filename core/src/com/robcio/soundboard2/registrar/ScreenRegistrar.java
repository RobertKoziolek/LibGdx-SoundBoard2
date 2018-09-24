package com.robcio.soundboard2.registrar;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.filter.FilterMap;
import com.robcio.soundboard2.gui.AbstractScreen;
import com.robcio.soundboard2.gui.StageController;
import com.robcio.soundboard2.gui.load.LoadScreen;
import com.robcio.soundboard2.gui.main.MainScreen;
import com.robcio.soundboard2.gui.options.OptionsScreen;
import com.robcio.soundboard2.gui.splash.SplashScreen;
import com.robcio.soundboard2.loader.VoiceLoader;
import com.robcio.soundboard2.utils.ScreenChanger;
import com.robcio.soundboard2.voice.VoiceHolder;

import java.util.HashMap;
import java.util.Map;

public class ScreenRegistrar {
    private final Map<ScreenId, AbstractScreen> map;
    private AbstractScreen current;

    public ScreenRegistrar(final ScreenChanger screenChanger,
                           final OrthographicCamera camera,
                           final VoiceLoader voiceLoader) {
        StageController.setScreenChangerAndCamera(screenChanger, camera);
        final VoiceHolder voiceHolder = new VoiceHolder();
        final FilterMap filterMap = voiceLoader.getFilterMap();
        map = new HashMap<>();
        map.put(ScreenId.LOAD, new LoadScreen(voiceLoader, voiceHolder));
        map.put(ScreenId.SPLASH, new SplashScreen());
        map.put(ScreenId.MAIN, new MainScreen(voiceHolder));
        map.put(ScreenId.OPTIONS, new OptionsScreen(voiceHolder, filterMap));
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
