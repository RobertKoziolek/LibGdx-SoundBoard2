package com.robcio.soundboard2.gui.animation;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.robcio.soundboard2.gui.SizeHolder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.robcio.soundboard2.constants.Numeral.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ActorAnimation {

    @Setter
    private static SizeHolder sizeHolder;

    public static void enterToTopBar(final Actor actor) {
        actor.addAction(moveTo(0f, sizeHolder.NO_TOP_HEIGHT, ACTOR_IN_DURATION, Interpolation.pow5));
    }

    public static void exitToTop(final Actor actor){
        actor.addAction(moveTo(0, HEIGHT, ACTOR_OUT_DURATION, Interpolation.pow5));
    }
}
