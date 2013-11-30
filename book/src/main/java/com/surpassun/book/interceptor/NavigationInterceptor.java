package com.surpassun.book.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

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
    
    public static final String LOCALE_SESSION_ATTRIBUTE_NAME = SessionLocaleResolver.class.getName() + ".LOCALE";

    @Override
    public boolean preHandle(HttpServletRequest request,
    		HttpServletResponse response, Object handler) throws Exception {
    	String localeString = request.getParameter("locale");
    	Locale locale = Locale.getDefault();
		if (localeString != null) {
			locale = StringUtils.parseLocaleString(localeString);
		} else {
			Locale sessionLocale = (Locale)WebUtils.getSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME);
			if (sessionLocale != null) {
				locale = sessionLocale;
			}
		}
		
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		if (localeResolver != null) {
			localeResolver.setLocale(request, response, locale);
		} else {
			WebUtils.setSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME, locale);
		}
		
    	return super.preHandle(request, response, handler);
    }
    
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
