package com.surpassun.book.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.surpassun.book.bean.CategoryBean;
import com.surpassun.book.model.Category;
import com.surpassun.book.service.CategoryService;
import com.surpassun.book.service.LanguageService;
import com.surpassun.book.util.BookUtil;
import com.surpassun.book.util.Constants;

public class NavigationInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
    private CategoryService categoryService;

    @Autowired
    private LanguageService languageService;

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if (modelAndView != null) {
			Boolean showAdminControls = (Boolean)request.getSession().getAttribute(Constants.SHOW_ADMIN_CONTROLS);
			modelAndView.addObject(Constants.SHOW_ADMIN_CONTROLS, showAdminControls);
			
			//verify the admin permission
			modelAndView.addObject(Constants.HAS_ADMIN_PERMISSION, BookUtil.hasAdminPermission(request));
			
			List<CategoryBean> navItems = new ArrayList<CategoryBean>();
			
			//load all of the categories
			List<Category> categories = categoryService.getActiveCategories();
			if (categories != null) {
				String lang = BookUtil.getLang(request);
				for (Category category : categories) {
					navItems.add(BookUtil.convertCategoryToBean(category, lang, languageService));
				}
			}
			
			//load images to display in the home page
			
			modelAndView.addObject(Constants.NAV_ITEMS, navItems);
		}
		
		super.postHandle(request, response, handler, modelAndView);
	}
}
