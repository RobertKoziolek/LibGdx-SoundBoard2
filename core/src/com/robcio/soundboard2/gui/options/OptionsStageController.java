package com.robcio.soundboard2.gui.options;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.filter.FilterMap;
import com.robcio.soundboard2.gui.StageController;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.gui.assembler.PaneAssembler;
import com.robcio.soundboard2.gui.assembler.TableAssembler;
import com.robcio.soundboard2.gui.assembler.TextButtonAssembler;
import com.robcio.soundboard2.gui.options.component.FilterCheckBox;
import com.robcio.soundboard2.utils.Command;
import com.robcio.soundboard2.utils.Maths;
import com.robcio.soundboard2.voice.VoiceHolder;

import java.util.Map;

import static com.robcio.soundboard2.SoundBoard2.WIDTH;
import static com.robcio.soundboard2.gui.constants.Sizes.*;
import static com.robcio.soundboard2.gui.constants.Strings.*;

class OptionsStageController extends StageController {


    private final VoiceHolder voiceHolder;
    private final FilterMap filterMap;

    OptionsStageController(final VoiceHolder voiceHolder,
                           final FilterMap filterMap) {
        super();
        this.voiceHolder = voiceHolder;
        this.filterMap = filterMap;
    }

    void buildStage() {
        final Table rootTable = TableAssembler.table()
                                              .align(Align.top)
                                              .fillParent()
                                              .assemble();

        final Actor topBar = getTopBar();
        final Actor filterPane = getFilterPane();


        rootTable.add(topBar)
                 .row();
        rootTable.add(filterPane)
                 .width(WIDTH)
                 .height(ALMOST_HEIGHT)
                 .row();

        addActor(rootTable);
    }

    private Actor getTopBar() {
        final TextButton backButton = TextButtonAssembler.buttonOf(BACK_BUTTON_TEXT)
                                                         .withCommand(new Command() {
                                                             @Override
                                                             public void execute() {
                                                                 voiceHolder.filter(FilterCheckBox.getCurrentFilter(),
                                                                                    filterMap.getFilterInformation());
                                                                 changeScreen(ScreenId.MAIN,
                                                                              StageAnimation.exitToBot());
                                                             }
                                                         })
                                                         .withSize(WIDTH, MENU_HEIGHT)
                                                         .assemble();

        return TableAssembler.tableOf(backButton)
                             .assemble();
    }

    private Actor getFilterPane() {
        final Table optionsTable = TableAssembler.table()
                                                 .align(Align.top)
                                                 .assemble();

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
                    .width(WIDTH);

        return PaneAssembler.paneOf(optionsTable)
                            .withScrollingDisabled(true, false)
                            .assemble();
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
            column.add(filterCheckBox)
                  .width(HALF_WIDTH)
                  .height(OPTION_HEIGHT)
                  .row();
        }
    }
}
