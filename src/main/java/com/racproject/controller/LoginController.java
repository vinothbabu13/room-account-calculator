package com.racproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.racproject.model.Login;
import com.racproject.model.User;
import com.racproject.service.UserService;
import com.racproject.dao.UserDaoImpl;

@Controller
@RequestMapping(value = "/login")
@SessionAttributes("user")
public class LoginController {

	/*
	 * Add user in model attribute
	 */
	@ModelAttribute("user")
	public User setUpUserForm() {
		return new User();
	}

	@Autowired
	UserService userService;

	@Autowired
	UserDaoImpl userDaoImpl;

	@RequestMapping(value = "check", method = RequestMethod.POST)
	public ModelAndView loginProcess(@ModelAttribute("user") User user, HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("login") Login login) throws Exception {

		ModelAndView mav = null;

		if(!user.getUsername().isEmpty()) {
			mav = new ModelAndView("welcome");
			mav.addObject("username", user.getUsername());
			return mav;
		}

		User userData = userDaoImpl.validateUser(login);
		if (null != userData) {
			user.setId(userData.getId());
			user.setName(userData.getName());
			user.setUsername(userData.getUsername());
			mav = new ModelAndView("welcome");
			mav.addObject("username", userData.getUsername());
		} else {
			mav = new ModelAndView("welcome");
			mav.addObject("message", "Username or Password is wrong!");
		}
		return mav;

	}
	

}