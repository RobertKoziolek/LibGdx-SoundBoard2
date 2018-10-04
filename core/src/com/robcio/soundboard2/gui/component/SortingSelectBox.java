package com.robcio.soundboard2.gui.component;

import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.robcio.soundboard2.enumeration.SortingType;
import com.robcio.soundboard2.utils.assets.Assets;

public class SortingSelectBox extends SelectBox<SortingType> {

    public SortingSelectBox() {
        super(Assets.getSkin());
        setAvailableSortingTypes();
    }

    private void setAvailableSortingTypes() {
        setItems(SortingType.values());
        setSelected(SortingType.getFromSettings());
    }
}
