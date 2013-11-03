package com.surpassun.book.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.surpassun.book.model.Comment;
import com.surpassun.book.model.Img;
import com.surpassun.book.service.CommentService;

@Service("commentService")
public class CommentServiceImpl extends AbstractServiceImpl<Comment> implements CommentService {

	public CommentServiceImpl() {
		super(Comment.class);
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
}
