package com.robcio.soundboard2.voice;

import com.robcio.soundboard2.filter.FilterInformation;
import com.robcio.soundboard2.loader.VoiceLoader;
import com.robcio.soundboard2.utils.Maths;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
public class VoiceHolder {
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

    public void filter(final int filter, final FilterInformation filterInformation) {
        currentList.clear();
        final int packetFilter = filterInformation.getPacketFilter(filter);
        final int peopleFilter = filterInformation.getPeopleFilter(filter);
        final Set<Integer> otherFilters = filterInformation.getOtherFilters();
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
    }
}
