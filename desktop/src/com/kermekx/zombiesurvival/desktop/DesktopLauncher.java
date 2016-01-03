package com.kermekx.zombiesurvival.desktop;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kermekx.zombiesurvival.ZombieSurvival;

public class DesktopLauncher {
	
	public static final String version = "v0.0.0";
	
	public static void main (String[] arg) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Zombie Survival " + version;
		config.width = (int) (screenSize.getWidth() * 0.5);
		config.height = (int) (screenSize.getHeight() * 0.5);
		
		config.samples = 8;
		
		new LwjglApplication(new ZombieSurvival(), config);
	}
}
