package com.bsiag.education.di.exercises.ex02.framework.internal;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bsiag.education.di.exercises.ex02.framework.Injector;

/**
 * This class is to implement. Instances are usually create by reflection. 
 * See:
 * - {@link Class#getAnnotation(Class)} 
 * - {@link Class#getDeclaredConstructors()}
 * - {@link Constructor#getAnnotation(Class)}
 * - {@link Constructor#newInstance(Object...)}
 * - {@link Constructor#getTypeParameters()}
 */
public class InjectorImpl implements Injector {

	private Map<Class<?>, Class<?>> bindings;
	private Map<Class<?>, Object> instances;

	public InjectorImpl(Map<Class<?>, Class<?>> bindings) {
		this.bindings = bindings;
		instances = new HashMap<Class<?>, Object>();
	}

	@Override
	public <T> T getInstance(Class<T> key) {
		Object instance = instances.get(key);
		if (instance != null) {
			// TODO: check singleton, return the instance if it has a @Singleton annotation.
		}

		return createInstance(key);
	}

	private <T> T createInstance(Class<T> key) {
		Class<?> clazz = this.bindings.get(key);
		if (clazz == null) {
			// TODO: check if the key itself can be instanciated.  
			// Only needed if the binding Pizza to Pizza is removed.

		}
		T instance = null;
		Constructor<?> constructor = findInjectConstructor(clazz);
		
		// TODO: create the instance of the clazz. Use the getArguments(Constructor) to resolve the arguments 
		// see: - Constructor.setAccessible(true)
		//      - Constructor.newInstance(Object ... initargs)
		//		- Class.newInstance()

		// store for next lookup
		instances.put(key, instance);
		return instance;
	}

	private Constructor<?> findInjectConstructor(Class<?> clazz) {
		/* TODO: find a constructor with the @Inject annotation return null otherwise.
		 * Class#getDeclaredConstructors()
		*/
		return null;
	}

	private List<Object> getArguments(Constructor<?> constructor) {
		List<Object> arguments = new ArrayList<Object>();

		// TODO: resolve all arguments for the constructor
		// see: Constructor.getTypeParameters()
		
		return arguments;
	}

}
