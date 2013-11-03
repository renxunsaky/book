package com.surpassun.book.model;

import com.google.code.twig.annotation.Id;

public class Category {

	@Id
	private Long id;
	private String imageUrl;
	private Boolean active;
	private int axe;
	
	public Category() {
	}
	
	public Category(String imageUrl, Boolean active, int axe) {
		this.imageUrl = imageUrl;
		this.active = active;
		this.axe = axe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
