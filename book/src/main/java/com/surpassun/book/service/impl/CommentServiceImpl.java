package com.surpassun.book.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Key;
import com.surpassun.book.model.Comment;
import com.surpassun.book.model.Img;
import com.surpassun.book.service.CommentService;

@Service("commentService")
public class CommentServiceImpl extends AbstractServiceImpl implements CommentService {

	@Override
	public Key saveComment(Comment comment) {
		return datastore.store(comment);
	}

	@Override
	public Comment getComment(String blobKey) {
		return datastore.load(Comment.class, blobKey);
	}

	@Override
	public List<Comment> getCommentsByImage(String imgBlobKey) {
		Img img = datastore.load(Img.class, imgBlobKey);
		if (img != null) {
			return img.getComments();
		} else {
			return null;
		}
	}

	@Override
	public void updateComment(Comment comment) {
		datastore.storeOrUpdate(comment);
	}

	@Override
	public void deleteComment(Comment comment) {
		datastore.delete(comment);
	}

}
