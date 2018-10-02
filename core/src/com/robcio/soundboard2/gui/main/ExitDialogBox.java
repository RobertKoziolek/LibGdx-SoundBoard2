package com.robcio.soundboard2.gui.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.robcio.soundboard2.gui.assembler.LabelAssembler;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.Maths;

import static com.robcio.soundboard2.constants.Strings.*;

public class ExitDialogBox extends Dialog {

    private static final String WINDOW_STYLE_DIALOG = "dialog";

    private boolean active;

    ExitDialogBox() {
        super(EMPTY_STRING, Assets.getSkin(), WINDOW_STYLE_DIALOG);

        getButtonTable().pad(Maths.PPM / 3f, Maths.PPM, Maths.PPM / 3f, Maths.PPM);
        getContentTable().add(LabelAssembler.labelOf(EXIT_QUESTION)
                                            .assemble());

        button(EXIT_YES, new Object());
        button(EXIT_NO, null);
        active = false;
    }

    @Override
    protected void result(final Object object) {
        if (object != null) {
            Gdx.app.exit();
        }
    }

    void showOrHide(final Stage stage) {
        if (active) {
            hide();
        } else {
            show(stage);
        }
    }

    @Override
    public void hide() {
        active = false;
        super.hide();
    }

    @Override
    public Dialog show(final Stage stage) {
        active = true;
        return super.show(stage);
    }
}
