package com.robcio.soundboard2.gui.component;

import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.robcio.soundboard2.enumeration.SortingType;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.Settings;

import static com.robcio.soundboard2.constants.Strings.SETTING_SORT_TYPE;

public class SortingSelectBox extends SelectBox<SortingType> {

    public SortingSelectBox() {
        super(Assets.getSkin());
        setAvailableSortingTypes();
    }

    private void setAvailableSortingTypes() {
        setItems(SortingType.values());
        setSelected(SortingType.fromInteger(Settings.getInteger(SETTING_SORT_TYPE, SortingType.PACKETS.getInteger())));
    }
}
