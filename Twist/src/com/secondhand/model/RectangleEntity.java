package com.secondhand.model;



public abstract class RectangleEntity extends Entity {
		

	private final Vector2 position;
	private final float width;
	private final float height;
	
	/*public PolygonEntity(final Vector2 position,  final List<Vector2> polygon, 
			final boolean isEdible, final GameWorld level) {
		super(isEdible, level);
		
		this.position = position;
		this.polygon = polygon;
	}*/
	
	
	public RectangleEntity(final Vector2 position, final float width, 
			final float height, final boolean isEdible, final GameWorld level) {
		super(isEdible, level);
		this.position = position;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public float getRadius() {
		// because don't really use this method for rectangles entities at all.
		return 0;
	}
	
	@Override
	public void createType(){
	}	
	
	@Override
	public boolean isCircle(){
		return false;
	}
	
	public float getWidth() {
		return this.width;
	}
	
	public Vector2 getPosition() {
		return this.position;
	}
	
	public float getHeight() {
		return this.height;
	}
}
