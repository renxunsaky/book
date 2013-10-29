package com.surpassun.book.service;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.surpassun.book.model.Category;

public interface CategoryService {

	public Key saveCategory(Category category);

	public Category getCategory(String blobKey);
	
	public List<Category> getCategories(List<String> blobKeys);
	
	public List<Category> getAllCategories();
	
	public void updateCategory(Category category);
	
	public void deleteCategory(Category category);
	
}
