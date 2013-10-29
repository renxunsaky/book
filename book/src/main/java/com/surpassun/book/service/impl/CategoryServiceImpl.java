package com.surpassun.book.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Key;
import com.surpassun.book.model.Category;
import com.surpassun.book.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl extends AbstractServiceImpl implements CategoryService {

	@Override
	public Key saveCategory(Category category) {
		return datastore.store(category);
	}

	@Override
	public Category getCategory(String blobKey) {
		return datastore.load(Category.class, blobKey);
	}

	@Override
	public List<Category> getCategories(List<String> blobKeys) {
		List<Category> results = new ArrayList<>();
		for (String key : blobKeys) {
			results.add(getCategory(key));
		}
		return results;
	}

	@Override
	public void updateCategory(Category category) {
		datastore.storeOrUpdate(category);
	}

	@Override
	public void deleteCategory(Category category) {
		datastore.delete(category);
	}

	@Override
	public List<Category> getAllCategories() {
		return datastore.find().type(Category.class).returnAllResultsNow();
	}

}
