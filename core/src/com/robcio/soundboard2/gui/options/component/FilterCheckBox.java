package com.robcio.soundboard2.gui.options.component;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.robcio.soundboard2.utils.Assets;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

import static com.robcio.soundboard2.filter.FilterMap.PERSON_PREFIX;
import static com.robcio.soundboard2.gui.constants.Strings.EMPTY_STRING;

public class FilterCheckBox extends CheckBox {
    static private Set<FilterCheckBox> set = new HashSet<>();

    @Getter
    final private int filterBit;

    private FilterCheckBox(final String text, final int filterBit) {
        super(text, Assets.getSkin());
        this.filterBit = filterBit;
        setChecked(true);
    }

    public static FilterCheckBox of(final String text, final Integer filterBit) {
        final FilterCheckBox filterCheckBox = new FilterCheckBox(text.replace(PERSON_PREFIX, EMPTY_STRING), filterBit);
        set.add(filterCheckBox);
        return filterCheckBox;
    }

    public static int getCurrentFilter() {
        int filter = 0;
        for (final FilterCheckBox box : set) {
            if (box.isChecked()) {
                filter = filter | box.getFilterBit();
            }
        }
        return filter;
    }
}
