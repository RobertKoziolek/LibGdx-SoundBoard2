package com.robcio.soundboard2.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.robcio.soundboard2.enumeration.TextureId;
import com.robcio.soundboard2.voice.loader.model.VoiceModel;
import lombok.Getter;

public class Assets {

    private static final AssetManager assetManager = new AssetManager();
    @Getter
    private static final AssetsLoader assetsLoader = new AssetsLoader(assetManager);

    private static final String LOADING_BACKGROUND = "loading_background.png";
    private static final String UISKIN_ATLAS = "ui/uiskin.atlas";
    private static final String TEXTURE_ATLAS = "texture/texture.atlas";
    private static final String UISKIN_JSON = "ui/uiskin.json";
    private static final String FONT = "default-font";
    private static final String FONT_FNT = "font/modak32.fnt";
    private static final String FONT_PNG = "font/modak32.png";

    @Getter
    static private Skin skin;
    @Getter
    static private BitmapFont font;
    @Getter
    static private TextureAtlas textureAtlas;

    public static void initialize() {
        loadFont();
        loadSkin();
        assetManager.load(LOADING_BACKGROUND, Texture.class);
        assetManager.load(TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();
        textureAtlas = assetManager.get(TEXTURE_ATLAS);
    }

    private static void loadFont() {
        font = new BitmapFont(Gdx.files.internal(FONT_FNT), Gdx.files.internal(FONT_PNG),
                              false);
    }

    private static void loadSkin() {
        assetManager.load(UISKIN_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();
        skin = new Skin(assetManager.get(UISKIN_ATLAS, TextureAtlas.class));
        skin.add(FONT, font);
        skin.load(Gdx.files.internal(UISKIN_JSON));
    }

    public static void dispose() {
        assetManager.dispose();
        font.dispose();
        skin.dispose();
    }

    public static Texture getLoadingBackground() {
        return assetManager.get(LOADING_BACKGROUND, Texture.class);
    }

    public static Image getImage(final TextureId textureId) {
        return getImage(textureId.getFilename());
    }

    public static Image getImage(final String textureName) {
        return new Image(textureAtlas.findRegion(textureName));
    }

    public static void loadVoice(final VoiceModel voiceModel) {
        assetManager.load(voiceModel.getFile(), Sound.class);
    }

    public static Sound getSound(final String file) {
        return assetManager.get(file, Sound.class);
    }

}
