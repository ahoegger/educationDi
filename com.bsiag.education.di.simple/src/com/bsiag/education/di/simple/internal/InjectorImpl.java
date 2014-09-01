package com.bsiag.education.di.simple.internal;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bsiag.education.di.simple.Inject;
import com.bsiag.education.di.simple.Injector;

public class InjectorImpl implements Injector {

	private Map<Class<?>, Class<?>> bindings;

	public InjectorImpl(Map<Class<?>, Class<?>> bindings) {
		this.bindings = bindings;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T createInstance(Class<T> key) {
		Class<?> clazz = this.bindings.get(key);
		Constructor<?>[] constructors = clazz.getConstructors();
		Constructor<?> constructor = constructors[0];
		T instance = null;
		try {
			instance = (T) constructor.newInstance(getArguments(constructor));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}

	private List<Object> getArguments(Constructor<?> constructor){
		List<Object> arguments = new ArrayList<Object>();
		if(constructor.getAnnotation(Inject.class)!= null){
			// resolve arguments
			Class<?>[] parameterTypes = constructor.getParameterTypes();
			for(Class<?> paramType : parameterTypes){
				Object param = createInstance(paramType);
				arguments.add(param);
			}
		}
		return arguments;
	}

}
