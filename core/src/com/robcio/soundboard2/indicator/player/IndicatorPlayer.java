package com.robcio.soundboard2.indicator.player;

import com.robcio.soundboard2.filter.FilterMap;
import com.robcio.soundboard2.indicator.Indicator;
import lombok.AllArgsConstructor;

import java.util.Map;

import static com.robcio.soundboard2.indicator.player.LongIndicatorPlayer.LONG_FILTER_NAME;

@AllArgsConstructor
public abstract class IndicatorPlayer {
    final protected Map<Integer, Indicator> map;
    final protected FilterMap filterMap;

    public void stopAll() {
        for (final Indicator indicator : map.values()) {
            indicator.reset();
        }
    }

    abstract public void indicate(final int filter);

    public static IndicatorPlayer getNewIndicatorPlayer(final Map<Integer, Indicator> map, final FilterMap filterMap) {
        if (filterMap.containsKey(LONG_FILTER_NAME)) {
            return new LongIndicatorPlayer(map, filterMap);
        } else {
            return new StandardIndicatorPlayer(map, filterMap);
        }
    }
}
