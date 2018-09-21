package com.robcio.soundboard2.gui.main;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.robcio.soundboard2.enumeration.SoundId;
import com.robcio.soundboard2.gui.GuiAssembler;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.Command;

import static com.robcio.soundboard2.SoundBoard2.HEIGHT;
import static com.robcio.soundboard2.SoundBoard2.WIDTH;

public class MainStageController extends Stage {

    public MainStageController(final Camera camera) {
        super(new FillViewport(WIDTH, HEIGHT, camera));

        final Table table = new Table(Assets.getSkin());

        for (final SoundId soundId : SoundId.values()) {
            final TextButton button = GuiAssembler.textButtonOf(soundId.getFilename())
                                                  .withCommand(new Command() {
                                                      @Override
                                                      public void execute() {
                                                          Assets.getSound(soundId)
                                                                .play();
                                                      }
                                                  })
                                                  .assemble();
            table.add(button)
                 .width(WIDTH)
                 .height(HEIGHT / 12)
                 .row();
        }

        final ScrollPane scrollPane = GuiAssembler.paneOf(table)
                                                  .withScrollingDisabled(true, false)
                                                  .assemble();

        final Table mainTable = new Table(Assets.getSkin());
        mainTable.setFillParent(true);
        mainTable.align(Align.top);
        mainTable.add(scrollPane);
        addActor(mainTable);

    }
}
