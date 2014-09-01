package com.bsiag.education.di.simple;

import java.util.Map;

import com.bsiag.education.di.simple.internal.InjectorImpl;

public class DI {

	public static Injector createInjector(Map<Class<?>, Class<?> > bindings){
		return new InjectorImpl(bindings);
	}

}
