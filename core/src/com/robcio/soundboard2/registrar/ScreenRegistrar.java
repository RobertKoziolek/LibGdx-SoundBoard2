package com.robcio.soundboard2.registrar;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.filter.FilterMap;
import com.robcio.soundboard2.gui.SoundboardScreen;
import com.robcio.soundboard2.gui.SoundboardStage;
import com.robcio.soundboard2.gui.stage.LoadStage;
import com.robcio.soundboard2.gui.stage.MainStage;
import com.robcio.soundboard2.gui.stage.OptionsStage;
import com.robcio.soundboard2.gui.stage.SplashStage;
import com.robcio.soundboard2.gui.stage.SuiteStage;
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
    private final Map<ScreenId, SoundboardScreen> map;

    public ScreenRegistrar(final ScreenChanger screenChanger,
                           final OrthographicCamera camera,
                           final VoiceLoader voiceLoader,
                           final ShareDispatcher shareDispatcher) {
        SoundboardStage.setScreenChangerAndCamera(screenChanger, camera);

        final VoiceContainer voiceContainer = new VoiceContainer(voiceLoader);
        final SuiteContainer suiteContainer = new SuiteContainer(voiceLoader);
        final VoiceSorter voiceSorter = new VoiceSorter(voiceContainer);
        final FilterMap filterMap = voiceLoader.getFilterMap();
        final IndicatorContainer indicatorContainer = new IndicatorContainer(filterMap);

        map = new HashMap<>();
        map.put(ScreenId.LOAD, SoundboardScreen.of(new LoadStage(voiceContainer, suiteContainer)));
        map.put(ScreenId.SPLASH, SoundboardScreen.of(new SplashStage()));
        map.put(ScreenId.MAIN, SoundboardScreen.of(new MainStage(voiceContainer, voiceSorter, shareDispatcher, indicatorContainer)));
        map.put(ScreenId.SUITES, SoundboardScreen.of(new SuiteStage(voiceContainer, suiteContainer)));
        map.put(ScreenId.OPTIONS, SoundboardScreen.of(new OptionsStage(voiceContainer,
                                                                       voiceSorter,
                                                                       shareDispatcher,
                                                                       indicatorContainer,
                                                                       filterMap)));
    }

    public SoundboardScreen get(final ScreenId screenId) {
        if (!map.containsKey(screenId))
            throw new IllegalArgumentException("Screen " + screenId + " not implemented yet");
        return map.get(screenId);
    }

    public void dispose() {
        for (final SoundboardScreen screen : map.values()) {
            screen.dispose();
        }
    }
}
