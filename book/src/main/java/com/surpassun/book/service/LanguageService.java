package com.surpassun.book.service;

import java.util.List;

import com.surpassun.book.bean.CategoryBean;
import com.surpassun.book.model.Language;


public interface LanguageService extends AbstractService<Language> {

	String getContent(String key, String lang);
	
	void saveLangForCategory(CategoryBean bean);

	List<Language> getLangByCategoryId(Long categoryId);

	void deleteLangByCategoryId(long categoryId);
	
}
