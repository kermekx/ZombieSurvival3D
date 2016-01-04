package com.kermekx.zombiesurvival.engine.drawable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
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

	public Box(float x, float y, float z, float width, float height, float depth, Color color) {
		super(modelBuilder.createBox(width, height, depth, new Material(ColorAttribute.createDiffuse(color)),
				Usage.Position | Usage.Normal));
		getInstance().transform.translate(x, y, z);
	}

	public Box(float x, float y, float z, float width, float height, float depth, Texture texture) {
		super(modelBuilder.createBox(width, height, depth,
				new Material(TextureAttribute.createDiffuse(texture)),
				Usage.Position | Usage.Normal | Usage.TextureCoordinates));
		getInstance().transform.translate(x, y, z);
	}

	public Box(float x, float y, float z, float width, float height, float depth, TextureRegion texture) {
		super(modelBuilder.createBox(width, height, depth,
				new Material(TextureAttribute.createDiffuse(texture)),
				Usage.Position | Usage.Normal | Usage.TextureCoordinates));
		getInstance().transform.translate(x, y, z);
	}
	
	public Box(Model model) {
		super(model);
	}
	
	public Box(float x, float y, float z, Model model) {
		super(model);
		getInstance().transform.translate(x, y, z);
	}
	
	public Box(float x, float y, float z, float scaleX, float scaleY, float scaleZ, Model model) {
		super(model);
		getInstance().transform.scale(scaleX, scaleY, scaleZ);
		getInstance().nodes.get(0).scale.set(scaleX, scaleY, scaleZ); 
		getInstance().calculateTransforms();
		getInstance().transform.setTranslation(x, y, z);
	}

}
