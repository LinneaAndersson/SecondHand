package com.secondhand.util;

import java.util.Random;


public final class RandomUtil {

	private RandomUtil() {}
	
    public static <T extends Enum<?>> T randomEnum(final Random random, final Class<T> clazz){
        final int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
	
}
