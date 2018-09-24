package com.robcio.soundboard2.gui.animation;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.robcio.soundboard2.SoundBoard2.HEIGHT;
import static com.robcio.soundboard2.SoundBoard2.WIDTH;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StageAnimation {

    public static void shake(final Stage stage) {
        float time = 0.05f, offset = 15;
        if (MathUtils.randomBoolean()) offset *= -1;
        stage.addAction(sequence(moveBy(offset, offset, time),
                                 moveBy(-offset * 2, -offset * 2, time),
                                 moveBy(offset, offset, time)));
    }

    public static void enterFromTop(final Stage stage) {
        stage.addAction(sequence(alpha(0f), moveTo(0, HEIGHT),
                                 parallel(fadeIn(0.4f), moveBy(0, -HEIGHT, 0.4f, Interpolation.pow5))));
    }

    public static void enterFromBot(final Stage stage) {
        stage.addAction(sequence(alpha(0f), moveTo(0, -HEIGHT),
                                 parallel(fadeIn(0.4f), moveBy(0, HEIGHT, 0.4f, Interpolation.pow5))));
    }

    public static void enterFromRight(final Stage stage) {
        stage.addAction(parallel(moveTo(WIDTH, 0), fadeIn(0.25f), moveBy(-WIDTH, 0, 0.25f)));
    }

    public static Action exitToLeft() {
        return parallel(moveTo(0, 0), fadeOut(0.25f), moveBy(-WIDTH, 0, 0.25f));
    }

    public static Action exitToTop() {
        return parallel(moveTo(0, 0), fadeOut(.4f), moveBy(0, HEIGHT, 0.4f, Interpolation.pow5));
    }

    public static Action exitToBot() {
        return parallel(moveTo(0, 0), fadeOut(.4f), moveBy(0, -HEIGHT, 0.4f, Interpolation.pow5));
    }
}
