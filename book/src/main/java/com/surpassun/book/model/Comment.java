package com.surpassun.book.model;

import java.sql.Blob;
import java.util.Date;

import com.vercer.engine.persist.annotation.Parent;
import com.vercer.engine.persist.annotation.Type;

public class Comment {

	private long id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
