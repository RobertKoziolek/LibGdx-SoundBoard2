package com.robcio.soundboard2.gui.load;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.robcio.soundboard2.enumeration.ScreenId;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.utils.Maths;
import com.robcio.soundboard2.utils.ScreenChanger;

import static com.robcio.soundboard2.SoundBoard2.HEIGHT;
import static com.robcio.soundboard2.SoundBoard2.WIDTH;

class LoadStageController extends Stage {

    private final Slider slider;
    private final ScreenChanger screenChanger;

    private float progress;

    LoadStageController(final ScreenChanger screenChanger, final Camera camera) {
        super(new FillViewport(WIDTH, HEIGHT, camera));
        this.screenChanger = screenChanger;

        slider = new Slider(0, 1, 0.01f, false, Assets.getSkin());
        slider.getStyle().knob.setMinWidth(Maths.PPM / 2);
        slider.getStyle().knob.setMinHeight(Maths.PPM);

        final Table table = new Table();
        table.setFillParent(true);
        table.add(slider)
             .width(WIDTH * 4 / 5)
             .height(HEIGHT / 12)
             .padBottom(HEIGHT * 4 / 9);
        addActor(table);
    }

    @Override
    public void act(final float delta) {
        super.act(delta);
        updateProgress();
        loadAssets();
    }

    private void loadAssets() {
        if (Assets.update()) {
            screenChanger.setScreen(ScreenId.MAIN);
        }
    }

    private void updateProgress() {
        slider.setValue(Assets.getProgress());
    }
}
