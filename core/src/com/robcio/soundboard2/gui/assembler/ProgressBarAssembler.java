package com.robcio.soundboard2.gui.assembler;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.robcio.soundboard2.utils.assets.Assets;

import static com.robcio.soundboard2.constants.Numeral.MIN_KNOB_HEIGHT;
import static com.robcio.soundboard2.constants.Numeral.MIN_KNOB_WIDTH;

public class ProgressBarAssembler {
    private final ProgressBar progressBar;

    private ProgressBarAssembler(final float min, final float max, final float stepSize) {
        this.progressBar = new ProgressBar(min, max, stepSize, false, Assets.getSkin());
    }

    public static ProgressBarAssembler barOf(final float min, final float max, final float stepSize) {
        return new ProgressBarAssembler(min, max, stepSize);
    }

    public ProgressBar assemble() {
        final ProgressBar.ProgressBarStyle style = progressBar.getStyle();
        style.knob.setMinWidth(MIN_KNOB_WIDTH);
        style.knob.setMinHeight(MIN_KNOB_HEIGHT);
        return progressBar;
    }
}
