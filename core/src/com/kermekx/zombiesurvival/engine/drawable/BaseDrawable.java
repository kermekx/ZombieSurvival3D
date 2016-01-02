package com.kermekx.zombiesurvival.engine.drawable;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public abstract class BaseDrawable implements Drawable {

	protected static final ModelBuilder modelBuilder = new ModelBuilder();

	private Model model;
	private ModelInstance instance;

	public BaseDrawable(Model model) {
		this.model = model;
		instance = new ModelInstance(this.model);
	}

	@Override
	public ModelInstance getInstance() {
		return instance;
	}

	@Override
	public void dispose() {
		model.dispose();
	}

}
