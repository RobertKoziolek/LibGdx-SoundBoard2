package com.robcio.soundboard2.filter;

import com.robcio.soundboard2.utils.helper.Maths;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class FilterInformation {

    private int packetFilters;
    private int peopleFilters;
    @Getter
    private Set<Integer> otherFilters = new HashSet<>();

    void setPacketFilters(final int packetFilters) {
        this.packetFilters = packetFilters;
        this.otherFilters.clear();
    }

    public final int getPacketFilters() {
        if (packetFilters == 0) {
            throw new IllegalStateException("Packet filters not set");
        }
        return packetFilters;
    }

    public final int getPeopleFilters() {
        if (peopleFilters == 0) {
            throw new IllegalStateException("Person filters not set");
        }
        return peopleFilters;
    }

    public int getPacketFilter(final int filter) {
        return packetFilters & filter;
    }

    public int getPeopleFilter(final int filter) {
        return peopleFilters & filter;
    }

    void addFilterBit(final boolean isPersonBit, final int index) {
        if (isPersonBit) {
            peopleFilters = peopleFilters | index;
        } else if (!Maths.containsBit(packetFilters, index)) {
            otherFilters.add(index);
        }
    }
}
