package com.hrmsdashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping({ "/GemsDashboard", "/GemsDashboard/", "/GemsDashboard/{path:[^\\.]*}", "/GemsDashboard/**" })
	public String redirect() {
		return "forward:/index.html";
	}
}
