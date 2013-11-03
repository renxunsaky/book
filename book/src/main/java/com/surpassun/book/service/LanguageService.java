package com.surpassun.book.service;

import com.surpassun.book.bean.CategoryBean;
import com.surpassun.book.model.Language;


public interface LanguageService extends AbstractService<Language> {

	String getContent(String key, String lang);
	
	void saveLangForCategory(CategoryBean bean);
	
}
