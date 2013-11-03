package com.surpassun.book.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Query.FilterOperator;
import com.surpassun.book.bean.CategoryBean;
import com.surpassun.book.model.Category;
import com.surpassun.book.model.Language;
import com.surpassun.book.service.LanguageService;

@Service("languageService")
public class LanguageServiceImpl extends AbstractServiceImpl<Language> implements LanguageService {

	public LanguageServiceImpl() {
		super(Language.class);
	}

	@Override
	public String getContent(String key, String lang) {
		List<Language> results = datastore.find().type(Language.class)
				.addFilter("key", FilterOperator.EQUAL, key)
				.addFilter("lang", FilterOperator.EQUAL, lang)
				.returnAll().now();
		
		if (results != null && results.size() > 0) {
			return results.get(0).getContent();
		} else {
			return null;
		}
	}

	@Override
	public void saveLangForCategory(CategoryBean bean) {
		List<Language> objs = new ArrayList<Language>();
		Language name = new Language(generateKey("name", bean.getId()), bean.getName(),  bean.getLang());
		Language description = new Language(generateKey("description", bean.getId()), bean.getDescription(),  bean.getLang());
		objs.add(name);
		objs.add(description);
		saveAll(objs);
	}
	
	private String generateKey(String fieldName, Long id) {
		return Category.class.getName() + id + fieldName;
	}
}
