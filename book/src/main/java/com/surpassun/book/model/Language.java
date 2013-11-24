package com.surpassun.book.model;

import com.google.code.twig.annotation.Id;
import com.google.code.twig.annotation.Index;

public class Language {

	@Id
	private Long id;
	
	@Index
	private String key;
	private String content;
	
	@Index
	private String lang;
	
	public Language() {
	}
	
	public Language(String key, String content, String lang) {
		this.key = key;
		this.content = content;
		this.lang = lang;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
}
