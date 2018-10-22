package com.robcio.soundboard2.gui;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.robcio.soundboard2.utils.assets.Assets;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SoundboardScreen implements Screen {
    private SoundboardStage stage;

    public static SoundboardScreen of(@NonNull final SoundboardStage stage){
        return new SoundboardScreen(stage);
    }

    @Override
    public final void show() {
        if (stage.getActors().size == 0) {
            stage.buildStage();
        }
        stage.show();
    }

    @Override
    public void render(final float deltaTime) {
        //TODO might be better to make this screen-specific
        Assets.getAssetsLoader()
              .notifyObservers();
        stage.act(deltaTime);
        stage.draw();
    }

    @Override
    public void resize(final int width, final int height) {
        stage.getViewport()
             .update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        if (stage != null) {
            stage.dispose();
        }
    }

    public InputProcessor[] getInputs() {
        return new InputProcessor[]{stage};
    }
}