package com.robcio.soundboard2.voice;

import com.robcio.soundboard2.enumeration.SortingType;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
public class VoiceSorter {

    private SortingType sortingType = SortingType.PACKETS;

    public void sort(final VoiceContainer voiceContainer) {
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
            case CONTRALPHABETHIC:
                Collections.sort(voiceContainer.getCurrentList(), SortingType.ANALPHABETICAL_ORDER);
                break;
        }
    }

    private void packetSort(VoiceContainer voiceContainer) {
        final List<Voice> fullList = voiceContainer.getFullList();
        final List<Voice> currentList = voiceContainer.getCurrentList();

        if (currentList.size() == fullList.size()) {
            currentList.addAll(fullList);
            return;
        }
        final List<Voice> tempList = new LinkedList<>(currentList);
        currentList.clear();
        for (final Voice voice : fullList){
            if (tempList.contains(voice)){
                currentList.add(voice);
            }
        }
    }
}
