package com.robcio.soundboard2.gui;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.robcio.soundboard2.utils.Assets;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public abstract class AbstractScreen implements Screen {
    private Stage stage;

    @Override
    abstract public void show();

    @Override
    public void render(final float deltaTime) {
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

    protected final void setStage(@NonNull final Stage stage) {
        if (this.stage != null) throw new IllegalStateException("Cannot set another stage to a screen");
        this.stage = stage;
    }

    public InputProcessor[] getInputs() {
        return new InputProcessor[]{stage};
    }
}