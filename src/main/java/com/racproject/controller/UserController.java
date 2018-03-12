package com.racproject.controller;

import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.racproject.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("user/list");
		model.addObject("listUser", userService.listUser());
		return model;
	}
}
