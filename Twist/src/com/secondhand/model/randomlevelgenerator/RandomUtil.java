package com.secondhand.model.randomlevelgenerator;

import java.util.Random;


final class RandomUtil {

	private RandomUtil() {}
	
    public static <T extends Enum<?>> T randomEnum(final Random random, final Class<T> clazz){
        final int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
 
    public static float nextFloat(final Random rng, final float min, final float max) {
    	return rng.nextFloat() * (max - min)
    			+ min;
	
    }
    
	
	
}
