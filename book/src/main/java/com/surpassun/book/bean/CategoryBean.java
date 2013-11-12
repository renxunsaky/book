package com.surpassun.book.bean;


public class CategoryBean {

	private Long id;
	private String name;
	private String description;
	private String imageUrl;
	private Boolean active;
	private int axe;
	private String lang;
	private String blobKey;
	
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

	public String getBlobKey() {
		return blobKey;
	}

	public void setBlobKey(String blobKey) {
		this.blobKey = blobKey;
	}
}
