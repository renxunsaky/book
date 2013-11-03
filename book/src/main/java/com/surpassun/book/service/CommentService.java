package com.surpassun.book.service;

import java.util.List;

import com.surpassun.book.model.Comment;

public interface CommentService extends AbstractService<Comment> {

	public List<Comment> getCommentsByImage(String imgBlobKey);
	
}
