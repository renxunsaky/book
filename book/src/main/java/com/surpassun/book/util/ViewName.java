package com.surpassun.book.util;

public interface ViewName {
	
	String REDIRECT = "redirect:";
    
    String ERROR_PAGE = "/error/index";
    
    String REDIRECT_HOME = REDIRECT + "/home.html";
    
    String HOME = "home";

	String ADMIN_IMAGE = "admin/image/view";
	String ADMIN_CATEGORY = "admin/category/view";
	String ADMIN_CATEGORY_DATATABLE = "admin/category/dataTable";
	String ADMIN_CATEGORY_EDIT_FORM = "admin/category/editForm";
	String REDIRECT_CATEGORY = REDIRECT + "/admin/category.html";
	
	String NO_AUTHORISATION = "/error/403";

}
