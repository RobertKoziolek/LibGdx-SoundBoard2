package com.robcio.soundboard2.gui.assembler;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.robcio.soundboard2.utils.Assets;

public class TextFieldAssembler {
    private final TextField textField;
    private float width, height;
    private TextField.TextFieldListener textFieldListener;

    private TextFieldAssembler(final String text) {
        this.textField = new TextField(text, Assets.getSkin());
    }

    public static TextFieldAssembler fieldOf(final String text) {
        return new TextFieldAssembler(text);
    }

    public TextField assemble() {
        textField.setSize(width, height);
        if (textFieldListener == null) throw new IllegalArgumentException("Cannot assemble a textField without any listener");
        textField.setTextFieldListener(textFieldListener);
        return textField;
    }

    public TextFieldAssembler withSize(final float width, final float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public TextFieldAssembler withTextFieldListener(final TextField.TextFieldListener textFieldListener) {
        this.textFieldListener = textFieldListener;
        return this;
    }

}
