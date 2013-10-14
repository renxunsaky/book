package com.surpassun.book.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Key;
import com.surpassun.book.model.Img;
import com.surpassun.book.service.ImgService;

@Service("imageService")
public class ImgServiceImpl extends AbstractServiceImpl implements ImgService {
	
	@Override
	public Key saveImage(Img image) {
		return datastore.store(image);
	}

	@Override
	public Map<Img, Key> saveImages(List<Img> uploadedImages) {
		return datastore.storeAll(uploadedImages);
	}

}
