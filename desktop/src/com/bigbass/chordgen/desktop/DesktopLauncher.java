package com.bigbass.chordgen.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bigbass.chordgen.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 1000;
		config.height = 800;
		config.resizable = false;
		
		config.vSyncEnabled = false;
		
		config.title = "Circle Chord Generator";
		
		new LwjglApplication(new Main(), config);
	}
}
