package com.secondhand.twirl;

import java.util.List;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

public class Level {

	private List<Entity> entityList;
	private int maxSize;
	private PhysicsWorld pW;
	
	
	// many constructors necessary?  
	public Level(){
		this(100);
	}
	
	public Level(int maxSize){
		this(maxSize, new PhysicsWorld(null, false));
	}
	
	public Level(int maxSize, PhysicsWorld pW){
		this.maxSize = maxSize;
		this.pW = pW;
	}
	
	public void addEntity(Entity entity){
		entityList.add(entity);	
	}
	
	public void removeEntity(Entity entity){
		entityList.remove(entity);
	}
	
	public void setEntetyList(List<Entity> list){
		entityList = list;
	}
	
	public List<Entity> getEntityList(){
		return entityList;
	}
	
	public void registerEntities(){
	//TODO somehow connect entities to physics	
	//need factory?
	//or make every entity register itself?
	//entity extend body?
	}
	
	
}
