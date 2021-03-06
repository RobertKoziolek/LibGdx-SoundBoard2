package com.robcio.soundboard2.gui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.utils.ScreenChanger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.robcio.soundboard2.constants.Numeral.HEIGHT;
import static com.robcio.soundboard2.constants.Numeral.WIDTH;

public abstract class SoundboardStage extends Stage {

    @Setter
    @Getter(AccessLevel.PROTECTED)
    static private SizeHolder sizeHolder;
    static private ScreenChanger screenChanger;
    static private Camera camera;

    public SoundboardStage() {
        super(new StretchViewport(WIDTH, HEIGHT, camera));
    }

    private void changeScreen(final ScreenId screenId) {
        screenChanger.setScreen(screenId);
    }

    final protected void changeScreen(final ScreenId screenId, final Action action) {
        addAction(sequence(action,
                           run(new Runnable() {
                               @Override
                               public void run() {
                                   changeScreen(screenId);
                               }
                           })));
    }

    public static void setScreenChangerAndCamera(final ScreenChanger screenChanger, final OrthographicCamera camera) {
        SoundboardStage.screenChanger = screenChanger;
        SoundboardStage.camera = camera;
    }

    protected abstract void backKeyDown();

    protected abstract void buildStage();

    protected abstract void show();

    protected void touchDown() {

    }

    @Override
    public boolean keyDown(final int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
            backKeyDown();
        }
        return super.keyDown(keycode);
    }

    @Override
    public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
        final boolean handled = super.touchDown(screenX, screenY, pointer, button);
        touchDown();
        return handled;
    }

    @Override
    public void dispose() {
        super.dispose();
        SoundboardStage.screenChanger = null;
        SoundboardStage.camera = null;
    }
}
