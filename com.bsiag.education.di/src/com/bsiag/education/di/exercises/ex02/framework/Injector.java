package com.bsiag.education.di.exercises.ex02.framework;

public interface Injector {

	/**
	 * returns an instance bound to the given key. If the key is not bound to
	 * an object and is a concrete class (not interface, not abstract) an
	 * instance of the key is returned.
	 * If a bound class is annotated with @Singleton, 
	 * 
	 * @param key
	 * @return null if the key could not be resolved nor instanciated.
	 */
	<T> T getInstance(Class<T> key);

}
