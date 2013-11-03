package com.surpassun.book.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Key;

public interface AbstractService<T> {
	
	public Key save(T obj);
	
	public Map<T, Key> saveAll(Collection<T> objs);
	
	public T get(String blobkey);
	
	public T get(Long id);
	
	public Class<T> getType();
	
	public List<T> getAll();

	public void update(T obj);

	public void delete(T obj);
	
	public void deleteAll(Collection<T> objs);
}
