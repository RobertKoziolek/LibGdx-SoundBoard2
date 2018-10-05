package com.robcio.soundboard2.gui.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.robcio.soundboard2.gui.assembler.LabelAssembler;
import com.robcio.soundboard2.utils.assets.Assets;
import com.robcio.soundboard2.utils.helper.Maths;

import static com.robcio.soundboard2.constants.Strings.*;

public class ExitDialogBox extends Dialog {

    private static final String WINDOW_STYLE_DIALOG = "dialog";
    private final Stage stage;

    private boolean active;

    public ExitDialogBox(final Stage stage) {
        super(EMPTY_STRING, Assets.getSkin(), WINDOW_STYLE_DIALOG);
        this.stage = stage;

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

    public void showOrHide() {
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
