package com.robcio.soundboard2.gui.main;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.gui.StageController;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.gui.assembler.PaneAssembler;
import com.robcio.soundboard2.gui.assembler.TableAssembler;
import com.robcio.soundboard2.gui.assembler.TextButtonAssembler;
import com.robcio.soundboard2.gui.assembler.TextFieldAssembler;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.Command;
import com.robcio.soundboard2.voice.Voice;
import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.util.List;

import static com.robcio.soundboard2.SoundBoard2.WIDTH;
import static com.robcio.soundboard2.gui.constants.Sizes.MENU_HEIGHT;
import static com.robcio.soundboard2.gui.constants.Sizes.THIRD_WIDTH;
import static com.robcio.soundboard2.gui.constants.Strings.*;
import static com.robcio.soundboard2.utils.Maths.SEARCH_RATIO;

class MainStageController extends StageController {

    private final List<Voice> voiceList;
    private final ScrollPane buttonPane;

    MainStageController(final List<Voice> voiceList) {
        super();
        this.voiceList = voiceList;

        final Table rootTable = getMainTable();

        final Actor topBar = getTopBar();
        buttonPane = getButtonPane();


        rootTable.add(topBar)
                 .row();
        rootTable.add(buttonPane)
                 .row();

        addActor(rootTable);
        updateButtons();
    }

    private Actor getTopBar() {
        final Button silenceButton = TextButtonAssembler.buttonOf(SILENCE_BUTTON)
                                                        .shake(this)
                                                        .withCommand(new Command() {
                                                            @Override
                                                            public void execute() {
                                                                silenceAllVoices();
                                                            }
                                                        })
                                                        .withSize(THIRD_WIDTH, MENU_HEIGHT)
                                                        .assemble();
        final TextButton optionsButton = TextButtonAssembler.buttonOf(OPTIONS_BUTTON)
                                                            .withCommand(new Command() {
                                                                @Override
                                                                public void execute() {
                                                                    silenceAllVoices();
                                                                    changeScreen(ScreenId.OPTIONS, StageAnimation.exitToTop());
                                                                }
                                                            })
                                                            .withSize(THIRD_WIDTH, MENU_HEIGHT)
                                                            .assemble();
        final TextField searchField = TextFieldAssembler.fieldOf(SEARCH_STRING)
                                                        .withSize(THIRD_WIDTH, MENU_HEIGHT)
                                                        .withTextFieldListener(
                                                                new TextField.TextFieldListener() {
                                                                    @Override
                                                                    public void keyTyped(TextField textField, char c) {
                                                                        updateButtons(textField.getText());
                                                                    }
                                                                })
                                                        .assemble();

        return TableAssembler.tableOf(searchField, silenceButton, optionsButton)
                             .assemble();
    }

    private void silenceAllVoices() {
        for (final Voice voice : voiceList) {
            voice.getSound()
                 .stop();
        }
    }

    private Table getMainTable() {
        final Table rootTable = new Table(Assets.getSkin());
        rootTable.setFillParent(true);
        rootTable.align(Align.top);
        return rootTable;
    }

    private ScrollPane getButtonPane() {
        return PaneAssembler.paneOf(null)
                            .withScrollingDisabled(true, false)
                            .assemble();
    }

    void updateButtons() {
        updateButtons(EMPTY_STRING);
    }

    private void updateButtons(final String searchString) {
        final Table table = new Table(Assets.getSkin());
        for (final Voice voice : voiceList) {
            if (!searchString.isEmpty() && FuzzySearch.tokenSetPartialRatio(voice.getName(),
                                                                            searchString) < SEARCH_RATIO) {
                continue;
            }
            final TextButton button = TextButtonAssembler.buttonOf(voice.getName())
                                                         .withCommand(new Command() {
                                                             @Override
                                                             public void execute() {
                                                                 voice.getSound()
                                                                      .play();
                                                             }
                                                         })
                                                         .assemble();
            table.add(button)
                 .width(WIDTH)
                 .height(MENU_HEIGHT)
                 .row();
        }
        buttonPane.setActor(table);
    }
}
