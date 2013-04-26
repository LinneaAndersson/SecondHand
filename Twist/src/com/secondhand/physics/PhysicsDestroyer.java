package com.secondhand.physics;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.secondhand.debug.MyDebug;

public final class PhysicsDestroyer {

	// true when a body is being destroyed
	private boolean killingInProcess;

	private Engine engine;
	private PhysicsWorld physicsWorld;
	private Boolean bodyToo;
	private static BlockingQueue<IShape> queue;
	private static PhysicsDestroyer instance;

	private PhysicsDestroyer() { }
	
	// reset for a new physicsworld
	public void reset(final Engine engine, final PhysicsWorld physicsWorld) {
		killingInProcess = false;
		//perhaps a normal linkedList would suffice
		queue = new LinkedBlockingQueue<IShape>();
	
		
		this.engine = engine;
		this.physicsWorld = physicsWorld;
		bodyToo = true;
	}
	
	
 	public static PhysicsDestroyer getInstance(){
		if(instance == null){
			instance = new PhysicsDestroyer();
		}
		return instance;
	}
	
	public void destroy(final IShape mySprite, final boolean detachSprite){
		if(queue.isEmpty()){
			dest(mySprite, detachSprite);
		}else{
			queue.add(mySprite);
		}
	}
	
	
	public void startDestruction(){
		while(!queue.isEmpty()){
			dest(queue.poll(), true);
		}
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
	private void dest(final IShape mySprite, final boolean detachSprite) {
		
		if (!killingInProcess) {
			MyDebug.i("commence destruction");
			killingInProcess = true;
			final PhysicsConnector physicsConnector = physicsWorld
					.getPhysicsConnectorManager().findPhysicsConnectorByShape(
							mySprite);
			engine.registerUpdateHandler(new IUpdateHandler() {
				@Override
				public void onUpdate(final float pSecondsElapsed) {
					engine.unregisterUpdateHandler(this);
					engine.runOnUpdateThread(new Runnable() {
						@Override
						public void run() {
							physicsWorld
									.unregisterPhysicsConnector(physicsConnector);
							// myFixture.getBody().destroyFixture(myFixture);
							// don't know if the above is needed
							if (bodyToo) {
								MyDebug.i(physicsConnector.getBody()
										+ " will be destroyed");
								physicsWorld.destroyBody(physicsConnector
										.getBody());
							}

							if (detachSprite) {
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
		} else {
			MyDebug.d("killing already in process, ignored!");
		}
	}

}
