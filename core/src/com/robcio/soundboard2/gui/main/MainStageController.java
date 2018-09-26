package com.robcio.soundboard2.gui.main;

import com.badlogic.gdx.audio.Sound;
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
import com.robcio.soundboard2.utils.Command;
import com.robcio.soundboard2.utils.SharingManager;
import com.robcio.soundboard2.voice.Voice;
import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.util.List;

import static com.robcio.soundboard2.SoundBoard2.WIDTH;
import static com.robcio.soundboard2.gui.constants.Numeral.UNIT_HEIGHT;
import static com.robcio.soundboard2.gui.constants.Numeral.THIRD_WIDTH;
import static com.robcio.soundboard2.gui.constants.Strings.*;
import static com.robcio.soundboard2.utils.Maths.SEARCH_RATIO;

class MainStageController extends StageController {

    private final List<Voice> voiceList;
    private final SharingManager sharingManager;
    private final ScrollPane buttonPane;

    MainStageController(final List<Voice> voiceList, final SharingManager sharingManager) {
        super();
        this.voiceList = voiceList;
        this.sharingManager = sharingManager;

        final Table rootTable = TableAssembler.table()
                                              .fillParent()
                                              .align(Align.top)
                                              .assemble();

        final Actor topBar = getTopBar();
        buttonPane = PaneAssembler.paneOf(null)
                                  .assemble();


        rootTable.add(topBar)
                 .row();
        rootTable.add(buttonPane)
                 .row();

        addActor(rootTable);
        updateButtons();
    }

    private Actor getTopBar() {
        final TextField searchField = TextFieldAssembler.fieldOf(SEARCH_STRING)
                                                        .withSize(THIRD_WIDTH, UNIT_HEIGHT)
                                                        .withTextFieldListener(
                                                                new TextField.TextFieldListener() {
                                                                    @Override
                                                                    public void keyTyped(TextField textField, char c) {
                                                                        updateButtons(textField.getText());
                                                                    }
                                                                })
                                                        .assemble();
        final Button silenceButton = TextButtonAssembler.buttonOf(SILENCE_BUTTON)
                                                        .shakeStage(this)
                                                        .withClickCommand(new Command() {
                                                            @Override
                                                            public void execute() {
                                                                silenceAllVoices();
                                                            }
                                                        })
                                                        .withSize(THIRD_WIDTH, UNIT_HEIGHT)
                                                        .assemble();
        final TextButton optionsButton = TextButtonAssembler.buttonOf(OPTIONS_BUTTON)
                                                            .withClickCommand(new Command() {
                                                                @Override
                                                                public void execute() {
                                                                    silenceAllVoices();
                                                                    changeScreen(ScreenId.OPTIONS,
                                                                                 StageAnimation.exitToTop());
                                                                }
                                                            })
                                                            .withSize(THIRD_WIDTH, UNIT_HEIGHT)
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

    void updateButtons() {
        updateButtons(EMPTY_STRING);
    }

    private void updateButtons(final String searchString) {
        final Table table = TableAssembler.table()
                                          .assemble();
        for (final Voice voice : voiceList) {
            if (!searchString.isEmpty() && FuzzySearch.tokenSetPartialRatio(voice.getName(),
                                                                            searchString) < SEARCH_RATIO) {
                continue;
            }
            final Sound sound = voice.getSound();
            final TextButtonAssembler buttonAssembler = TextButtonAssembler.buttonOf(voice.getName())
                                                                           .withClickCommand(new Command() {
                                                                               @Override
                                                                               public void execute() {
                                                                                   sound.stop();
                                                                                   sound.play();
                                                                               }
                                                                           });
            if (sharingManager.isSharingAllowed()) {
                buttonAssembler.withSpecialClickCommand(new Command() {
                    @Override
                    public void execute() {
                        sharingManager.share(voice.getFilePath());
                    }
                });
            }
            table.add(buttonAssembler.assemble())
                 .width(WIDTH)
                 .height(UNIT_HEIGHT)
                 .row();
        }
        final int size = table.getCells().size;
        if (size < 11) {
            table.add()
                 .height(UNIT_HEIGHT * (11 - size))
                 .width(WIDTH);
        }
        buttonPane.setScrollingDisabled(true, size < 11);
        buttonPane.setActor(table);
    }
}
