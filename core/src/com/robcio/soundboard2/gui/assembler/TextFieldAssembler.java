package com.robcio.soundboard2.gui.assembler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.robcio.soundboard2.utils.assets.Assets;

import static com.robcio.soundboard2.constants.Strings.EMPTY_STRING;

public class TextFieldAssembler {
    private final TextField textField;
    private float width, height;
    private boolean resetting;
    private TextField.TextFieldListener textFieldListener;

    private TextFieldAssembler() {
        this.textField = new TextField(EMPTY_STRING, Assets.getSkin());
    }

    public static TextFieldAssembler field() {
        return new TextFieldAssembler();
    }

    public TextField assemble() {
        textField.setSize(width, height);
        if (textFieldListener == null)
            throw new IllegalArgumentException("Cannot assemble a textField without any listener");
        textField.setTextFieldListener(textFieldListener);
        if (resetting) {
            textField.addCaptureListener(new FocusListener() {
                @Override
                public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                    super.keyboardFocusChanged(event, actor, focused);
                    if (focused) {
                        textField.setText(EMPTY_STRING);
                    }
                    Gdx.input.setOnscreenKeyboardVisible(focused);
                }
            });
        }
        return textField;
    }

    public TextFieldAssembler withSize(final float width, final float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public TextFieldAssembler resetting() {
        this.resetting = true;
        return this;
    }

    public TextFieldAssembler withTextFieldListener(final TextField.TextFieldListener textFieldListener) {
        this.textFieldListener = textFieldListener;
        return this;
    }

}
