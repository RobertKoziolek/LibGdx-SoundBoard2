package com.robcio.soundboard2.gui.assembler;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.gui.assembler.listener.LabelListener;

import java.util.Observable;

public class LabelAssembler {
    private final Label label;
    private Observable observable;

    private LabelAssembler(final String text) {
        this.label = new Label(text, Assets.getSkin());
    }

    public static LabelAssembler labelOf(final String text) {
        return new LabelAssembler(text);
    }

    public Label assemble() {
        if (observable != null) {
            observable.addObserver(new LabelListener(label));
        }
        return label;
    }

    public LabelAssembler observing(final Observable observable) {
        this.observable = observable;
        return this;
    }

}
