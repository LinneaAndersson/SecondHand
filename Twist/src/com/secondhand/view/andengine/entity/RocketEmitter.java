package com.secondhand.view.andengine.entity;

import org.anddev.andengine.entity.particle.ParticleSystem;
import org.anddev.andengine.entity.particle.emitter.IParticleEmitter;
import org.anddev.andengine.entity.particle.emitter.PointParticleEmitter;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.secondhand.view.resource.loader.TextureRegionLoader;

public class RocketEmitter extends ParticleSystem {

	public RocketEmitter(final float surfaceX, final float surfaceY, final float centerX, final float centerY) {
		super(new PointParticleEmitter(surfaceX, surfaceY), 60, 60, 100, 
				TextureRegionLoader.getInstance().loadTextureRegion("particle.png", 256, 256));
	}

}
