package com.robcio.soundboard2.gui.options;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.filter.FilterMap;
import com.robcio.soundboard2.gui.GuiAssembler;
import com.robcio.soundboard2.gui.StageController;
import com.robcio.soundboard2.gui.options.component.FilterCheckBox;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.Command;
import com.robcio.soundboard2.utils.ScreenChanger;
import com.robcio.soundboard2.voice.VoiceHolder;

import java.util.Map;

import static com.robcio.soundboard2.SoundBoard2.WIDTH;
import static com.robcio.soundboard2.gui.GuiConstants.*;

class OptionsStageController extends StageController {

    private final VoiceHolder voiceHolder;
    private final FilterMap filterMap;

    OptionsStageController(final ScreenChanger screenChanger,
                           final Camera camera,
                           final VoiceHolder voiceHolder,
                           final FilterMap filterMap) {
        super(screenChanger, camera);
        this.voiceHolder = voiceHolder;
        this.filterMap = filterMap;
    }

    void buildStage() {
        final Table mainTable = getMainTable();

        final Actor topBar = getTopBar();
        final Actor filterPane = getFilterPane();


        mainTable.add(topBar)
                 .row();
        mainTable.add(filterPane)
                 .width(WIDTH)
                 .height(ALMOST_HEIGHT)
                 .row();

        addActor(mainTable);
    }

    private Actor getTopBar() {
        final TextButton backButton = GuiAssembler.textButtonOf("Wróć")
                                                  .withCommand(new Command() {
                                                      @Override
                                                      public void execute() {
                                                          voiceHolder.filter(FilterCheckBox.getCurrentFilter(),
                                                                             filterMap.getFilterInformation());
                                                          changeScreen(ScreenId.MAIN);
                                                      }
                                                  })
                                                  .withSize(WIDTH, MENU_HEIGHT)
                                                  .assemble();

        return GuiAssembler.tableOf(backButton);
    }

    private Table getMainTable() {
        final Table mainTable = new Table(Assets.getSkin());
        mainTable.setFillParent(true);
        mainTable.align(Align.top);
        return mainTable;
    }

    private Actor getFilterPane() {
        final Table optionsTable = new Table(Assets.getSkin());
        optionsTable.align(Align.top);

        final Table firstColumn = new Table(Assets.getSkin());
        firstColumn.align(Align.top)
                   .add(GuiAssembler.labelOf("Pakiety:"))
                   .row();
        final Table secondColumn = new Table(Assets.getSkin());
        secondColumn.align(Align.top)
                    .add(GuiAssembler.labelOf("Ludzie:"))
                    .row();
        final Table thirdColumn = new Table(Assets.getSkin());
        thirdColumn.align(Align.top)
                   .add(GuiAssembler.labelOf("Filtry:"))
                   .row();

        final int packetFilters = filterMap.getFilterInformation()
                                           .getPacketFilters();
        final int personFilters = filterMap.getFilterInformation()
                                           .getPeopleFilters();
        for (final Map.Entry<String, Integer> entry : filterMap.getEntrySet()) {
            final FilterCheckBox filterCheckBox = FilterCheckBox.of(entry.getKey(), entry.getValue());
            Table column = thirdColumn;
            if ((entry.getValue() & packetFilters) > 0) {
                column = firstColumn;
            } else if ((entry.getValue() & personFilters) > 0) {
                column = secondColumn;
            }
            column.add(filterCheckBox)
                  .width(HALF_WIDTH)
                  .height(OPTION_HEIGHT)
                  .row();
        }
        final Table doubleColumn = new Table(Assets.getSkin());
        {
            final int firstColumnSize = firstColumn.getCells().size;
            final int secondColumnSize = secondColumn.getCells().size;
            final int diff = Math.abs(secondColumnSize - firstColumnSize);
            if (firstColumnSize < secondColumnSize) {
                firstColumn.add()
                           .height(diff * OPTION_HEIGHT);
            } else {
                secondColumn.add()
                            .height(diff * OPTION_HEIGHT);
            }

        }
        doubleColumn.add(firstColumn, secondColumn);


        optionsTable.add(doubleColumn)
                    .width(WIDTH)
                    .row();
        optionsTable.add(thirdColumn)
                    .width(WIDTH);

        return GuiAssembler.paneOf(optionsTable)
                           .withScrollingDisabled(true, false)
                           .assemble();
    }
}
