package com.robcio.soundboard2.voice;

import com.robcio.soundboard2.enumeration.SortingType;
import com.robcio.soundboard2.utils.Settings;
import lombok.Getter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.robcio.soundboard2.constants.Strings.SETTING_SORT_TYPE;

public class VoiceSorter {

    private final VoiceContainer voiceContainer;

    @Getter
    private SortingType sortingType;

    public VoiceSorter(final VoiceContainer voiceContainer) {
        this.voiceContainer = voiceContainer;
        this.sortingType = SortingType.fromInteger(
                Settings.getInteger(SETTING_SORT_TYPE, SortingType.PACKETS.getInteger()));
    }

    public void setSortingType(final SortingType sortingType) {
        this.sortingType = sortingType;
        Settings.putInteger(SETTING_SORT_TYPE, sortingType.getInteger());
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

