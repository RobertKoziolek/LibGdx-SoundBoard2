package com.robcio.soundboard2.gui.options;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
import com.robcio.soundboard2.gui.options.component.FilterCheckBox;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.Command;
import com.robcio.soundboard2.utils.Maths;
import com.robcio.soundboard2.utils.SharingManager;
import com.robcio.soundboard2.voice.VoiceHolder;

import java.util.Map;

import static com.robcio.soundboard2.SoundBoard2.WIDTH;
import static com.robcio.soundboard2.gui.constants.Numeral.*;
import static com.robcio.soundboard2.gui.constants.Strings.*;

class OptionsStageController extends StageController {


    private final VoiceHolder voiceHolder;
    private final SharingManager sharingManager;
    private final FilterMap filterMap;
    private final EventListener filterClickListener;

    OptionsStageController(final VoiceHolder voiceHolder,
                           final SharingManager sharingManager,
                           final FilterMap filterMap) {
        super();
        this.sharingManager = sharingManager;
        FilterCheckBox.setFilterInformation(filterMap.getFilterInformation());
        this.voiceHolder = voiceHolder;
        this.filterMap = filterMap;
        filterClickListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                filter();
            }
        };
    }

    void buildStage() {
        final Table rootTable = TableAssembler.table()
                                              .align(Align.top)
                                              .fillParent()
                                              .assemble();

        final Actor topBar = getTopBar();
        final Actor filterPane = getFilterPane();
        final Actor counter = getCounter();

        rootTable.add(topBar)
                 .row();
        rootTable.add(filterPane)
                 .width(WIDTH)
                 .height(ALMOST_HEIGHT)
                 .row();
        rootTable.add(counter)
                 .width(WIDTH)
                 .height(UNIT_HEIGHT);
        addActor(rootTable);
    }

    private Actor getCounter() {
        final Label counter = LabelAssembler.labelOf(ALL_FILTERS_BUTTON)
                                            .observing(voiceHolder)
                                            .assemble();
        return PaneAssembler.paneOfInTable(counter)
                            .withScrollingDisabled()
                            .assemble();
    }

    private Actor getTopBar() {
        final Button allFiltersButton = TextButtonAssembler.buttonOf(ALL_FILTERS_BUTTON)
                                                           .shakeStage(this)
                                                           .withClickCommand(new Command() {
                                                               @Override
                                                               public void execute() {
                                                                   FilterCheckBox.setAll();
                                                                   filter();
                                                               }
                                                           })
                                                           .withSize(HALF_WIDTH, UNIT_HEIGHT)
                                                           .assemble();
        final Button backButton = TextButtonAssembler.buttonOf(BACK_BUTTON)
                                                     .withClickCommand(new Command() {
                                                         @Override
                                                         public void execute() {
                                                             changeScreen(ScreenId.MAIN,
                                                                          StageAnimation.exitToBot());
                                                         }
                                                     })
                                                     .withSize(HALF_WIDTH, UNIT_HEIGHT)
                                                     .assemble();

        return TableAssembler.tableOf(allFiltersButton, backButton)
                             .assemble();
    }

    private void filter() {
        voiceHolder.filter(FilterCheckBox.getCurrentFilter(),
                           filterMap.getFilterInformation());
    }

    private Actor getFilterPane() {
        final Table optionsTable = TableAssembler.table()
                                                 .align(Align.top)
                                                 .assemble();
        fillInFilterOptions(optionsTable);
        fillInSharingOption(optionsTable);

        return PaneAssembler.paneOf(optionsTable)
                            .withScrollingDisabledX()
                            .assemble();
    }

    private void fillInSharingOption(final Table optionsTable) {
        final CheckBox checkBox = new CheckBox(SHARING_LABEL, Assets.getSkin());
        checkBox.setChecked(sharingManager.isSharingAllowed());
        checkBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                final boolean checked = checkBox.isChecked();
                sharingManager.setSharingAllowed(checked);
                if (checked) {
                    sharingManager.askForSharingPermission();
                }
            }
        });

        final Table table = TableAssembler.table(OPTIONS_LABEL)
                                          .align(Align.top)
                                          .assemble();
        table.add(checkBox)
             .height(OPTION_HEIGHT);
        optionsTable.add(table).row();
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
                    .width(WIDTH).row();
    }

    private void buildFilterCheckBoxes(final Table firstColumn, final Table secondColumn, final Table thirdColumn) {
        final int packetFilters = filterMap.getFilterInformation()
                                           .getPacketFilters();
        final int personFilters = filterMap.getFilterInformation()
                                           .getPeopleFilters();
        for (final Map.Entry<String, Integer> entry : filterMap.getEntrySet()) {
            final FilterCheckBox filterCheckBox = FilterCheckBox.of(entry.getKey(), entry.getValue());
            Table column = thirdColumn;
            if (Maths.containsBit(entry.getValue(), packetFilters)) {
                column = firstColumn;
            } else if (Maths.containsBit(entry.getValue(), personFilters)) {
                column = secondColumn;
            }
            filterCheckBox.addListener(filterClickListener);
            column.add(filterCheckBox)
                  .width(HALF_WIDTH)
                  .height(OPTION_HEIGHT)
                  .row();
        }
    }
}
