package com.robcio.soundboard2.gui.assembler.observer;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import lombok.AllArgsConstructor;

import java.util.Observable;
import java.util.Observer;

@AllArgsConstructor
public class LabelObserver implements Observer {

    private final Label label;

    @Override
    public void update(final Observable o, final Object arg) {
        if (arg instanceof String){
            label.setText(String.valueOf(arg));
        }
    }
}
