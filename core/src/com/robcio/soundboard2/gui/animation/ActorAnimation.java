package com.robcio.soundboard2.gui.animation;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.robcio.soundboard2.constants.Numeral.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ActorAnimation {

    public static void shake(final Actor actor) {
        actor.addAction(sequence(moveBy(SHAKE_OFFSET, SHAKE_OFFSET, SHORT_DURATION),
                                 moveBy(-SHAKE_OFFSET * 2f, -SHAKE_OFFSET * 2f, SHORT_DURATION),
                                 moveBy(SHAKE_OFFSET, SHAKE_OFFSET, SHORT_DURATION)));
    }

    public static void enterToTopBar(final Actor actor) {
        actor.addAction(moveTo(0f, NO_TOP_HEIGHT, ACTOR_IN_DURATION, Interpolation.pow5));

    }

    public static void exitToTop(final Actor actor){
        actor.addAction(moveTo(0, HEIGHT, ACTOR_OUT_DURATION, Interpolation.pow5));
    }
}
