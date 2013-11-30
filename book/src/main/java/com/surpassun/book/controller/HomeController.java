package com.surpassun.book.controller;

import java.util.ArrayList;
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
		
		List<Img> list1 = new ArrayList<>();
		List<Img> list2 = new ArrayList<>();
		List<Img> list3 = new ArrayList<>();
		List<Img> list4 = new ArrayList<>();
		List<Img> list5 = new ArrayList<>();
		
		if (images != null) {
			int size = images.size();
			for (int i = 0; i < size; i++) {
				if (i < size) {
					list1.add(images.get(i++));
				}
				if (i < size) {
					list2.add(images.get(i++));
				}
				if (i < size) {
					list3.add(images.get(i++));
				}
				if (i < size) {
					list4.add(images.get(i++));
				}
				if (i < size) {
					list5.add(images.get(i++));
				}
			}
		}
		
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		model.addAttribute("list4", list4);
		model.addAttribute("list5", list5);
		return ViewName.HOME;
	}
	
	@RequestMapping(value = "/toggleAdminControls", method = RequestMethod.POST)
	@ResponseBody
	public String toggleAdminControls(@RequestParam(defaultValue = "true") boolean showAdminControls, HttpServletRequest request) {
		if (BookUtil.hasAdminPermission(request)) {
			request.getSession().setAttribute(Constants.SHOW_ADMIN_CONTROLS, showAdminControls);
			return Constants.OK;
		} else {
			return Constants.KO;
		}
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(HttpServletRequest request) {
		return ViewName.CONTACT_ME;
	}
}
