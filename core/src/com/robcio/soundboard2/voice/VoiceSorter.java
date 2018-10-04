package com.robcio.soundboard2.voice;

import com.robcio.soundboard2.enumeration.Setting;
import com.robcio.soundboard2.enumeration.SortingType;
import com.robcio.soundboard2.utils.Settings;
import lombok.Getter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class VoiceSorter {

    private final VoiceContainer voiceContainer;

    @Getter
    private SortingType sortingType;

    public VoiceSorter(final VoiceContainer voiceContainer) {
        this.voiceContainer = voiceContainer;
        this.sortingType = SortingType.getFromSettings();
    }

    public void setSortingType(final SortingType sortingType) {
        this.sortingType = sortingType;
        Settings.put(Setting.SORT_TYPE_INTEGER, sortingType.getId());
    }

    public void sort() {
        switch (sortingType) {
            case PACKETS:
                packetSort(voiceContainer);
                break;
            case RANDOM:
                Collections.shuffle(voiceContainer.getCurrentList());
                break;
            case ALPHABETHIC:
                Collections.sort(voiceContainer.getCurrentList(), SortingType.ALPHABETICAL_ORDER);
                break;
            case ANALPHABETHIC:
                Collections.sort(voiceContainer.getCurrentList(), SortingType.ANALPHABETICAL_ORDER);
                break;
        }
    }

    private void packetSort(final VoiceContainer voiceContainer) {
        final List<Voice> fullList = voiceContainer.getFullList();
        final List<Voice> currentList = voiceContainer.getCurrentList();

        if (fullList.isEmpty()) return;

        if (currentList.size() == fullList.size()) {
            currentList.clear();
            currentList.addAll(fullList);
            return;
        }
        final List<Voice> tempList = new LinkedList<>(currentList);
        currentList.clear();
        for (final Voice voice : fullList) {
            if (tempList.contains(voice)) {
                currentList.add(voice);
            }
        }
    }
}

