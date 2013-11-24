package com.surpassun.book.model;

import com.google.code.twig.annotation.Entity;
import com.google.code.twig.annotation.Id;
import com.google.code.twig.annotation.Index;

@Entity(allocateIdsBy = 0)
public class Category {

	@Id
	private Long id;
	private String imageUrl;
	@Index
	private Boolean active;
	@Index
	private int axe;
	//blob key of the image
	private String blobKey;
	
	public Category() {
	}
	
	public Category(String imageUrl, String blobKey, Boolean active, int axe) {
		this.imageUrl = imageUrl;
		this.blobKey = blobKey;
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

	public String getBlobKey() {
		return blobKey;
	}

	public void setBlobKey(String blobKey) {
		this.blobKey = blobKey;
	}
}
