package com.bsiag.education.di.simple;

public interface Injector {

	<T> T createInstance(Class<T> key);


}
