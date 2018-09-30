package com.robcio.soundboard2.indicator.loader.model;

import com.robcio.soundboard2.enumeration.IndicatorAlign;
import lombok.Getter;

@Getter
public class IndicatorModel {
    private String name;
    private String file;
    private IndicatorAlign align;
}
