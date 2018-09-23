package com.robcio.soundboard2.gui.main;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.gui.GuiAssembler;
import com.robcio.soundboard2.gui.StageController;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.Command;
import com.robcio.soundboard2.utils.ScreenChanger;
import com.robcio.soundboard2.voice.Voice;

import java.util.List;

import static com.robcio.soundboard2.SoundBoard2.WIDTH;
import static com.robcio.soundboard2.gui.GuiConstants.HALF_WIDTH;
import static com.robcio.soundboard2.gui.GuiConstants.MENU_HEIGHT;

class MainStageController extends StageController {

    private final List<Voice> voiceList;
    private final ScrollPane buttonPane;

    MainStageController(final ScreenChanger screenChanger,
                        final Camera camera,
                        final List<Voice> voiceList) {
        super(screenChanger, camera);
        this.voiceList = voiceList;

        final Table mainTable = getMainTable();

        final Actor topBar = getTopBar();
        buttonPane = getButtonPane();


        mainTable.add(topBar)
                 .row();
        mainTable.add(buttonPane)
                 .row();

        addActor(mainTable);
        updateButtons();
    }

    private Actor getTopBar() {
        final Button silenceButton = GuiAssembler.textButtonOf("Silentium")
                                                 .withCommand(new Command() {
                                                     @Override
                                                     public void execute() {
                                                         silenceAllVoices();
                                                     }
                                                 })
                                                 .withSize(HALF_WIDTH, MENU_HEIGHT)
                                                 .assemble();
        final TextButton optionsButton = GuiAssembler.textButtonOf("Opcje")
                                                     .withCommand(new Command() {
                                                         @Override
                                                         public void execute() {
                                                             silenceAllVoices();
                                                             changeScreen(ScreenId.OPTIONS);
                                                         }
                                                     })
                                                     .withSize(HALF_WIDTH, MENU_HEIGHT)
                                                     .assemble();

        return GuiAssembler.tableOf(silenceButton, optionsButton);
    }

    private void silenceAllVoices() {
        for (final Voice voice : voiceList) {
            voice.getSound()
                 .stop();
        }
    }

    private Table getMainTable() {
        final Table mainTable = new Table(Assets.getSkin());
        mainTable.setFillParent(true);
        mainTable.align(Align.top);
        return mainTable;
    }

    private ScrollPane getButtonPane() {
        final ScrollPane buttonPane = GuiAssembler.paneOf(null)
                                                  .withScrollingDisabled(true, false)
                                                  .assemble();
        return buttonPane;
    }

    public void updateButtons() {
        final Table table = new Table(Assets.getSkin());
        for (final Voice voice : voiceList) {
            final TextButton button = GuiAssembler.textButtonOf(voice.getName())
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
