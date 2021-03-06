package com.robcio.soundboard2.gui.animation;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.robcio.soundboard2.constants.Numeral.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StageAnimation {

    public static void shake(final Stage stage) {
        stage.addAction(sequence(moveBy(SHAKE_OFFSET, SHAKE_OFFSET, SHORT_DURATION),
                                 moveBy(-SHAKE_OFFSET * 2f, -SHAKE_OFFSET * 2f, SHORT_DURATION),
                                 moveBy(SHAKE_OFFSET, SHAKE_OFFSET, SHORT_DURATION)));
    }

    public static void enterFadeIn(final Stage stage) {
        stage.getRoot().clearActions();
        stage.addAction(sequence(alpha(0f), fadeIn(LONG_DURATION)));
    }

    public static void enterFromTop(final Stage stage) {
        stage.getRoot().clearActions();
        stage.addAction(sequence(alpha(0f), moveTo(0f, HEIGHT),
                                 parallel(fadeIn(LONG_DURATION),
                                          moveTo(0f, 0f, LONG_DURATION, Interpolation.pow5))));
    }

    public static void enterFromBot(final Stage stage) {
        stage.getRoot().clearActions();
        stage.addAction(sequence(alpha(0f), moveTo(0f, -HEIGHT),
                                 parallel(fadeIn(LONG_DURATION),
                                          moveTo(0f, 0f, LONG_DURATION, Interpolation.pow5))));
    }

    public static void enterFromRight(final Stage stage) {
        stage.getRoot().clearActions();
        stage.addAction(parallel(moveTo(WIDTH, 0f), fadeIn(NORMAL_DURATION), moveTo(0f, 0f, NORMAL_DURATION)));
    }

    public static Action exitFadeOut() {
        return fadeOut(NORMAL_DURATION);
    }

    public static Action exitToLeft() {
        return parallel(fadeOut(NORMAL_DURATION), moveTo(-WIDTH, 0f, NORMAL_DURATION));
    }

    public static Action exitToTop() {
        return parallel(fadeOut(LONG_DURATION), moveTo(0f, HEIGHT, LONG_DURATION, Interpolation.pow5));
    }

    public static Action exitToBot() {
        return parallel(fadeOut(LONG_DURATION), moveTo(0f, -HEIGHT, LONG_DURATION, Interpolation.pow5));
    }
}
