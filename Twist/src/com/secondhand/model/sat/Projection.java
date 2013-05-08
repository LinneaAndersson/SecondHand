package com.secondhand.model.sat;

class Projection {

	private final float min;
	private final  float max;

	public Projection(final float min, final float max) {
		this.min = min;
		this.max = max;
	}

	public boolean overlap(final Projection other) {

		return (!(other.max < this.min || this.max < other.min));  
	}	
}