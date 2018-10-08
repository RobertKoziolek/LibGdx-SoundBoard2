package com.robcio.soundboard2.gui.assembler;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.robcio.soundboard2.utils.assets.Assets;
import com.robcio.soundboard2.gui.assembler.observer.LabelObserver;

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
            observable.addObserver(new LabelObserver(label));
        }
        return label;
    }

    public LabelAssembler observing(final Observable observable) {
        this.observable = observable;
        return this;
    }

}
