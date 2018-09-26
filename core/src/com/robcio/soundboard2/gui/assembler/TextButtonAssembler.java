package com.robcio.soundboard2.gui.assembler;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.robcio.soundboard2.gui.animation.StageAnimation;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.Command;

public class TextButtonAssembler {
    private final TextButton button;
    private float width, height;
    //        private Observable observable;
    private Command command;
    private Stage stage;

    private TextButtonAssembler(final String text) {
        this.button = new TextButton(text, Assets.getSkin());
    }

    public static TextButtonAssembler buttonOf(final String text) {
        return new TextButtonAssembler(text);
    }

    public TextButton assemble() {
        button.setSize(width, height);
        if (command == null) throw new IllegalArgumentException("Cannot assemble a button without any action");
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                command.execute();
                if (stage != null) {
                    StageAnimation.shake(stage);
                }
            }
        });
//            if (observable != null) {
//                observable.addObserver(new ButtonListener(button));
//            }
        return button;
    }

    public TextButtonAssembler withSize(final float width, final float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public TextButtonAssembler withCommand(final Command command) {
        this.command = command;
        return this;
    }

    public TextButtonAssembler shakeStage(final Stage stage) {
        this.stage = stage;
        return this;
    }

//        public TextButtonAssembler observing(final Observable observable) {
//            this.observable = observable;
//            return this;
//        }

}
