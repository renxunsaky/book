package com.surpassun.book.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.google.appengine.api.blobstore.BlobKey;
import com.surpassun.book.bean.CategoryBean;
import com.surpassun.book.model.Category;
import com.surpassun.book.service.LanguageService;

public class BookUtil {
	
	private final static String DEFAULT_LANG = "zh";

	public static String getLang(HttpServletRequest request) {
		String lang = DEFAULT_LANG;
		Locale locale = RequestContextUtils.getLocale(request);
		if (locale != null) {
			lang = locale.getLanguage();
		}
		return lang;
	}

	public static CategoryBean convertCategoryToBean(Category category, String lang,
			LanguageService languageService) {
		if (category == null) {
			return null;
		}
		CategoryBean bean = new CategoryBean();
		BeanUtils.copyProperties(category, bean);
		bean.setLang(lang);
		bean.setName(languageService.getContent(Category.class.getName() + category.getId() + "name", lang));
		bean.setDescription(languageService.getContent(Category.class.getName() + category.getId() + "description", lang));
		return bean;
	}
	
	public static Category convertToCategory(CategoryBean bean, BlobKey blobKey) {
		Category cat = new Category();
		BeanUtils.copyProperties(bean, cat);
		
		//the admin has uploaded a new icon
		if (blobKey != null) {
			cat.setBlobKey(blobKey.getKeyString());
		}
		return cat;
	}
	
	public static boolean hasAdminPermission(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.isAuthenticated();
	}
}
