package com.robcio.soundboard2.indicator.player;

import com.robcio.soundboard2.filter.FilterMap;
import com.robcio.soundboard2.indicator.Indicator;
import com.robcio.soundboard2.utils.helper.Maths;

import java.util.Map;

class StandardIndicatorPlayer extends IndicatorPlayer {

    StandardIndicatorPlayer(final Map<Integer, Indicator> map, final FilterMap filterMap) {
        super(map, filterMap);
    }

    @Override
    public void indicate(final int filter) {
        for (final Map.Entry<Integer, Indicator> entry : map.entrySet()) {
            if (Maths.containsBit(entry.getKey(), filter)) {
                entry.getValue()
                     .start(false);
            }
        }
    }
}
