package com.bsiag.education.di.examples.di14;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class PersonServiceProvider implements Provider<IPersonService> {

	public Set<IPersonService> orderedServices = new TreeSet<IPersonService>(new P_RankedServiceComparator());
	
	@Inject
	private PersonServiceProvider(){
	}
	
	public void add(IPersonService service){
		orderedServices.add(service);
	}
	
	public List<IPersonService> getOrderedServices() {
		return new ArrayList<IPersonService>(orderedServices);
	}
	
	@Override
	public IPersonService get() {
		if(orderedServices.size() > 0){
			return orderedServices.iterator().next();
		}
		return null;
	}
	
	
	
	private class P_RankedServiceComparator implements Comparator<IPersonService>{
		@Override
		public int compare(IPersonService o1, IPersonService o2) {
			return o2.getRanking()-o1.getRanking();
		}
	}
}
