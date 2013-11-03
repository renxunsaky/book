package com.surpassun.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.surpassun.book.util.ViewName;

@Controller
@RequestMapping("/error/**")
public class ErrorController {

	@RequestMapping("/error/index")
	public String index() {
		return ViewName.ERROR_PAGE;
	}
}
