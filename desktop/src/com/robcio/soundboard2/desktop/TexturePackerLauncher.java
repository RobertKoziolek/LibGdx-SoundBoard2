package com.robcio.soundboard2.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class TexturePackerLauncher {

    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 1024;
        settings.maxHeight = 1024;
        TexturePacker.process(settings, "D:\\Users\\Robert\\Documents\\projekty\\LibGDX-SoundBoard2\\android\\assets\\texture",
                              "texture", "texture");
    }
}
