package com.surpassun.book.service;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.surpassun.book.model.Comment;

public interface CommentService {

	public Key saveComment(Comment comment);

	public Comment getComment(String blobKey);
	
	public List<Comment> getCommentsByImage(String imgBlobKey);
	
	public void updateComment(Comment comment);
	
	public void deleteComment(Comment comment);
}
