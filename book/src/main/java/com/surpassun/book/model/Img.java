package com.surpassun.book.model;

import java.util.Date;
import java.util.List;

import com.google.code.twig.annotation.Child;
import com.google.code.twig.annotation.Id;
import com.google.code.twig.annotation.Index;
import com.google.code.twig.annotation.Type;

public class Img {

	@Id
	private Long id;
	
	@Index
	private String blobKey;
	
	@Type(Date.class)
	private Date createDate;
	
	@Index
	private Long categoryId;
	
	private int views;
	
	private String servingUrl;
	
	@Child
	private List<Comment> comments;
	
	@Index
	private boolean showInFront;
	
	public Img() {
	}

	public Img(String blobKey, Date createDate, Long categoryId, int views,
			List<Comment> comments, boolean showInFront, String servingUrl) {
		super();
		this.blobKey = blobKey;
		this.createDate = createDate;
		this.categoryId = categoryId;
		this.views = views;
		this.comments = comments;
		this.showInFront = showInFront;
		this.servingUrl = servingUrl;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public boolean isShowInFront() {
		return showInFront;
	}

	public void setShowInFront(boolean showInFront) {
		this.showInFront = showInFront;
	}
}
