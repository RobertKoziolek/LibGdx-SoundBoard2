package com.robcio.soundboard2.indicator.player;

import com.robcio.soundboard2.filter.FilterMap;
import com.robcio.soundboard2.indicator.Indicator;
import com.robcio.soundboard2.utils.helper.Maths;

import java.util.Map;

public class LongIndicatorPlayer extends IndicatorPlayer {

    static final String LONG_FILTER_NAME = "LONG";

    LongIndicatorPlayer(final Map<Integer, Indicator> map, final FilterMap filterMap) {
        super(map, filterMap);
    }

    @Override
    public void indicate(final int filter) {
        final boolean isLong = Maths.containsBit(filter, filterMap.get(LONG_FILTER_NAME));
        for (final Map.Entry<Integer, Indicator> entry : map.entrySet()) {
            if (Maths.containsBit(entry.getKey(), filter)) {
                entry.getValue()
                     .start(isLong);
            }
        }
    }
}
