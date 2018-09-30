package com.robcio.soundboard2.voice;

import com.robcio.soundboard2.constants.Strings;
import com.robcio.soundboard2.voice.loader.VoiceLoader;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

@Getter
public class VoiceContainer extends Observable {
    final private List<Voice> fullList = new LinkedList<>();
    final private List<Voice> currentList = new LinkedList<>();

    public void loadUp(final VoiceLoader voiceLoader) {
        if (!fullList.isEmpty()) {
            throw new IllegalStateException("Cannot add sounds to voice holder more than once");
        }
        final List<Voice> voiceList = voiceLoader.getVoiceList();
        fullList.addAll(voiceList);
        currentList.addAll(voiceList);
    }

    @Override
    public void notifyObservers() {
        setChanged();
        notifyObservers(Strings.counter(currentList.size(), fullList.size()));
    }
}
