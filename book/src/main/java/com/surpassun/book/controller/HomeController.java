package com.surpassun.book.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.surpassun.book.model.Img;
import com.surpassun.book.service.ImgService;
import com.surpassun.book.util.ViewName;

@Controller
public class HomeController {
	
	private Logger log = Logger.getLogger(HomeController.class.getName());
	
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    
    @Autowired
    private ImgService imageService;

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String view() {
		return ViewName.HOME;
	}
	
	@RequestMapping(value = "/upload.html", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		Map<String, List<BlobKey>> images = blobstoreService.getUploads(request);
		
		BlobKey k = null;
		
		List<Img> uploadedImages = new ArrayList<Img>();
		for (Entry<String, List<BlobKey>> entries : images.entrySet()) {
			String key = entries.getKey();
			List<BlobKey> value = entries.getValue();
			
			log.info("uploaded image item : " + key);
			
			for (BlobKey blobKey : value) {
				log.info("uploaded image blobkey for item[" + key + "] :" + blobKey);
				Img image = new Img(blobKey.getKeyString(), new Date(), null, 0, null);
				uploadedImages.add(image);
				k = blobKey;
				break;
			}
		}
		
		imageService.saveImages(uploadedImages);
		
		ImagesService imgService = ImagesServiceFactory.getImagesService();
		String imgUrl = imgService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(k));
		model.addAttribute("imgUrl", imgUrl);
		
		return "index";
	}
}
