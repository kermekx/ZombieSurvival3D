package com.kermekx.zombiesurvival.game.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.kermekx.zombiesurvival.ZombieSurvival;
import com.kermekx.zombiesurvival.engine.scene.Scene;
import com.kermekx.zombiesurvival.game.texture.TerrainTexture;

public class LoadingScene extends Scene {
	
	private Label progressLabel;
	private BitmapFont font;
	private StringBuilder progressText;
	
	private float lastProgress = 0;
	
	public LoadingScene() {
		TerrainTexture.values();
		
		font = new BitmapFont();
		progressLabel = new Label(" ", new Label.LabelStyle(font, Color.WHITE));
		stage.addActor(progressLabel);
		progressText = new StringBuilder(" Progress : 0%");
		progressLabel.setText(progressText);
	}

	@Override
	public boolean update(float delta) {
		
		if (ZombieSurvival.manager.update())
			ZombieSurvival.INSTANCE.setScene(new GameScene());
			
		float progress = ZombieSurvival.manager.getProgress();
		
		if(progress == lastProgress)
			return false;
		
		lastProgress = progress;
		progressText.setLength(0);
		progressText.append(" Progress : " + ((int) (100 * progress)) + "%");
		progressLabel.setText(progressText);
		
		return true;
	}
	
}
