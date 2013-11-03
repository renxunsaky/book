package com.surpassun.book.model;

import java.sql.Blob;
import java.util.Date;

import com.google.code.twig.annotation.Id;
import com.google.code.twig.annotation.Parent;
import com.google.code.twig.annotation.Type;

public class Comment {

	@Id
	private Long id;
	private int starts;
	
	@Type(Blob.class)
	private String content;
	
	@Type(Date.class)
	private Date commentDate;
	private String ip;
	
	@Parent
	private Img image;
	
	public Comment() {
	}

	public Comment(int starts, String content, Date commentDate, String ip,
			Img image) {
		super();
		this.starts = starts;
		this.content = content;
		this.commentDate = commentDate;
		this.ip = ip;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStarts() {
		return starts;
	}

	public void setStarts(int starts) {
		this.starts = starts;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Img getImage() {
		return image;
	}

	public void setImage(Img image) {
		this.image = image;
	}
}
