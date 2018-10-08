package com.robcio.soundboard2.gui.suite;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.gui.StageController;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.gui.assembler.PaneAssembler;
import com.robcio.soundboard2.gui.assembler.TableAssembler;
import com.robcio.soundboard2.gui.assembler.TextButtonAssembler;
import com.robcio.soundboard2.utils.Command;
import com.robcio.soundboard2.voice.SuiteContainer;
import com.robcio.soundboard2.voice.Voice;
import com.robcio.soundboard2.voice.VoiceContainer;

import java.util.List;
import java.util.Map;

import static com.robcio.soundboard2.constants.Numeral.*;
import static com.robcio.soundboard2.constants.Strings.BACK_BUTTON;
import static com.robcio.soundboard2.constants.Strings.LIST_IS_EMPTY;

class SuiteStageController extends StageController {

    private final VoiceContainer voiceContainer;
    private final SuiteContainer suiteContainer;

    private ScrollPane suitesPane;

    SuiteStageController(final VoiceContainer voiceContainer, final SuiteContainer suiteContainer) {
        super();
        this.voiceContainer = voiceContainer;
        this.suiteContainer = suiteContainer;

    }

    @Override
    protected void backKeyDown() {
        changeScreenToOptions();
    }

    private void changeScreenToOptions() {
        changeScreen(ScreenId.OPTIONS, StageAnimation.exitToLeft());
    }

    private void changeScreenToMain() {
        changeScreen(ScreenId.MAIN, StageAnimation.exitToBot());
    }

    void buildStage() {
        final Table rootTable = TableAssembler.table()
                                              .alignTop()
                                              .fillParent()
                                              .assemble();

        final Actor topBar = getTopBar();
        suitesPane = PaneAssembler.blank()
                                  .withScrollingDisabledX()
                                  .assemble();

        rootTable.add(topBar)
                 .row();
        rootTable.add(suitesPane)
                 .width(WIDTH)
                 .height(NO_TOP_HEIGHT)
                 .row();
        addActor(rootTable);
        buildButtons();
    }

    private Actor getTopBar() {
        final Button backButton = TextButtonAssembler.buttonOf(BACK_BUTTON)
                                                     .withClickCommand(new Command() {
                                                         @Override
                                                         public void execute() {
                                                             changeScreenToOptions();
                                                         }
                                                     })
                                                     .withSize(WIDTH, UNIT_HEIGHT)
                                                     .assemble();
        return TableAssembler.tableOf(backButton)
                             .assemble();
    }

    private void buildButtons() {
        final Table table = TableAssembler.table()
                                          .alignTop()
                                          .assemble();
        for (final Map.Entry<String, List<Voice>> entry : suiteContainer.getSuiteMap()
                                                                        .entrySet()) {
            addSuiteButton(table, entry);
        }
        if (table.getCells().size == 0) {
            table.add(LIST_IS_EMPTY);
        }
        suitesPane.setActor(table);
    }

    private void addSuiteButton(final Table table, final Map.Entry<String, List<Voice>> entry) {
        final Button button = TextButtonAssembler.buttonOf(entry.getKey())
                                                 .withClickCommand(getClickCommand(entry.getValue()))
                                                 .assemble();
        table.add(button)
             .width(WIDTH)
             .height(UNIT_HEIGHT)
             .row();
    }

    private Command getClickCommand(final List<Voice> voiceList) {
        return new Command() {
            @Override
            public void execute() {
                final List<Voice> currentList = voiceContainer.getCurrentList();
                currentList.clear();
                currentList.addAll(voiceList);
                changeScreenToMain();
            }
        };
    }
}
