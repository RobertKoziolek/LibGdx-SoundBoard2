package com.robcio.soundboard2.gui.options.component;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.robcio.soundboard2.filter.FilterInformation;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.Maths;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

import static com.robcio.soundboard2.filter.FilterMap.PERSON_PREFIX;
import static com.robcio.soundboard2.gui.constants.Strings.EMPTY_STRING;

public class FilterCheckBox extends CheckBox {
    private static FilterInformation filterInformation;

    private static Set<FilterCheckBox> set = new HashSet<>();

    @Getter
    final private int filterBit;

    private FilterCheckBox(final String text, final int filterBit) {
        super(text, Assets.getSkin());
        this.filterBit = filterBit;
        setChecked(true);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (getTapCount() == 2) {
                    leaveOnlyOneInFilterColumn();
                }
            }
        });
    }

    public static void setFilterInformation(final FilterInformation filterInformation) {
        if (FilterCheckBox.filterInformation != null) {
            throw new IllegalArgumentException("Filter information for checkboxes cannot be set twice");
        }
        FilterCheckBox.filterInformation = filterInformation;
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

    public static void setAll() {
        final int packetAndPeopleFilters = filterInformation.getPacketFilters() | filterInformation.getPeopleFilters();
        for (final FilterCheckBox checkBox : set) {
            if (Maths.containsBit(checkBox.filterBit, packetAndPeopleFilters)) {
                checkBox.setChecked(true);
            }
        }
    }

    private void leaveOnlyOneInFilterColumn() {
        int columnFilter = 0;
        if (Maths.containsBit(filterBit, filterInformation.getPacketFilters())) {
            columnFilter = filterInformation.getPacketFilters();
        } else if (Maths.containsBit(filterBit, filterInformation.getPeopleFilters())) {
            columnFilter = filterInformation.getPeopleFilters();
        }
        for (final FilterCheckBox checkBox : set) {
            if (Maths.containsBit(checkBox.filterBit, columnFilter)) {
                checkBox.setChecked(checkBox == this);
            }
        }

    }
}
