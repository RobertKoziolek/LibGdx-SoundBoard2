package com.robcio.soundboard2.indicator;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.robcio.soundboard2.enumeration.Setting;
import com.robcio.soundboard2.filter.FilterMap;
import com.robcio.soundboard2.indicator.loader.JsonIndicatorLoader;
import com.robcio.soundboard2.indicator.loader.model.IndicatorModel;
import com.robcio.soundboard2.indicator.player.IndicatorPlayer;
import com.robcio.soundboard2.utils.Enablable;
import com.robcio.soundboard2.utils.Settings;
import com.robcio.soundboard2.utils.assets.Assets;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndicatorContainer implements Enablable {
    private final FilterMap filterMap;
    @Getter
    private boolean enabled = Settings.get(Setting.INDICATORS_BOOLEAN);
    private IndicatorPlayer indicatorPlayer;

    public IndicatorContainer(final FilterMap filterMap) {
        this.filterMap = filterMap;
    }

    public void loadUp(final Stage stageController) {
        if (indicatorPlayer != null) {
            throw new IllegalStateException("Cannot load up indicators twice");
        }
        final Map<Integer, Indicator> indicatorMap = new HashMap<>();
        final List<IndicatorModel> modelList = new JsonIndicatorLoader().load();
        for (final IndicatorModel model : modelList) {
            final Integer filter = filterMap.get(model.getName());
            if (filter == null) continue;
            final Image image = Assets.getImage(model.getFile());
            final Indicator indicator = new Indicator(image, model.getAlign());
            indicatorMap.put(filter, indicator);
            stageController.addActor(indicator.getImage());
        }
        this.indicatorPlayer = IndicatorPlayer.getNewIndicatorPlayer(indicatorMap, filterMap);
    }

    public void indicate(final int filter) {
        if (!enabled) return;
        indicatorPlayer.indicate(filter);
    }

    public void stopAll() {
        indicatorPlayer.stopAll();
    }

    @Override
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
        Settings.put(Setting.INDICATORS_BOOLEAN, enabled);
    }
}
