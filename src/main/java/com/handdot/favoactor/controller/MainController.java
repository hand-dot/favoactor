package com.handdot.favoactor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.handdot.favoactor.service.TwitterService;

import twitter4j.TwitterException;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	TwitterService twitterService;

	@RequestMapping(params="oauth_token", method=RequestMethod.GET)
	String login(@RequestParam String oauth_token, Model model){
		model.addAttribute("oauth_token", oauth_token);
		return "top";
	}

	@RequestMapping(value="login",method=RequestMethod.GET)
	String login(Model model) throws TwitterException{
		model.addAttribute("authorizationURL", twitterService.getAuthorizationURL());
		return "login";
	}
}
