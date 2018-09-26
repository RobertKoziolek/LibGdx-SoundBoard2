package com.robcio.soundboard2.gui.assembler;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.Command;

import static com.robcio.soundboard2.gui.constants.Numeral.UNIT_WIDTH;

public class TextButtonAssembler {
    private final TextButton button;
    private float width, height;
    private Command clickCommand, specialClickCommand;
    private Stage stage;

    private TextButtonAssembler(final String text) {
        this.button = new TextButton(text, Assets.getSkin());
    }

    public static TextButtonAssembler buttonOf(final String text) {
        return new TextButtonAssembler(text);
    }

    public TextButton assemble() {
        button.setSize(width, height);
        if (clickCommand == null) {
            throw new IllegalArgumentException("Cannot assemble a button without click action");
        }
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (specialClickCommand != null && x < UNIT_WIDTH) {
                    specialClickCommand.execute();
                    return;
                }
                clickCommand.execute();
                if (stage != null) {
                    StageAnimation.shake(stage);
                }
            }
        });
        return button;
    }

    public TextButtonAssembler withSize(final float width, final float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public TextButtonAssembler withClickCommand(final Command command) {
        this.clickCommand = command;
        return this;
    }

    public TextButtonAssembler withSpecialClickCommand(final Command specialClickCommand) {
        this.specialClickCommand = specialClickCommand;
        return this;
    }

    public TextButtonAssembler shakeStage(final Stage stage) {
        this.stage = stage;
        return this;
    }

}
