package com.robcio.soundboard2.filter;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class FilterMap extends HashMap<String, Integer> {
    public static final String PERSON_PREFIX = "P_";
    public static final String EMPTY_STRING = "";

    @Getter
    final private FilterInformation filterInformation = new FilterInformation();

    @Getter
    private int index = 1;

    @Override
    public Integer get(final Object key) {
        if (!containsKey(key)) {
            put(String.valueOf(key));
        }
        return super.get(key);
    }

    public void put(final String key) {
        filterInformation.addFilterBit(key.startsWith(PERSON_PREFIX), index);
        super.put(key, index);
        index *= 2;
    }

    public int getFilter(final List<String> filterList) {
        int filter = 0;
        for (final String filterString : filterList) {
            final int filterShort = get(filterString);
            filter = filter | filterShort;
        }
        return filter;
    }

    public Set<Entry<String, Integer>> getEntrySet() {
        return entrySet();
    }

    public void markPacketFilters() {
        int packetFilters = 0;
        for (final Entry<String, Integer> entry : entrySet()) {
            packetFilters = packetFilters | entry.getValue();
        }
        filterInformation.setPacketFilters(packetFilters);
    }
}
