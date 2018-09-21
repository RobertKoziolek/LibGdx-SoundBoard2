package com.robcio.soundboard2.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.robcio.soundboard2.SoundBoard2;

public class DesktopLauncher {
	public static void main (String[] arg) {
		final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SoundBoard2.WIDTH;
		config.height = SoundBoard2.HEIGHT;
		config.title = SoundBoard2.TITLE;
		new LwjglApplication(new SoundBoard2(), config);
	}
}
