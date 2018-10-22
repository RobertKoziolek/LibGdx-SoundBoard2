package com.robcio.soundboard2.gui.component;

import com.robcio.soundboard2.filter.FilterInformation;
import com.robcio.soundboard2.utils.helper.Maths;

import java.util.HashSet;
import java.util.Set;

import static com.robcio.soundboard2.constants.Strings.EMPTY_STRING;
import static com.robcio.soundboard2.filter.FilterMap.PERSON_PREFIX;

public class FilterCheckBoxContainer {

    private final FilterInformation filterInformation;
    private final Set<FilterCheckBox> checkBoxSet = new HashSet<>();

    private final FilterCheckBox.FilterBoxCommand filterBoxCommand;

    public FilterCheckBoxContainer(final FilterInformation filterInformation) {
        this.filterInformation = filterInformation;
        filterBoxCommand = new FilterCheckBox.FilterBoxCommand() {
            @Override
            public void execute(FilterCheckBox filterCheckBox) {
                uncheckAllExcept(filterCheckBox);
            }
        };
    }

    public FilterCheckBox createFilterCheckBox(final String text, final Integer filterBit) {
        final FilterCheckBox filterCheckBox = new FilterCheckBox(text.replace(PERSON_PREFIX, EMPTY_STRING),
                                                                 filterBit,
                                                                 filterBoxCommand);
        checkBoxSet.add(filterCheckBox);
        return filterCheckBox;
    }

    public int getCurrentFilter() {
        int filter = 0;
        for (final FilterCheckBox box : checkBoxSet) {
            if (box.isChecked()) {
                filter = filter | box.getFilterBit();
            }
        }
        return filter;
    }

    public void setAll() {
        final int packetAndPeopleFilters = filterInformation.getPacketFilters() | filterInformation.getPeopleFilters();
        for (final FilterCheckBox checkBox : checkBoxSet) {
            if (Maths.containsBit(checkBox.getFilterBit(), packetAndPeopleFilters)) {
                checkBox.setChecked(true);
            }
        }
    }

    private void uncheckAllExcept(final FilterCheckBox filterCheckBox) {
        final int filterBit = filterCheckBox.getFilterBit();

        if (Maths.containsBit(filterBit, filterInformation.getPacketFilters())) {
            uncheckAll(filterInformation.getPacketFilters());
            filterCheckBox.setChecked(true);
        } else if (Maths.containsBit(filterBit, filterInformation.getPeopleFilters())) {
            uncheckAll(filterInformation.getPeopleFilters());
            filterCheckBox.setChecked(true);
        }
    }

    private void uncheckAll(final int columnFilter) {
        for (final FilterCheckBox checkBox : checkBoxSet){
            if (Maths.containsBit(checkBox.getFilterBit(), columnFilter)){
                checkBox.setChecked(false);
            }
        }
    }
}
