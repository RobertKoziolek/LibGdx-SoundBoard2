package com.robcio.soundboard2.gui.assembler.observer;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.robcio.soundboard2.utils.Command;

import java.util.Observable;
import java.util.Observer;

import static com.robcio.soundboard2.constants.Strings.HUNDRED_PERCENT;

public class TextButtonObserver implements Observer {

    private final TextButton button;
    private final Command command;
    private final Object finishTrigger, finishValue;

    public TextButtonObserver(final TextButton button, final Command command, final String text) {
        this.button = button;
        this.command = command;
        this.finishTrigger = HUNDRED_PERCENT;
        this.finishValue = text;
    }

    @Override
    public void update(final Observable o, final Object arg) {
        if (finishTrigger.equals(arg)) {
            setText(finishValue);
            command.execute();
        } else if (arg instanceof String) {
            setText(arg);
        }
    }

    private void setText(final Object arg) {
        button.setText(String.valueOf(arg));
    }
}
