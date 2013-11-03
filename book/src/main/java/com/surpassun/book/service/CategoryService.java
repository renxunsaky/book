package com.surpassun.book.service;

import java.util.List;

import com.surpassun.book.model.Category;

public interface CategoryService extends AbstractService<Category> {

	public List<Category> getCategories(List<String> blobKeys);

	public void delete(long id);

	public List<Category> getActiveCategories();
}
