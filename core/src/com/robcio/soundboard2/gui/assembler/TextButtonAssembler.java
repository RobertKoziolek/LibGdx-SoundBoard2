package com.robcio.soundboard2.gui.assembler;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.gui.assembler.observer.TextButtonObserver;
import com.robcio.soundboard2.utils.Command;
import com.robcio.soundboard2.utils.assets.Assets;
import lombok.NonNull;

import java.util.Observable;

public class TextButtonAssembler {
    private final TextButton button;
    private float width, height;
    private Command clickCommand, longPressCommand;
    private Observable observable;
    private Command observableFinishCommand;
    private String buttonText;
    private Stage stageToShake;

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
            throw new IllegalArgumentException("Cannot assemble a button without any click action");
        }
        final ActorGestureListener actorGestureListener = new ActorGestureListener() {
            @Override
            public void tap(final InputEvent event, final float x, final float y, final int count, final int buttonId) {
                clickCommand.execute();
                if (stageToShake != null) {
                    StageAnimation.shake(stageToShake);
                }
            }

            @Override
            public boolean longPress(final Actor actor, final float x, final float y) {
                final boolean longPressCommandPresent = longPressCommand != null;
                if (longPressCommandPresent) {
                    longPressCommand.execute();
                }
                return longPressCommandPresent;
            }

        };
        button.addListener(actorGestureListener);

        if (observable != null) {
            observable.addObserver(new TextButtonObserver(button, observableFinishCommand, buttonText));
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

    public TextButtonAssembler withLongPressCommand(final Command longPressCommand) {
        this.longPressCommand = longPressCommand;
        return this;
    }

    public TextButtonAssembler observing(@NonNull final Observable observable, @NonNull final Command command) {
        this.observable = observable;
        this.observableFinishCommand = command;
        return this;
    }

    public TextButtonAssembler shakeStage(final Stage stage) {
        this.stageToShake = stage;
        return this;
    }

}
