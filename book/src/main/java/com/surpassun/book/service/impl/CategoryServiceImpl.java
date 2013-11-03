package com.surpassun.book.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Query.FilterOperator;
import com.surpassun.book.model.Category;
import com.surpassun.book.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl extends AbstractServiceImpl<Category> implements CategoryService {

	public CategoryServiceImpl() {
		super(Category.class);
	}

	@Override
	public List<Category> getCategories(List<String> blobKeys) {
		List<Category> results = new ArrayList<>();
		for (String key : blobKeys) {
			results.add(get(key));
		}
		return results;
	}

	@Override
	public void delete(long id) {
		Category cat = datastore.load(Category.class, id);
		if (cat != null) {
			datastore.delete(cat);
		}
	}
	
	@Override
	public List<Category> getActiveCategories() {
		return datastore.find().type(Category.class).addFilter("active", FilterOperator.EQUAL, Boolean.TRUE).returnAll().now();
	}
}
