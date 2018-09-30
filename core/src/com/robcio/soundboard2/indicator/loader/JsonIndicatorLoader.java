package com.robcio.soundboard2.indicator.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.robcio.soundboard2.indicator.loader.model.IndicatorInfoModel;
import com.robcio.soundboard2.indicator.loader.model.IndicatorModel;

import java.util.List;


public class JsonIndicatorLoader {

    public List<IndicatorModel> load() {
        final Json json = new Json();
        final IndicatorInfoModel indicatorInfoModel = json
                .fromJson(IndicatorInfoModel.class, Gdx.files.internal("json/indicator_information.json"));
        return indicatorInfoModel.getIndicators();
    }
}
