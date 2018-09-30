package com.robcio.soundboard2.enumeration;

import com.robcio.soundboard2.voice.Voice;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;

import static com.robcio.soundboard2.constants.Strings.*;

@Getter
@AllArgsConstructor
public enum SortingType {

    PACKETS(SORT_PACKETS, 0),

    RANDOM(SORT_RANDOM, 1),

    ALPHABETHIC(SORT_ALPHABETICAL, 2),

    CONTRALPHABETHIC(SORT_ANALPHABETIC, 3);

    final private String value;

    final private int integer;

    public static SortingType fromInteger(final int i) {
        final SortingType[] values = values();
        for (final SortingType type : values) {
            if (type.getInteger() == i)
                return type;
        }
        return PACKETS;
    }

    @Override
    public String toString() {
        return value;
    }

    public static final Comparator<Voice> ALPHABETICAL_ORDER = new Comparator<Voice>() {
        @Override
        public int compare(final Voice s1, final Voice s2) {
            int res = String.CASE_INSENSITIVE_ORDER.compare(s1.getName(), s2.getName());
            if (res == 0) {
                res = s1.getName().compareTo(s2.getName());
            }
            return res;
        }
    };
    public static final Comparator<Voice> ANALPHABETICAL_ORDER = new Comparator<Voice>() {
        @Override
        public int compare(final Voice s2, final Voice s1) {
            int res = String.CASE_INSENSITIVE_ORDER.compare(s1.getName(), s2.getName());
            if (res == 0) {
                res = s1.getName().compareTo(s2.getName());
            }
            return res;
        }
    };
}
