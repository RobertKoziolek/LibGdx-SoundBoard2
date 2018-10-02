package com.robcio.soundboard2.gui.assembler.listener;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.robcio.soundboard2.utils.Command;
import lombok.AllArgsConstructor;

import java.util.Observable;
import java.util.Observer;

@AllArgsConstructor
public class ButtonListener implements Observer {

    private final TextButton button;
    private final Command command;
    private final Object finishTrigger, finishValue;

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
