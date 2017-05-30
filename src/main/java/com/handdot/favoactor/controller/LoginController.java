package com.handdot.favoactor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//TODO https://github.com/yusuke/sign-in-with-twitter
//を参考にアプリケーション認証の遷移を完成させる

@Controller
@RequestMapping("login")
public class LoginController {
	@RequestMapping(method=RequestMethod.GET)
	String login(Model model){
		return "login";
	}
}