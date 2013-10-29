package com.surpassun.book.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Category;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.surpassun.book.model.Img;
import com.surpassun.book.service.ImgService;

@Service("imageService")
public class ImgServiceImpl extends AbstractServiceImpl implements ImgService {
	
	ImagesService imgService = ImagesServiceFactory.getImagesService();
	
	@Override
	public Key saveImage(Img image) {
		String servingUrl = imgService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(new BlobKey(image.getBlobKey())));
		image.setServingUrl(servingUrl);
		return datastore.store(image);
	}

	@Override
	public Map<Img, Key> saveImages(List<Img> uploadedImages) {
		if (uploadedImages != null) {
			for (Img image : uploadedImages) {
				String servingUrl = imgService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(new BlobKey(image.getBlobKey())));
				image.setServingUrl(servingUrl);
			}
		}
		return datastore.storeAll(uploadedImages);
	}

	@Override
	public Img getImage(String blobKey) {
		return datastore.load(Img.class, blobKey);
	}

	@Override
	public List<Img> getImages(List<String> blobKeys) {
		List<Img> results = new ArrayList<>();
		for (String key : blobKeys) {
			results.add(getImage(key));
		}
		return results;
	}

	@Override
	public void updateImage(Img image) {
		datastore.storeOrUpdate(image);
	}

	@Override
	public void deleteImage(Img image) {
		datastore.delete(image);
	}

	@Override
	public List<Img> getImagesByCategory(String categoryBlobKey) {
		Category category = datastore.load(Category.class, categoryBlobKey);
		Iterator<Img> images = datastore.find().type(Img.class)
			.addFilter("category", FilterOperator.EQUAL, category)
			.returnResultsNow();
		return Lists.newArrayList(images);
	}

}
