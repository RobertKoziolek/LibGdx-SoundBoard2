package com.robcio.soundboard2.gui.main;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.robcio.soundboard2.gui.GuiAssembler;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.Command;
import com.robcio.soundboard2.voice.Voice;

import java.util.List;

import static com.robcio.soundboard2.SoundBoard2.HEIGHT;
import static com.robcio.soundboard2.SoundBoard2.WIDTH;

public class MainStageController extends Stage {

    private final List<Voice> voiceList;

    public MainStageController(final Camera camera,
                               final List<Voice> voiceList) {
        super(new FillViewport(WIDTH, HEIGHT, camera));
        this.voiceList = voiceList;

        final Table mainTable = getMainTable();

        final Actor buttonPane = getButtonPane();
        final Actor topBar = getTopBar();


        mainTable.add(topBar)
                 .row();
        mainTable.add(buttonPane)
                 .row();

        addActor(mainTable);
    }

    private Actor getTopBar() {
        final Button silenceButton = GuiAssembler.textButtonOf("Silentium")
                                                 .withCommand(new Command() {
                                                     @Override
                                                     public void execute() {
                                                         for (final Voice voice : voiceList) {
                                                             voice.getSound()
                                                                  .stop();
                                                         }
                                                     }
                                                 })
                                                 .withSize(WIDTH, HEIGHT / 12f)
                                                 .assemble();
        return GuiAssembler.tableOf(silenceButton);
    }

    private Table getMainTable() {
        final Table mainTable = new Table(Assets.getSkin());
        mainTable.setFillParent(true);
        mainTable.align(Align.top);
        return mainTable;
    }

    private Actor getButtonPane() {
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
                 .height(HEIGHT / 12f)
                 .row();
        }
        return GuiAssembler.paneOf(table)
                           .withScrollingDisabled(true, false)
                           .assemble();
    }
}
