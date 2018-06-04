package com.beerapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value= "/")
public class WebController {
	
	public String homePage(ModelMap model) {
		return "index";
	}
	
	
}
