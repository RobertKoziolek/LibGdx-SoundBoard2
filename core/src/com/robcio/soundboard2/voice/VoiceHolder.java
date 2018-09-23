package com.robcio.soundboard2.voice;

import com.robcio.soundboard2.filter.FilterInformation;
import com.robcio.soundboard2.loader.VoiceLoader;
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
            if ((packetFilter & voiceFilter) > 0 && (peopleFilter & voiceFilter) > 0) {
                for (final Integer otherFilterBit : otherFilters) {
                    if ((otherFilterBit & voiceFilter) > 0 && (otherFilterBit & filter) == 0) {
                        continue voiceLoop;
                    }
                }

                currentList.add(voice);
            }
        }
    }
}
