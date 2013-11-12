package com.surpassun.book.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpassun.book.model.Img;
import com.surpassun.book.service.ImgService;
import com.surpassun.book.util.BookUtil;
import com.surpassun.book.util.Constants;
import com.surpassun.book.util.ViewName;

@Controller
public class HomeController {
	
	@Autowired
	ImgService imgService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String view(HttpServletRequest request, Model model) {
		List<Img> images = imgService.getImagesForFront();
		model.addAttribute(Constants.IMAGES, images);
		return ViewName.HOME;
	}
	
	@RequestMapping(value = "/toggleAdminControls", method = RequestMethod.POST)
	@ResponseBody
	public String toggleAdminControls(@RequestParam(defaultValue = "true") boolean showAdminControls, HttpServletRequest request) {
		if (BookUtil.hasAdminPermission(request)) {
			request.getSession().setAttribute(Constants.SHOW_ADMIN_CONTROLS, showAdminControls);
		}
		return Constants.OK;
	}
}
