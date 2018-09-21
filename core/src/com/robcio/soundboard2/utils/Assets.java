package com.robcio.soundboard2.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.robcio.soundboard2.enumeration.SoundId;
import com.robcio.soundboard2.enumeration.TextureId;
import lombok.Getter;

import static com.robcio.soundboard2.enumeration.SoundId.*;

public class Assets {

    private static final AssetManager assetManager = new AssetManager();

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
        loadSounds();
        assetManager.finishLoading();
        textureAtlas = assetManager.get(TEXTURE_ATLAS);
    }

    private static void loadSounds() {
        assetManager.load(SOUND_BASS_1.getFilename(), Sound.class);
        assetManager.load(SOUND_BASS_2.getFilename(), Sound.class);
        assetManager.load(SOUND_CELLO_1.getFilename(), Sound.class);
        assetManager.load(SOUND_ORGAN_1.getFilename(), Sound.class);
        assetManager.load(SOUND_ORGAN_2.getFilename(), Sound.class);
        assetManager.load(SOUND_ORGAN_3.getFilename(), Sound.class);
        assetManager.load(SOUND_ORGAN_4.getFilename(), Sound.class);
    }

    private static void loadFont() {
        font = new BitmapFont(Gdx.files.internal(FONT_FNT), Gdx.files.internal(FONT_PNG),
                              false);
    }

    private static void loadSkin() {
        assetManager.load(UISKIN_ATLAS, TextureAtlas.class);
        assetManager.load(TEXTURE_ATLAS, TextureAtlas.class);
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

    public static Sound getSound(final SoundId soundId) {
        return assetManager.get(soundId.getFilename(), Sound.class);
    }

    public static Image getImage(final TextureId textureId) {
        return new Image(getRegion(textureId));
    }
//
//    public static Sprite getSprite(final TextureId textureId) {
//        return textureAtlas.createSprite(textureId.getFilename());
//    }

    public static TextureAtlas.AtlasRegion getRegion(final TextureId textureId) {
        return textureAtlas.findRegion(textureId.getFilename());
    }
}
