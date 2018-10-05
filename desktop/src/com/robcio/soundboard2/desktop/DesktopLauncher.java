package com.robcio.soundboard2.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.robcio.soundboard2.SoundBoard2;
import com.robcio.soundboard2.enumeration.Setting;
import com.robcio.soundboard2.utils.Settings;
import com.robcio.soundboard2.utils.dispatcher.ShareDispatcher;

import static com.robcio.soundboard2.constants.Numeral.HEIGHT;
import static com.robcio.soundboard2.constants.Numeral.WIDTH;
import static com.robcio.soundboard2.constants.Strings.TITLE;

public class DesktopLauncher {
	public static void main (String[] arg) {
		final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = WIDTH;
		config.height = HEIGHT;
		config.title = TITLE;

		final ShareDispatcher shareDispatcher = new DesktopShareDispatcher();

		new LwjglApplication(new SoundBoard2(shareDispatcher), config);

		final Boolean sharingEnabled = Settings.get(Setting.SHARING_BOOLEAN);
		shareDispatcher.setEnabled(sharingEnabled);
	}
}
