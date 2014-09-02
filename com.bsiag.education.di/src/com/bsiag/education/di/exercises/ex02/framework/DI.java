package com.bsiag.education.di.exercises.ex02.framework;

import java.util.Map;

import com.bsiag.education.di.exercises.ex02.framework.internal.InjectorImpl;


public final class DI {

	
	/** factory to create a new injector with the given bindings.
	 * @param bindings
	 * @return
	 */
	public static Injector createInjector(Map<Class<?>, Class<?> > bindings){
		return new InjectorImpl(bindings);
	}
}
