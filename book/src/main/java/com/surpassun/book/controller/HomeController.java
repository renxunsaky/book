package com.surpassun.book.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.surpassun.book.util.ViewName;

@Controller
@RequestMapping("/**")
public class HomeController {
	
	private Logger log = Logger.getLogger(HomeController.class.getName());
	
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	@RequestMapping(method = RequestMethod.GET)
	public String view() {
		return ViewName.HOME;
	}
	
	@RequestMapping(value = "/upload.html", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, HttpServletResponse response) {
		Map<String, List<BlobKey>> images = blobstoreService.getUploads(request);
		
		for (Entry<String, List<BlobKey>> entries : images.entrySet()) {
			String key = entries.getKey();
			List<BlobKey> value = entries.getValue();
			
			log.info("uploaded image item : " + key);
			
			for (BlobKey blobKey : value) {
				log.info("uploaded image blobkey for item[" + key + "] :" + blobKey);
			}
		}
		
		return ViewName.HOME;
	}
}
