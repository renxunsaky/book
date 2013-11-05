package com.surpassun.book.service.impl;

import java.util.Arrays;
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
		Language language = datastore.find().type(Language.class)
				.addFilter("key", FilterOperator.EQUAL, key)
				.addFilter("lang", FilterOperator.EQUAL, lang)
				.returnUnique().now();
		if (language != null) {
			return language.getContent();
		} else {
			return null;
		}
	}

	@Override
	public void saveLangForCategory(CategoryBean bean) {
		String nameKey = generateKey("name", bean.getId());
		String descKey = generateKey("description", bean.getId());
		String lang = bean.getLang();
		
		Language existedName = getExistedLang(nameKey, lang);
		if (existedName != null) {
			existedName.setContent(bean.getName());
			update(existedName);
		} else {
			existedName = new Language(nameKey, bean.getName(),  lang);
			save(existedName);
		}
		
		Language existedDesc = getExistedLang(descKey, lang);
		if (existedDesc != null) {
			existedDesc.setContent(bean.getDescription());
			update(existedDesc);
		} else {
			existedDesc = new Language(descKey, bean.getDescription(),  lang);
			save(existedDesc);
		}
	}

	protected String generateKey(String fieldName, Long id) {
		return Category.class.getName() + id + fieldName;
	}
		
	@Override
	public List<Language> getLangByCategoryId(Long categoryId) {
		String[] values = {generateKey("name", categoryId), generateKey("description", categoryId)};
		return datastore.find().type(Language.class).addFilter("key", FilterOperator.IN, Arrays.asList(values)).returnAll().now();
	}

	@Override
	public void deleteLangByCategoryId(long categoryId) {
		datastore.deleteAll(getLangByCategoryId(categoryId));
	}
	
	private Language getExistedLang(String key, String lang) {
		return datastore.find().type(Language.class)
			.addFilter("key", FilterOperator.EQUAL, key)
			.addFilter("lang", FilterOperator.EQUAL, lang)
			.returnUnique().now();
	}
	
}
