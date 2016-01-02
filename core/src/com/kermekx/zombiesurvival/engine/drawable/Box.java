package com.kermekx.zombiesurvival.engine.drawable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;

public class Box extends BaseDrawable {

	public Box(float width, float height, float depth, Color color) {
		super(modelBuilder.createBox(width, height, depth, new Material(ColorAttribute.createDiffuse(color)),
				Usage.Position | Usage.Normal));
	}

	public Box(float width, float height, float depth, Texture texture) {
		super(modelBuilder.createBox(width, height, depth,
				new Material(TextureAttribute.createDiffuse(texture)),
				Usage.Position | Usage.Normal | Usage.TextureCoordinates));
	}
	
	public Box(float width, float height, float depth, TextureRegion texture) {
		super(modelBuilder.createBox(width, height, depth,
				new Material(TextureAttribute.createDiffuse(texture)),
				Usage.Position | Usage.Normal | Usage.TextureCoordinates));
	}

	public Box(float x, float y, float width, float height, float depth, Color color) {
		super(modelBuilder.createBox(width, height, depth, new Material(ColorAttribute.createDiffuse(color)),
				Usage.Position | Usage.Normal));
		getInstance().transform.translate(x, y, 0);
	}

	public Box(float x, float y, float width, float height, float depth, Texture texture) {
		super(modelBuilder.createBox(width, height, depth,
				new Material(TextureAttribute.createDiffuse(texture)),
				Usage.Position | Usage.Normal | Usage.TextureCoordinates));
		getInstance().transform.translate(x, y, 0);
	}

	public Box(float x, float y, float width, float height, float depth, TextureRegion texture) {
		super(modelBuilder.createBox(width, height, depth,
				new Material(TextureAttribute.createDiffuse(texture)),
				Usage.Position | Usage.Normal | Usage.TextureCoordinates));
		getInstance().transform.translate(x, y, 0);
	}

}
