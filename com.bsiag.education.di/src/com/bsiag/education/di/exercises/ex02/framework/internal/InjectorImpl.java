package com.bsiag.education.di.exercises.ex02.framework.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.bsiag.education.di.exercises.ex02.framework.Injector;

public class InjectorImpl implements Injector {

	private Map<Class<?>, Class<?>> bindings;
	private Map<Class<?>, Object> instances;

	public InjectorImpl(Map<Class<?>, Class<?>> bindings) {
		this.bindings = bindings;
		instances = new HashMap<Class<?>, Object>();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getInstance(Class<T> key) {
		Object instance = instances.get(key);
		if (instance != null) {
			// check singleton
			if (instance.getClass().getAnnotation(Singleton.class) != null
					|| instance.getClass().getAnnotation(
							com.google.inject.Singleton.class) != null) {
				return (T) instance;
			}
		}

		return createInstance(key);
	}

	@SuppressWarnings("unchecked")
	private <T> T createInstance(Class<T> key) {
		Class<?> clazz = this.bindings.get(key);
		if (clazz == null) {
			// check if auto binding is possible
			if ((key.getModifiers() & (Modifier.ABSTRACT | Modifier.INTERFACE)) == 0) {
				clazz = key;
			} else {
				System.out.println("no binding for '" + key.getName()
						+ "' registered.");
				return null;
			}
		}
		T instance = null;
		Constructor<?> constructor = findInjectConstructor(clazz);
		try {
			if (constructor != null) {
				// ensure private constructors can be instanciated
				constructor.setAccessible(true);
				if (constructor.getParameterTypes() != null) {
					instance = (T) constructor.newInstance(getArguments(
							constructor).toArray());
				} else {
					instance = (T) constructor.newInstance();
				}
			} else {
				instance = (T) clazz.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		instances.put(key, instance);
		return instance;
	}

	private Constructor<?> findInjectConstructor(Class<?> clazz) {
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		if (constructors != null) {
			for (Constructor<?> c : constructors) {
				if (c.getAnnotation(Inject.class) != null
						|| c.getAnnotation(com.google.inject.Inject.class) != null) {
					return c;
				}
			}
		}
		return null;
	}

	private List<Object> getArguments(Constructor<?> constructor) {
		List<Object> arguments = new ArrayList<Object>();
		// resolve arguments
		Class<?>[] parameterTypes = constructor.getParameterTypes();
		for (Class<?> paramType : parameterTypes) {
			// recursive get instances of the injected parameters.
			Object param = getInstance(paramType);
			arguments.add(param);
		}
		return arguments;
	}

}
