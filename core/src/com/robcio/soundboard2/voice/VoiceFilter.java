package com.robcio.soundboard2.voice;

import com.robcio.soundboard2.filter.FilterInformation;
import com.robcio.soundboard2.utils.helper.Maths;

import java.util.List;
import java.util.Set;

public class VoiceFilter {

    public void filter(final VoiceContainer voiceContainer, final FilterInformation filterInformation, final int filter) {
        final List<Voice> currentList = voiceContainer.getCurrentList();
        final List<Voice> fullList = voiceContainer.getFullList();
        final int packetFilter = filterInformation.getPacketFilter(filter);
        final int peopleFilter = filterInformation.getPeopleFilter(filter);
        final Set<Integer> otherFilters = filterInformation.getOtherFilters();

        currentList.clear();
        voiceLoop:
        for (final Voice voice : fullList) {
            final Integer voiceFilter = voice.getFilter();
            if (Maths.containsBit(packetFilter, voiceFilter) && Maths.containsBit(peopleFilter, voiceFilter)) {
                for (final Integer otherFilterBit : otherFilters) {
                    if (Maths.containsBit(otherFilterBit, voiceFilter) && !Maths.containsBit(otherFilterBit, filter)) {
                        continue voiceLoop;
                    }
                }
                currentList.add(voice);
            }
        }
        voiceContainer.notifyObservers();
    }
}
