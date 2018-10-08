package com.robcio.soundboard2.gui.main;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.gui.StageController;
import com.robcio.soundboard2.gui.animation.ActorAnimation;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.gui.assembler.PaneAssembler;
import com.robcio.soundboard2.gui.assembler.TableAssembler;
import com.robcio.soundboard2.gui.assembler.TextButtonAssembler;
import com.robcio.soundboard2.gui.assembler.TextFieldAssembler;
import com.robcio.soundboard2.gui.component.ExitDialogBox;
import com.robcio.soundboard2.indicator.IndicatorContainer;
import com.robcio.soundboard2.utils.Command;
import com.robcio.soundboard2.utils.assets.Assets;
import com.robcio.soundboard2.utils.dispatcher.ShareDispatcher;
import com.robcio.soundboard2.voice.Voice;
import com.robcio.soundboard2.voice.VoiceContainer;
import com.robcio.soundboard2.voice.VoiceSearcher;
import com.robcio.soundboard2.voice.VoiceSorter;

import static com.robcio.soundboard2.constants.Numeral.*;
import static com.robcio.soundboard2.constants.Strings.*;

class MainStageController extends StageController {

    private final VoiceContainer voiceContainer;
    private final VoiceSorter voiceSorter;
    private final ShareDispatcher shareDispatcher;
    private final IndicatorContainer indicatorContainer;

    private final VoiceSearcher voiceSearcher;
    private final ScrollPane buttonPane;
    private final Actor searchField;

    private final ExitDialogBox exitDialogBox;

    MainStageController(final VoiceContainer voiceContainer,
                        final VoiceSorter voiceSorter,
                        final ShareDispatcher shareDispatcher,
                        final IndicatorContainer indicatorContainer) {
        super();
        this.voiceContainer = voiceContainer;
        this.voiceSorter = voiceSorter;
        this.shareDispatcher = shareDispatcher;
        this.indicatorContainer = indicatorContainer;
        this.voiceSearcher = new VoiceSearcher();

        final Table rootTable = TableAssembler.table()
                                              .fillParent()
                                              .alignTop()
                                              .assemble();

        searchField = getSearchField();
        buttonPane = PaneAssembler.blank()
                                  .withScrollingDisabledX()
                                  .assemble();
        final Actor topBar = getTopBar();

        rootTable.add(topBar)
                 .width(WIDTH)
                 .height(UNIT_HEIGHT)
                 .row();
        rootTable.add(buttonPane)
                 .width(WIDTH)
                 .height(NO_TOP_HEIGHT)
                 .row();

        addActor(rootTable);
        addActor(searchField);
        updateButtons();

        exitDialogBox = new ExitDialogBox(this);
    }

    @Override
    protected void backKeyDown() {
        exitDialogBox.showOrHide();
    }

    @Override
    protected void touchDown() {
        ActorAnimation.exitToTop(searchField);
        setKeyboardFocus(null);
    }

    private TextField getSearchField() {
        final TextField searchField = TextFieldAssembler.field()
                                                        .resetting()
                                                        .withSize(WIDTH, UNIT_HEIGHT)
                                                        .withTextFieldListener(
                                                                new TextField.TextFieldListener() {
                                                                    @Override
                                                                    public void keyTyped(TextField textField, char c) {
                                                                        updateButtons(textField.getText());
                                                                    }
                                                                })
                                                        .assemble();
        searchField.setPosition(0, HEIGHT);
        return searchField;
    }

    private Actor getTopBar() {
        final Button searchButton = TextButtonAssembler.buttonOf(SEARCH_STRING)
                                                       .withClickCommand(new Command() {
                                                           @Override
                                                           public void execute() {
                                                               ActorAnimation.enterToTopBar(searchField);
                                                               setKeyboardFocus(searchField);
                                                           }
                                                       })
                                                       .withSize(QUATER_WIDTH, UNIT_HEIGHT)
                                                       .assemble();
        final Button resetButton = TextButtonAssembler.buttonOf(RESET_BUTTON)
                                                      .shakeStage(this)
                                                      .withClickCommand(new Command() {
                                                          @Override
                                                          public void execute() {
                                                              updateButtons();
                                                          }
                                                      })
                                                      .withSize(QUATER_WIDTH, UNIT_HEIGHT)
                                                      .assemble();
        final Button silenceButton = TextButtonAssembler.buttonOf(SILENCE_BUTTON)
                                                        .shakeStage(this)
                                                        .withClickCommand(new Command() {
                                                            @Override
                                                            public void execute() {
                                                                silenceAllVoices();
                                                            }
                                                        })
                                                        .withSize(QUATER_WIDTH, UNIT_HEIGHT)
                                                        .assemble();
        final Command partialUpdateCommand = new Command() {
            @Override
            public void execute() {
                voiceContainer.partialLoadUp();
                updateButtons();
            }
        };
        final TextButton optionsButton = TextButtonAssembler.buttonOf(OPTIONS_BUTTON)
                                                            .withClickCommand(new Command() {
                                                                @Override
                                                                public void execute() {
                                                                    if (voiceContainer.isLoaded()) {
                                                                        silenceAllVoices();
                                                                        changeScreen(ScreenId.OPTIONS,
                                                                                     StageAnimation.exitToTop());
                                                                    } else {
                                                                        partialUpdateCommand.execute();
                                                                    }
                                                                }
                                                            })
                                                            .observing(Assets.getAssetsLoader(),
                                                                       partialUpdateCommand)
                                                            .withSize(QUATER_WIDTH, UNIT_HEIGHT)
                                                            .assemble();

        return TableAssembler.tableOf(searchButton, resetButton, silenceButton, optionsButton)
                             .alignTop()
                             .assemble();
    }

    private void silenceAllVoices() {
        for (final Voice voice : voiceContainer.getCurrentList()) {
            voice.getSound()
                 .stop();
        }
        indicatorContainer.stopAll();
    }

    void updateButtons() {
        updateButtons(EMPTY_STRING);
    }

    private void updateButtons(final String searchString) {
        voiceSorter.sort();
        voiceSearcher.setSearchString(searchString);
        final Table table = TableAssembler.table()
                                          .alignTop()
                                          .assemble();
        for (final Voice voice : voiceContainer.getCurrentList()) {
            if (voiceSearcher.doesQualify(voice.getName())) {
                addVoiceButton(table, voice);
            }
        }
        if (table.getCells().size == 0) {
            table.add(LIST_IS_EMPTY);
        }
        buttonPane.setActor(table);
    }

    private void addVoiceButton(final Table table, final Voice voice) {
        final Sound sound = voice.getSound();
        final TextButtonAssembler buttonAssembler = TextButtonAssembler.buttonOf(voice.getName())
                                                                       .withClickCommand(new Command() {
                                                                           @Override
                                                                           public void execute() {
                                                                               sound.stop();
                                                                               sound.play();
                                                                               indicatorContainer.indicate(
                                                                                       voice.getFilter());
                                                                           }
                                                                       });
        if (shareDispatcher.isEnabled()) {
            buttonAssembler.withLongPressCommand(new Command() {
                @Override
                public void execute() {
                    shareDispatcher.share(voice.getFilePath());
                }
            });
        }
        table.add(buttonAssembler.assemble())
             .width(WIDTH)
             .height(UNIT_HEIGHT)
             .row();
    }
}
