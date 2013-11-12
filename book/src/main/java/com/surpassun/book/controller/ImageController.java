package com.surpassun.book.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.surpassun.book.model.Category;
import com.surpassun.book.model.Img;
import com.surpassun.book.service.CategoryService;
import com.surpassun.book.service.ImgService;
import com.surpassun.book.service.LanguageService;
import com.surpassun.book.util.BookUtil;
import com.surpassun.book.util.Constants;
import com.surpassun.book.util.ViewName;

@Controller
@RequestMapping("/images/**")
public class ImageController {
	
	private Logger log = Logger.getLogger(ImageController.class.getName());
	
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ImgService imageService;

    @Autowired
    private LanguageService languageService;

	@RequestMapping(value = "/images/{categoryId}", method = RequestMethod.GET)
	public String imagesView(HttpServletRequest request, @PathVariable("categoryId") Long categoryId, Model model) {
		Category category = categoryService.get(categoryId);
		List<Img> images = imageService.getImagesByCategoryId(categoryId);
		
		String successPath = blobstoreService.createUploadUrl(Constants.UPLOAD_IMAGES_PATH);
		model.addAttribute(Constants.SUCCESS_PATH, successPath);
		model.addAttribute(Constants.CATEGORY_BEAN, BookUtil.convertCategoryToBean(category, BookUtil.getLang(request), languageService));
		model.addAttribute(Constants.IMAGES, images);
		return ViewName.CATEGORY;
	}
	
	@RequestMapping(value = "/images/upload", method = RequestMethod.POST)
	public String uploadImages(HttpServletRequest request, @RequestParam(required = true) Long categoryId, Model model) {
		if (BookUtil.hasAdminPermission(request)) {
			Map<String, List<BlobKey>> images = blobstoreService.getUploads(request);
			
			ImagesService imgService = ImagesServiceFactory.getImagesService();
			List<Img> uploadedImages = new ArrayList<Img>();
			for (Entry<String, List<BlobKey>> entries : images.entrySet()) {
				String key = entries.getKey();
				List<BlobKey> value = entries.getValue();
				
				log.info("uploaded image item : " + key);
				
				for (BlobKey blobKey : value) {
					try {
						log.info("uploaded image blobkey for item[" + key + "] :" + blobKey);
						
						String imgUrl = imgService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKey));
						Img image = new Img(blobKey.getKeyString(), new Date(), categoryId, 0, null, false, imgUrl);
						uploadedImages.add(image);
					} catch (Exception e) {
						log.log(Level.SEVERE, "error while uploading image, blok key : " + blobKey.getKeyString());
					}
				}
			}
			
			imageService.saveImages(uploadedImages);
			
			log.info(uploadedImages.size() + " images uploaded, redirect to the category page");
		} else {
			log.warning("The user do not have permission to ");
		}
		
		return ViewName.REDIRECT_CATEGORY_VIEW + categoryId + Constants.HTML_SUFFIX;
	}
	
	@RequestMapping(value = "/images/delete/{imageId}", method = RequestMethod.POST)
	public String deletingImage(@PathVariable("imageId") Long imageId, @RequestParam(required = true) Long categoryId) {
		imageService.delete(imageId);
		return ViewName.REDIRECT_CATEGORY_VIEW + categoryId + Constants.HTML_SUFFIX;
	}
	
	@RequestMapping(value = "/images/showInFront/{imageId}", method = RequestMethod.POST)
	@ResponseBody
	public String showInFront(@PathVariable("imageId") Long imageId, @RequestParam("showInFront") boolean showInFront) {
		if (imageService.showImageInFront(imageId, showInFront)) {
			return Constants.OK;
		} else {
			return Constants.KO;
		}
	}
}
