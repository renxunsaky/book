package com.surpassun.book.model;

import java.util.Date;
import java.util.List;

import com.google.code.twig.annotation.Child;
import com.google.code.twig.annotation.Id;
import com.google.code.twig.annotation.Parent;
import com.google.code.twig.annotation.Type;

public class Img {

	@Id
	private Long id;
	
	private String blobKey;
	
	@Type(Date.class)
	private Date createDate;
	
	@Parent
	private Category category;
	
	private int views;
	
	private String servingUrl;
	
	@Child
	private List<Comment> comments;

	public Img(String blobKey, Date createDate, Category category, int views,
			List<Comment> comments) {
		super();
		this.blobKey = blobKey;
		this.createDate = createDate;
		this.category = category;
		this.views = views;
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBlobKey() {
		return blobKey;
	}

	public void setBlobKey(String blobKey) {
		this.blobKey = blobKey;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getServingUrl() {
		return servingUrl;
	}

	public void setServingUrl(String servingUrl) {
		this.servingUrl = servingUrl;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
