package com.robcio.soundboard2.voice;

import com.robcio.soundboard2.constants.Strings;
import com.robcio.soundboard2.voice.loader.VoiceLoader;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class VoiceContainer extends Observable {
    @Getter
    final private List<Voice> fullList = new LinkedList<>();
    @Getter
    final private List<Voice> currentList = new LinkedList<>();

    final private VoiceLoader voiceLoader;

    public VoiceContainer(final VoiceLoader voiceLoader) {
        this.voiceLoader = voiceLoader;
    }

    public void loadUp() {
        if (isLoaded()) {
            throw new IllegalStateException("Cannot add sounds to voice holder more than once");
        }
        final List<Voice> voiceList = voiceLoader.getVoiceList();
        fullList.addAll(voiceList);
        currentList.clear();
        currentList.addAll(voiceList);
    }

    public void partialLoadUp() {
        currentList.clear();
        currentList.addAll(voiceLoader.getLoadedVoices());
    }

    public boolean isLoaded() {
        return !fullList.isEmpty();
    }

    @Override
    public void notifyObservers() {
        setChanged();
        notifyObservers(Strings.counter(currentList.size(), fullList.size()));
    }
}
