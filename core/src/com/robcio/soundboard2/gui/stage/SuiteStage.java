package com.robcio.soundboard2.gui.stage;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.gui.SoundboardStage;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.gui.assembler.PaneAssembler;
import com.robcio.soundboard2.gui.assembler.TableAssembler;
import com.robcio.soundboard2.gui.assembler.TextButtonAssembler;
import com.robcio.soundboard2.utils.Command;
import com.robcio.soundboard2.utils.helper.TableHelper;
import com.robcio.soundboard2.voice.SuiteContainer;
import com.robcio.soundboard2.voice.Voice;
import com.robcio.soundboard2.voice.VoiceContainer;

import java.util.List;
import java.util.Map;

import static com.robcio.soundboard2.constants.Numeral.*;
import static com.robcio.soundboard2.constants.Strings.BACK_BUTTON;

public class SuiteStage extends SoundboardStage {

    private final VoiceContainer voiceContainer;
    private final SuiteContainer suiteContainer;

    private ScrollPane suitesPane;

    public SuiteStage(final VoiceContainer voiceContainer, final SuiteContainer suiteContainer) {
        super();
        this.voiceContainer = voiceContainer;
        this.suiteContainer = suiteContainer;
    }

    @Override
    protected void backKeyDown() {
        changeScreenToOptions();
    }

    @Override
    protected void show() {
        StageAnimation.enterFromRight(this);
    }

    private void changeScreenToOptions() {
        changeScreen(ScreenId.OPTIONS, StageAnimation.exitToLeft());
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
        suitesPane = PaneAssembler.blank()
                                  .withScrollingDisabledX()
                                  .assemble();

        rootTable.add(topBar)
                 .row();
        rootTable.add(suitesPane)
                 .width(WIDTH)
                 .height(getSizeHolder().NO_TOP_HEIGHT)
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
                                                     .withSize(WIDTH, getSizeHolder().UNIT_HEIGHT)
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
        TableHelper.markIfEmpty(table);
        suitesPane.setActor(table);
    }

    private void addSuiteButton(final Table table, final Map.Entry<String, List<Voice>> entry) {
        final Button button = TextButtonAssembler.buttonOf(entry.getKey())
                                                 .withClickCommand(getClickCommand(entry.getValue()))
                                                 .assemble();
        table.add(button)
             .width(WIDTH)
             .height(getSizeHolder().UNIT_HEIGHT)
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
