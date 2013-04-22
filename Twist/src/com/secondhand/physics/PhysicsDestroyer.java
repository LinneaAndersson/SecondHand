package com.secondhand.physics;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.secondhand.debug.MyDebug;

public final class PhysicsDestroyer {

	// true when a body is being destroyed
	private boolean killingInProcess;
	
	private final Engine engine;
	private final PhysicsWorld physicsWorld;
	private final Boolean bodyToo;
	
	public PhysicsDestroyer(final Engine engine, final PhysicsWorld physicsWorld) {
		killingInProcess = false;
		this.engine = engine;
		this.physicsWorld = physicsWorld;
		bodyToo = true;
	}
	
	/*
	 * I found the following method on the AndEngine forum:
	 * http://www.andengine.
	 * org/forums/physics-box2d-extension/body-removal-crashing
	 * -andengine-t9814.html (by RealMayo, a guy who knows lots of good stuff
	 * bout engine. keep a look out for him if there is something you needs to
	 * know) and it seems to work well. I thought i would get null value at the
	 * third debug but it seems that the body still exist in the connector?
	 */
	public void destroy(final IShape mySprite, 
			final boolean detachSprite) {
		final PhysicsWorld mPhysicsWorld = physicsWorld;

		
		if (!killingInProcess) {
			MyDebug.i("commence destruction");
			killingInProcess = true;
			final PhysicsConnector physicsConnector = mPhysicsWorld
					.getPhysicsConnectorManager().findPhysicsConnectorByShape(
							mySprite);
			engine.registerUpdateHandler(new IUpdateHandler() {
				@Override
				public void onUpdate(final float pSecondsElapsed) {
					engine.unregisterUpdateHandler(this);
					engine.runOnUpdateThread(new Runnable() {
						@Override
						public void run() {
							mPhysicsWorld
									.unregisterPhysicsConnector(physicsConnector);
							// myFixture.getBody().destroyFixture(myFixture);
							// don't know if the above is needed
							if (bodyToo) {
								MyDebug.i(physicsConnector.getBody()
										+ " will be destroyed");
								mPhysicsWorld.destroyBody(physicsConnector
										.getBody());
							}
							
							if(detachSprite) {
								mySprite.detachSelf();
							}
							System.gc(); // NOPMD
							killingInProcess = false;
							MyDebug.i(physicsConnector.getBody()
									+ " destruction complete");
						}
					});
				}

				@Override
				public void reset() {
				}
			});
		} else
			MyDebug.d("killing already in process, ignored!");
	}

}
