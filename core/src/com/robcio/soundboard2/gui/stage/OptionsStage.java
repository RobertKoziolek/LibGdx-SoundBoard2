package com.robcio.soundboard2.gui.stage;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.enumeration.Setting;
import com.robcio.soundboard2.filter.FilterMap;
import com.robcio.soundboard2.gui.SoundboardStage;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.gui.assembler.*;
import com.robcio.soundboard2.gui.component.FilterCheckBox;
import com.robcio.soundboard2.gui.component.FilterCheckBoxContainer;
import com.robcio.soundboard2.gui.component.SortingSelectBox;
import com.robcio.soundboard2.utils.Command;
import com.robcio.soundboard2.utils.Enablable;
import com.robcio.soundboard2.utils.assets.Assets;
import com.robcio.soundboard2.utils.helper.Maths;
import com.robcio.soundboard2.voice.VoiceContainer;
import com.robcio.soundboard2.voice.VoiceFilter;
import com.robcio.soundboard2.voice.VoiceSorter;

import java.util.Map;

import static com.robcio.soundboard2.constants.Numeral.*;
import static com.robcio.soundboard2.constants.Strings.*;

public class OptionsStage extends SoundboardStage {

    private final VoiceContainer voiceContainer;
    private final VoiceSorter voiceSorter;
    private final Enablable sharingEnablable;
    private final Enablable indicatorEnablable;
    private final FilterMap filterMap;

    private final EventListener filterClickListener;
    private final VoiceFilter voiceFilter;
    private final FilterCheckBoxContainer filterCheckBoxContainer;

