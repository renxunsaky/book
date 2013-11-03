package com.surpassun.book.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.surpassun.book.bean.CategoryBean;
import com.surpassun.book.model.Category;
import com.surpassun.book.service.CategoryService;
import com.surpassun.book.service.LanguageService;
import com.surpassun.book.util.Constants;
import com.surpassun.book.util.ParamUtil;
import com.surpassun.book.util.ViewName;

@Controller
public class AdminController {
	
	private Logger log = Logger.getLogger(AdminController.class.getName());
	
	@Autowired
    private CategoryService categoryService;
	
	@Autowired
	private LanguageService languageService;
	
	private final static String DEFAULT_LANG = "zh";
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String view(ModelMap model) {
		
		model.addAttribute(Constants.ADMIN_MENU, Constants.ADMIN_MENU_IMAGE);
		return ViewName.ADMIN_IMAGE;
	}
	
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String viewCategory(ModelMap model) {

		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		String successPath = blobstoreService.createUploadUrl(Constants.ADMIN_CREATE_CATEGORY_PATH);
		
		List<Category> categories = categoryService.getAll();
		List<CategoryBean> beans = convertCategoryBeans(categories, DEFAULT_LANG);
		
		model.addAttribute(Constants.CATEGORIES, beans);
		model.addAttribute(Constants.ADMIN_MENU, Constants.ADMIN_MENU_CATEGORY);
		model.addAttribute(Constants.SUCCESS_PATH, successPath);
		
		return ViewName.ADMIN_CATEGORY;
	}
	
	@RequestMapping(value = "/admin/category/changeLanguage", method = RequestMethod.GET)
	public String changeCategoryLanguage(@RequestParam("lang") String lang, Model model) {
		List<Category> categories = categoryService.getAll();
		List<CategoryBean> beans = convertCategoryBeans(categories, lang);
		
		model.addAttribute(Constants.CATEGORIES, beans);
		model.addAttribute(Constants.ADMIN_MENU, Constants.ADMIN_MENU_CATEGORY);
		return ViewName.ADMIN_CATEGORY_DATATABLE;
	}
	
	@RequestMapping(value = "/admin/category/edit", method = RequestMethod.GET)
	public String editCategory(@RequestParam("lang") String lang, @RequestParam("id") Long id, Model model) {
		Category category = categoryService.get(id);
		CategoryBean bean = new CategoryBean();
		bean.copyFromEntity(category, lang, languageService);
		
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		String successPath = blobstoreService.createUploadUrl(Constants.ADMIN_CREATE_CATEGORY_PATH);
		
		model.addAttribute(Constants.CATEGORY_BEAN, bean);
		model.addAttribute(Constants.ADMIN_MENU, Constants.ADMIN_MENU_CATEGORY);
		model.addAttribute(Constants.SUCCESS_PATH, successPath);
		return ViewName.ADMIN_CATEGORY_EDIT_FORM;
	}
	
	@RequestMapping(value = "/admin/category/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteCategory(@RequestParam("id") long id) {
		categoryService.delete(id);
		return Constants.OK;
	}
	
	@RequestMapping(value = "/admin/category/save", method = RequestMethod.POST)
	public String saveCategory(HttpServletRequest request, Model model) {
		CategoryBean bean = new CategoryBean();
		bean.setName(ParamUtil.getString(request, "name"));
		bean.setDescription(ParamUtil.getString(request, "description"));
		bean.setId(ParamUtil.getLong(request, "id", null));
		bean.setActive(ParamUtil.getBoolean(request, "active"));
		bean.setAxe(ParamUtil.getInteger(request, "axe"));
		bean.setLang(ParamUtil.getString(request, "lang", DEFAULT_LANG));
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		Map<String, List<BlobKey>> images = blobstoreService.getUploads(request);
		BlobKey k = null;
		if (images != null && images.size() > 0) {
			for (Entry<String, List<BlobKey>> entries : images.entrySet()) {
				String key = entries.getKey();
				List<BlobKey> value = entries.getValue();
				for (BlobKey blobKey : value) {
					log.info("uploaded image blobkey for item[" + key + "] :" + blobKey);
					k = blobKey;
					break;
				}
				if (k != null) {
					break;
				}
			}
			
			ImagesService imgService = ImagesServiceFactory.getImagesService();
			String imgUrl = imgService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(k));
			bean.setImageUrl(imgUrl);
		}
		
		if (bean.getId() != null && bean.getId() > 0) {
			categoryService.update(bean.convertToEntity(k != null));
		} else {
			Key key = categoryService.save(bean.convertToEntity(k != null));
			bean.setId(key.getId());
		}
		languageService.saveLangForCategory(bean);
		
		return ViewName.REDIRECT_CATEGORY;
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(ModelMap model, Principal principal) {
		String username = principal.getName();
		log.warning(username + " tried to access to the admin page, but he does not have the authorization! Redirect to home page.");
		
		return ViewName.NO_AUTHORISATION;
	}
	
	private List<CategoryBean> convertCategoryBeans(List<Category> categories, String lang) {
		List<CategoryBean> beans = new ArrayList<>();
		if (categories != null) {
			for (Category category : categories) {
				CategoryBean bean = new CategoryBean();
				bean.copyFromEntity(category, lang, languageService);
				beans.add(bean);
			}
		}
		
		return beans;
	}
}
