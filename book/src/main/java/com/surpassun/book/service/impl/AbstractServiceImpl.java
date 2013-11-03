package com.surpassun.book.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Key;
import com.google.code.twig.ObjectDatastore;
import com.google.code.twig.annotation.AnnotationObjectDatastore;
import com.surpassun.book.service.AbstractService;

@Service
public class AbstractServiceImpl<T> implements AbstractService<T> {

	ObjectDatastore datastore = new AnnotationObjectDatastore();
	
	private Class<T> type;
	
	public AbstractServiceImpl() {
	}
	
	public AbstractServiceImpl(Class<T> type) {
		this.type = type;
	}
	
	public Key save(T obj) {
		return datastore.store(obj);
	}
	
	public Map<T, Key> saveAll(Collection<T> objs) {
		return datastore.storeAll(objs);
	}
	
	public T get(String blobkey) {
		return datastore.load(getType(), blobkey);
	}
	
	public T get(Long id) {
		return datastore.load(getType(), id);
	}
	
	public Class<T> getType() {
		return this.type;
	}
	
	public List<T> getAll() {
		return datastore.find().type(getType()).returnAll().now();
	}
	
	public void update(T obj) {
		datastore.storeOrUpdate(obj);
	}

	public void delete(T obj) {
		datastore.delete(obj);
	}
	
	public void deleteAll(Collection<T> objs) {
		datastore.deleteAll(objs);
	}
}
