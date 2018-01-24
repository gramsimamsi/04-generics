package de.fhro.inf.prg3.a04.collections;

import java.util.function.Function;

public interface SimpleList<T> extends Iterable<T> {
	/**
	 * Add a given object to the back of the list.
	 * @param o Object of generic type T to be added
	 */
	void add(T o);

	/**
	 * @return current size of the list
	 */
	int size();

	default void addDefault(Class<T> defaultClass) throws IllegalAccessException, InstantiationException {
		this.add(defaultClass.newInstance());
	}

	/**
	 * Get a new SimpleList instance with all items of this list which match the given filter
	 * @param filter SimpleFilter instance
	 * @return new SimpleList instance
	 */
	default SimpleList filter(SimpleFilter<T> filter) {
		SimpleList<T> result = new SimpleListImpl<>();
		for(T o : this){
			if(filter.include(o)){
				result.add(o);
			}
		}
		return result;
	}

	default <R> SimpleList<R> map(Function<T, R> transform){

		SimpleList<R> result;
		try {
			result = (SimpleList<R>) getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			result = new SimpleListImpl<>();
		}
		for(T t : this){
			result.add(transform.apply(t));
		}
		return result;

	}

}