    public OptionsStage(final VoiceContainer voiceContainer,
                        final VoiceSorter voiceSorter,
                        final Enablable sharingEnablable,
                        final Enablable indicatorEnablable,
                        final FilterMap filterMap) {
        super();
        this.voiceContainer = voiceContainer;
        this.voiceSorter = voiceSorter;
        this.sharingEnablable = sharingEnablable;
        this.indicatorEnablable = indicatorEnablable;
        this.filterMap = filterMap;

        this.filterClickListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                filter();
            }
        };
        this.voiceFilter = new VoiceFilter();
        this.filterCheckBoxContainer = new FilterCheckBoxContainer(filterMap.getFilterInformation());
    }

    @Override
    protected void backKeyDown() {
        changeScreenToMain();
    }

    @Override
    protected void show() {
        StageAnimation.enterFromTop(this);
    }

    private void changeScreenToMain() {
        changeScreen(ScreenId.MAIN, StageAnimation.exitToBot());
    }

    @Override
    protected void buildStage() {
        final Table rootTable = TableAssembler.table()
                                              .alignTop()
                                              .fillParent()
                                              .assemble();

        final Actor topBar = getTopBar();
        final Actor optionsPane = getOptionsPane();
        final Actor counter = getCounter();

        rootTable.add(topBar)
                 .row();
        rootTable.add(optionsPane)
                 .width(WIDTH)
                 .height(getSizeHolder().NO_TOP_AND_BOTTOM_HEIGHT)
                 .row();
        rootTable.add(counter)
                 .width(WIDTH)
                 .height(getSizeHolder().UNIT_HEIGHT);
        addActor(rootTable);
    }

    private Actor getTopBar() {
        final Button suitesButton = TextButtonAssembler.buttonOf(SUITES_BUTTON)
                                                     .withClickCommand(new Command() {
                                                         @Override
                                                         public void execute() {
                                                             changeScreen(ScreenId.SUITES, StageAnimation.exitToLeft());
                                                         }
                                                     })
                                                     .withSize(THIRD_WIDTH, getSizeHolder().UNIT_HEIGHT)
                                                     .assemble();
        final Button allFiltersButton = TextButtonAssembler.buttonOf(ALL_FILTERS_BUTTON)
                                                           .shakeStage(this)
                                                           .withClickCommand(new Command() {
                                                               @Override
                                                               public void execute() {
                                                                   filterCheckBoxContainer.setAll();
                                                                   filter();
                                                               }
                                                           })
                                                           .withSize(THIRD_WIDTH, getSizeHolder().UNIT_HEIGHT)
                                                           .assemble();
        final Button backButton = TextButtonAssembler.buttonOf(BACK_BUTTON)
                                                     .withClickCommand(new Command() {
                                                         @Override
                                                         public void execute() {
                                                             changeScreenToMain();
                                                         }
                                                     })
                                                     .withSize(THIRD_WIDTH, getSizeHolder().UNIT_HEIGHT)
                                                     .assemble();

        return TableAssembler.tableOf(suitesButton, allFiltersButton, backButton)
                             .assemble();
    }

    private void filter() {
        voiceFilter.filter(voiceContainer, filterMap.getFilterInformation(),
                           filterCheckBoxContainer.getCurrentFilter());
    }

    private Actor getOptionsPane() {
        final Table optionsTable = TableAssembler.table()
                                                 .alignTop()
                                                 .assemble();
        fillInFilterOptions(optionsTable);
        fillInOtherOptions(optionsTable);
        return PaneAssembler.paneOf(optionsTable)
                            .withScrollingDisabledX()
                            .dontCancelTouchFocus()
                            .assemble();
    }

    private void fillInOtherOptions(final Table optionsTable) {
        optionsTable.add(OPTIONS_LABEL)
                    .row();
        fillInEnablable(optionsTable, SHARING_LABEL, sharingEnablable);
        fillInSortOption(optionsTable);
        fillInEnablable(optionsTable, INDICATOR_LABEL, indicatorEnablable);
        fillInSizeSlider(optionsTable);
    }

    private void fillInSizeSlider(final Table optionsTable) {
        final Label label = LabelAssembler.labelOf(SEGMENT_SIZE_LABEL)
                                          .assemble();

        final Slider slider = SliderAssembler.sliderOf(MIN_SEGMENT_SIZE, MAX_SEGMENT_SIZE, SEGMENT_SIZE_STEP)
                                             .withSetting(Setting.SEGMENT_SIZE_FLOAT)
                                             .withChangeText(INSTRUCTION_RESTART_NEEDED)
                                             .assemble();

        optionsTable.add(label)
                    .height(OPTION_HEIGHT)
                    .row();
        optionsTable.add(slider)
                    .width(ALMOST_WIDTH)
                    .height(OPTION_HEIGHT)
                    .row();
    }

    private void fillInSortOption(final Table optionsTable) {
        final Label label = LabelAssembler.labelOf(SORT_LABEL)
                                          .assemble();
        final SortingSelectBox sortingSelectBox = new SortingSelectBox();
        sortingSelectBox.setWidth(HALF_WIDTH);
        sortingSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                voiceSorter.setSortingType(sortingSelectBox.getSelected());
            }
        });

        final Table table = TableAssembler.tableOf(label, sortingSelectBox)
                                          .assemble();
        optionsTable.add(table)
                    .width(WIDTH)
                    .height(OPTION_HEIGHT)
                    .row();
    }

    private void fillInEnablable(final Table optionsTable, final String text, final Enablable enablable) {
        final CheckBox checkBox = new CheckBox(text, Assets.getSkin());
        checkBox.setChecked(enablable.isEnabled());
        checkBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                enablable.setEnabled(checkBox.isChecked());
            }
        });
        optionsTable.add(checkBox)
                    .height(OPTION_HEIGHT)
                    .row();
    }

    private void fillInFilterOptions(final Table optionsTable) {
        final Table firstColumn = TableAssembler.table(PACKETS_LABEL)
                                                .alignTop()
                                                .assemble();
        final Table secondColumn = TableAssembler.table(PEOPLE_LABEL)
                                                 .alignTop()
                                                 .assemble();
        final Table thirdColumn = TableAssembler.table(FILTERS_LABEL)
                                                .alignTop()
                                                .assemble();

        buildFilterCheckBoxes(firstColumn, secondColumn, thirdColumn);
        final Table doubleColumn = TableAssembler.equalizedColumns(OPTION_HEIGHT, firstColumn, secondColumn)
                                                 .assemble();

        optionsTable.add(doubleColumn)
                    .width(WIDTH)
                    .row();
        optionsTable.add(thirdColumn)
                    .width(WIDTH)
                    .row();
    }

    private void buildFilterCheckBoxes(final Table packetColumn, final Table peopleColumn, final Table otherColumn) {
        final int packetFilters = filterMap.getFilterInformation()
                                           .getPacketFilters();
        final int personFilters = filterMap.getFilterInformation()
                                           .getPeopleFilters();
        for (final Map.Entry<String, Integer> entry : filterMap.getEntrySet()) {
            if (Maths.containsBit(entry.getValue(), packetFilters)) {
                addFilterCheckBox(entry, packetColumn);
            } else if (Maths.containsBit(entry.getValue(), personFilters)) {
                addFilterCheckBox(entry, peopleColumn);
            } else {
                addFilterCheckBox(entry, otherColumn);
            }
        }
    }

    private void addFilterCheckBox(final Map.Entry<String, Integer> entry, final Table column) {
        final FilterCheckBox filterCheckBox = filterCheckBoxContainer.createFilterCheckBox(entry.getKey(),
                                                                                           entry.getValue());
        filterCheckBox.addListener(filterClickListener);
        column.add(filterCheckBox)
              .width(HALF_WIDTH)
              .height(OPTION_HEIGHT)
              .row();
    }

    private Actor getCounter() {
        final Label counter = LabelAssembler.labelOf(ALL_FILTERS_BUTTON)
                                            .observing(voiceContainer)
                                            .assemble();
        return PaneAssembler.paneOfInTable(counter)
                            .withScrollingDisabled()
                            .assemble();
    }
}
