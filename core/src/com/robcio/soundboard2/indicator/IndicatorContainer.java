package com.robcio.soundboard2.indicator;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.robcio.soundboard2.filter.FilterMap;
import com.robcio.soundboard2.indicator.loader.JsonIndicatorLoader;
import com.robcio.soundboard2.indicator.loader.model.IndicatorModel;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.Enablable;
import com.robcio.soundboard2.utils.Maths;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndicatorContainer implements Enablable {
    final private Map<Integer, Indicator> map = new HashMap<>();
    private final FilterMap filterMap;
    @Setter
    @Getter
    private boolean enabled = true;

    public IndicatorContainer(final FilterMap filterMap) {
        this.filterMap = filterMap;
    }

    public void loadUp(final Stage stageController) {
        if (!map.isEmpty()) {
            throw new IllegalStateException("Cannot load up indicators twice");
        }
        final List<IndicatorModel> modelList = new JsonIndicatorLoader().load();
        for (final IndicatorModel model : modelList) {
            final Integer filter = filterMap.get(model.getName());
            final Image image = Assets.getImage(model.getFile());
            final Indicator indicator = new Indicator(image, model.getAlign());
            map.put(filter, indicator);
            stageController.addActor(indicator.getImage());
        }
    }

    public void indicate(final int filter) {
        if (!enabled) return;
        for (final Map.Entry<Integer, Indicator> entry : map.entrySet()) {
            if (Maths.containsBit(entry.getKey(), filter)) {
                entry.getValue()
                     .start();
            }
        }
    }
}
