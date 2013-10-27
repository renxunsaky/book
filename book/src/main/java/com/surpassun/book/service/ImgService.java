package com.surpassun.book.service;

import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Key;
import com.surpassun.book.model.Img;

public interface ImgService {

	public Key saveImage(Img image);

	public Map<Img, Key> saveImages(List<Img> uploadedImages);
	
	public Img getImage(String blobKey);
	
	public List<Img> getImages(List<String> blobKeys);
	
	public void updateImage(Img image);
	
	public void deleteImage(Img image);
	
	public List<Img> getImagesByCategory(String categoryBlobKey);
}
