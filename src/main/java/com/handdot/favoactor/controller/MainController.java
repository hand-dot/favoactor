package com.handdot.favoactor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainController {
	@RequestMapping(method=RequestMethod.GET)
	String login(Model model){
		model.addAttribute("name", "hei");
		return "top";
	}
}
