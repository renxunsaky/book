package com.surpassun.book.service;

import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Key;
import com.surpassun.book.model.Img;

public interface ImgService {

	public Key saveImage(Img image);

	public Map<Img, Key> saveImages(List<Img> uploadedImages);
}
