package com.robcio.soundboard2.gui.assembler;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.robcio.soundboard2.utils.assets.Assets;

public class PaneAssembler {
    private final ScrollPane pane;
    private float width, height;
    private boolean scrollingDisabledX, scrollingDisabledY;
    private boolean cancelTouchFocus = true;
    private float fadeAlphaSeconds = 1f, fadeDelaySeconds = 1f;
    private float overscrollDistance = 50, overscrollSpeedMin = 30, overscrollSpeedMax = 200;

    private PaneAssembler(final Actor widget) {
        this.pane = new ScrollPane(widget, Assets.getSkin());
    }

    public static PaneAssembler blank() {
        return new PaneAssembler(null);
    }

    public static PaneAssembler paneOf(final Actor widget) {
        return new PaneAssembler(widget);
    }

    public static PaneAssembler paneOfInTable(final Actor actor) {
        final Table table = TableAssembler.table(actor)
                                          .assemble();
        return paneOf(table);
    }

    public ScrollPane assemble() {
        pane.setSize(width, height);
        pane.setScrollingDisabled(scrollingDisabledX, scrollingDisabledY);
        pane.setupFadeScrollBars(fadeAlphaSeconds, fadeDelaySeconds);
        pane.setupOverscroll(overscrollDistance, overscrollSpeedMin, overscrollSpeedMax);
        pane.setCancelTouchFocus(cancelTouchFocus);
        return pane;
    }

    public PaneAssembler withSize(final float width, final float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public PaneAssembler withScrollingDisabledX() {
        this.scrollingDisabledX = true;
        return this;
    }

    public PaneAssembler withScrollingDisabledY() {
        this.scrollingDisabledY = true;
        return this;
    }

    public PaneAssembler withScrollingDisabled() {
        this.scrollingDisabledX = true;
        this.scrollingDisabledY = true;
        return this;
    }

    public PaneAssembler dontCancelTouchFocus() {
        this.cancelTouchFocus = false;
        return this;
    }

    public PaneAssembler withFadeScrollBars(final float fadeAlphaSeconds, final float fadeDelaySeconds) {
        this.fadeAlphaSeconds = fadeAlphaSeconds;
        this.fadeDelaySeconds = fadeDelaySeconds;
        return this;
    }

    public PaneAssembler withOverscroll(final float distance, float speedMin, float speedMax) {
        this.overscrollDistance = distance;
        this.overscrollSpeedMin = speedMin;
        this.overscrollSpeedMax = speedMax;
        return this;
    }
}
