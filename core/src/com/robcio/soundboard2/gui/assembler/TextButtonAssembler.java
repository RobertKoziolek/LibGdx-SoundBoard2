package com.robcio.soundboard2.gui.assembler;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.robcio.soundboard2.gui.animation.ActorAnimation;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.gui.assembler.listener.ButtonListener;
import com.robcio.soundboard2.utils.assets.Assets;
import com.robcio.soundboard2.utils.Command;
import lombok.NonNull;

import java.util.Observable;

import static com.robcio.soundboard2.constants.Numeral.UNIT_WIDTH;

public class TextButtonAssembler {
    private final TextButton button;
    private float width, height;
    private Command clickCommand, specialClickCommand;
    private Observable observable;
    private Command observableFinishCommand;
    private String buttonText;
    private Stage stage;

    private TextButtonAssembler(final String text) {
        this.button = new TextButton(text, Assets.getSkin());
        this.buttonText = text;
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
                    ActorAnimation.shake(button);
                    return;
                }
                clickCommand.execute();
                if (stage != null) {
                    StageAnimation.shake(stage);
                }
            }
        });
        if (observable != null) {
            observable.addObserver(new ButtonListener(button, observableFinishCommand, buttonText));
        }
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

    public TextButtonAssembler observing(@NonNull final Observable observable, @NonNull final Command command) {
        this.observable = observable;
        this.observableFinishCommand = command;
        return this;
    }

    public TextButtonAssembler shakeStage(final Stage stage) {
        this.stage = stage;
        return this;
    }

}
