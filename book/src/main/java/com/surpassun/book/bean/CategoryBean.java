package com.surpassun.book.bean;

import org.springframework.beans.BeanUtils;

import com.surpassun.book.model.Category;
import com.surpassun.book.service.LanguageService;

public class CategoryBean {

	private Long id;
	private String name;
	private String description;
	private String imageUrl;
	private Boolean active;
	private int axe;
	private String lang;
	
	public CategoryBean() {
	}
	
	public CategoryBean(String name, String description, String imageUrl, Boolean active, int axe, String lang) {
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.active = active;
		this.axe = axe;
		this.lang = lang;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean isActive() {
		return active;
	}
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public int getAxe() {
		return axe;
	}

	public void setAxe(int axe) {
		this.axe = axe;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public void copyFromEntity(Category category, String lang, LanguageService languageService) {
		BeanUtils.copyProperties(category, this);
		setLang(lang);
		setName(languageService.getContent(Category.class.getName() + category.getId() + "name", lang));
		setDescription(languageService.getContent(Category.class.getName() + category.getId() + "description", lang));
	}

	public Category convertToEntity(boolean changeImage) {
		Category cat = new Category();
		if (!changeImage) {
			String[] ignoredProps = {"imageUrl"};
			BeanUtils.copyProperties(this, cat, ignoredProps);
		} else {
			BeanUtils.copyProperties(this, cat);
		}
		return cat;
	}
}
