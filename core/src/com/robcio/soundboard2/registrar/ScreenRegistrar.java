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
import com.robcio.soundboard2.gui.suite.SuiteScreen;
import com.robcio.soundboard2.indicator.IndicatorContainer;
import com.robcio.soundboard2.utils.ScreenChanger;
import com.robcio.soundboard2.utils.dispatcher.ShareDispatcher;
import com.robcio.soundboard2.voice.SuiteContainer;
import com.robcio.soundboard2.voice.VoiceContainer;
import com.robcio.soundboard2.voice.VoiceSorter;
import com.robcio.soundboard2.voice.loader.VoiceLoader;

import java.util.HashMap;
import java.util.Map;

public class ScreenRegistrar {
    private final Map<ScreenId, AbstractScreen> map;
    private AbstractScreen current;

    public ScreenRegistrar(final ScreenChanger screenChanger,
                           final OrthographicCamera camera,
                           final VoiceLoader voiceLoader,
                           final ShareDispatcher shareDispatcher) {
        StageController.setScreenChangerAndCamera(screenChanger, camera);

        final VoiceContainer voiceContainer = new VoiceContainer(voiceLoader);
        final SuiteContainer suiteContainer = new SuiteContainer(voiceLoader);
        final VoiceSorter voiceSorter = new VoiceSorter(voiceContainer);
        final FilterMap filterMap = voiceLoader.getFilterMap();
        final IndicatorContainer indicatorContainer = new IndicatorContainer(filterMap);

        map = new HashMap<>();
        map.put(ScreenId.LOAD, new LoadScreen(voiceContainer, suiteContainer));
        map.put(ScreenId.SPLASH, new SplashScreen());
        map.put(ScreenId.MAIN, new MainScreen(voiceContainer, voiceSorter, shareDispatcher, indicatorContainer));
        map.put(ScreenId.SUITES, new SuiteScreen(voiceContainer, suiteContainer));
        map.put(ScreenId.OPTIONS, new OptionsScreen(voiceContainer,
                                                    voiceSorter,
                                                    shareDispatcher,
                                                    indicatorContainer,
                                                    filterMap));
    }

    public AbstractScreen get(final ScreenId screenId) {
        if (!map.containsKey(screenId))
            throw new IllegalArgumentException("Screen " + screenId + " not implemented yet");
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
