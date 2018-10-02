package com.robcio.soundboard2.gui.options;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.filter.FilterMap;
import com.robcio.soundboard2.gui.StageController;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.gui.assembler.LabelAssembler;
import com.robcio.soundboard2.gui.assembler.PaneAssembler;
import com.robcio.soundboard2.gui.assembler.TableAssembler;
import com.robcio.soundboard2.gui.assembler.TextButtonAssembler;
import com.robcio.soundboard2.gui.component.FilterCheckBox;
import com.robcio.soundboard2.gui.component.SortingSelectBox;
import com.robcio.soundboard2.utils.*;
import com.robcio.soundboard2.voice.VoiceFilter;
import com.robcio.soundboard2.voice.VoiceContainer;
import com.robcio.soundboard2.voice.VoiceSorter;

import java.util.Map;

import static com.robcio.soundboard2.SoundBoard2.WIDTH;
import static com.robcio.soundboard2.constants.Numeral.*;
import static com.robcio.soundboard2.constants.Strings.*;

class OptionsStageController extends StageController {


    private final VoiceContainer voiceContainer;
    private final VoiceSorter voiceSorter;
    private final Enablable sharingEnablable;
    private final Enablable indicatorEnablable;
    private final FilterMap filterMap;

    private final EventListener filterClickListener;
    private final VoiceFilter voiceFilter;
    private final FilterCheckBoxContainer filterCheckBoxContainer;

    OptionsStageController(final VoiceContainer voiceContainer,
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

    private void changeScreenToMain() {
        changeScreen(ScreenId.MAIN,
                     StageAnimation.exitToBot());
    }

    void buildStage() {
        final Table rootTable = TableAssembler.table()
                                              .align(Align.top)
                                              .fillParent()
                                              .assemble();

        final Actor topBar = getTopBar();
        final Actor optionsPane = getOptionsPane();
        final Actor counter = getCounter();

        rootTable.add(topBar)
                 .row();
        rootTable.add(optionsPane)
                 .width(WIDTH)
                 .height(ALMOST_HEIGHT)
                 .row();
        rootTable.add(counter)
                 .width(WIDTH)
                 .height(UNIT_HEIGHT);
        addActor(rootTable);
    }

    private Actor getTopBar() {
        final Button allFiltersButton = TextButtonAssembler.buttonOf(ALL_FILTERS_BUTTON)
                                                           .shakeStage(this)
                                                           .withClickCommand(new Command() {
                                                               @Override
                                                               public void execute() {
                                                                   filterCheckBoxContainer.setAll();
                                                                   filter();
                                                               }
                                                           })
                                                           .withSize(HALF_WIDTH, UNIT_HEIGHT)
                                                           .assemble();
        final Button backButton = TextButtonAssembler.buttonOf(BACK_BUTTON)
                                                     .withClickCommand(new Command() {
                                                         @Override
                                                         public void execute() {
                                                             changeScreenToMain();
                                                         }
                                                     })
                                                     .withSize(HALF_WIDTH, UNIT_HEIGHT)
                                                     .assemble();

        return TableAssembler.tableOf(allFiltersButton, backButton)
                             .assemble();
    }

    private void filter() {
        voiceFilter.filter(voiceContainer, filterMap.getFilterInformation(),
                           filterCheckBoxContainer.getCurrentFilter());
    }

    private Actor getOptionsPane() {
        final Table optionsTable = TableAssembler.table()
                                                 .align(Align.top)
                                                 .assemble();
        fillInFilterOptions(optionsTable);
        fillInOtherOptions(optionsTable);

        return PaneAssembler.paneOf(optionsTable)
                            .withScrollingDisabledX()
                            .assemble();
    }

    private void fillInOtherOptions(final Table optionsTable) {
        final Table table = TableAssembler.table(OPTIONS_LABEL)
                                          .align(Align.top)
                                          .assemble();
        optionsTable.add(table)
                    .row();
        fillInEnablable(optionsTable, SHARING_LABEL, sharingEnablable);
        fillInSortOption(optionsTable);
        fillInEnablable(optionsTable, INDICATOR_LABEL, indicatorEnablable);
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

        final Table sortTable = TableAssembler.tableOf(label, sortingSelectBox)
                                              .assemble();
        optionsTable.add(sortTable)
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
                                                .align(Align.top)
                                                .assemble();
        final Table secondColumn = TableAssembler.table(PEOPLE_LABEL)
                                                 .align(Align.top)
                                                 .assemble();
        final Table thirdColumn = TableAssembler.table(FILTERS_LABEL)
                                                .align(Align.top)
                                                .assemble();

        buildFilterCheckBoxes(firstColumn, secondColumn, thirdColumn);
        final Table doubleColumn = TableAssembler.equalizedDoubleColumn(firstColumn, secondColumn)
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
            final FilterCheckBox filterCheckBox = filterCheckBoxContainer.createFilterCheckBox(entry.getKey(),
                                                                                               entry.getValue());
            Table column = otherColumn;
            if (Maths.containsBit(entry.getValue(), packetFilters)) {
                column = packetColumn;
            } else if (Maths.containsBit(entry.getValue(), personFilters)) {
                column = peopleColumn;
            }
            filterCheckBox.addListener(filterClickListener);
            column.add(filterCheckBox)
                  .width(HALF_WIDTH)
                  .height(OPTION_HEIGHT)
                  .row();
        }
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
