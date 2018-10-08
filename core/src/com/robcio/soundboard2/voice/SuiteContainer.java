package com.robcio.soundboard2.voice;

import com.robcio.soundboard2.voice.loader.VoiceLoader;
import lombok.Getter;

import java.util.List;
import java.util.Map;

public class SuiteContainer {
    @Getter
    private Map<String, List<Voice>> suiteMap;

    final private VoiceLoader voiceLoader;

    public SuiteContainer(final VoiceLoader voiceLoader) {
        this.voiceLoader = voiceLoader;
    }

    public void loadUp() {
        suiteMap = voiceLoader.getSuiteMap();
    }
}
