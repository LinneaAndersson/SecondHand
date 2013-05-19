package com.secondhand.view.andengine.entity;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.entity.particle.ParticleSystem;
import org.anddev.andengine.entity.particle.emitter.PointParticleEmitter;
import org.anddev.andengine.entity.particle.initializer.AlphaInitializer;
import org.anddev.andengine.entity.particle.initializer.ColorInitializer;
import org.anddev.andengine.entity.particle.initializer.RotationInitializer;
import org.anddev.andengine.entity.particle.initializer.VelocityInitializer;
import org.anddev.andengine.entity.particle.modifier.AlphaModifier;
import org.anddev.andengine.entity.particle.modifier.ColorModifier;
import org.anddev.andengine.entity.particle.modifier.ExpireModifier;
import org.anddev.andengine.entity.particle.modifier.ScaleModifier;

import com.secondhand.view.resource.TextureRegions;

public class RocketEmitter extends ParticleSystem {

	public final static float DURATION = 1;
	
	public RocketEmitter(final float surfaceX, final float surfaceY, final float centerX, final float centerY) {
		super(new PointParticleEmitter(surfaceX, surfaceY), 60, 60, 10, 
				TextureRegions.getInstance().rocketParticleTexture);
		
		addParticleInitializer(new ColorInitializer(1, 0, 0));
		addParticleInitializer(new AlphaInitializer(0));
		setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
		
		final float factor = 2;
		final float velocityX = (surfaceX - centerX) * factor;
		final float velocityY = (surfaceY - centerY) * factor;
		VelocityInitializer particleVelocity = new VelocityInitializer(velocityX, velocityY);
		addParticleInitializer(particleVelocity);
		               
		addParticleInitializer(new RotationInitializer(0.0f, 360.0f));
		addParticleModifier(new ScaleModifier(0.5f, 0.1f, 0,1));
		addParticleModifier(new ColorModifier(1, 1, 0, 0.5f, 0, 0, 0, 3));
		addParticleModifier(new ColorModifier(1, 1, 0.5f, 1, 0, 1, 4, 6));
		addParticleModifier(new AlphaModifier(0, 1, 0, 1));
		addParticleModifier(new AlphaModifier(1, 0, 5, 6));
		addParticleModifier(new ExpireModifier(DURATION));
	}
}
