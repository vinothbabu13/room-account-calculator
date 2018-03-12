package com.racproject.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Configuration
@ComponentScan("com.racproject") 
public class HomeController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "Home";
	}
	
	@RequestMapping(value="welcome", method=RequestMethod.GET)
	public String welcome(Model m) {
		m.addAttribute("name", "Vinoth");
		return "welcome";
	}
}
