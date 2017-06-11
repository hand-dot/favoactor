package com.handdot.favoactor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.handdot.favoactor.service.TwitterService;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	TwitterService twitterService;

	private Twitter twitter;

	private RequestToken requestToken;

	private AccessToken accessToken;

	/**
	 * ルートにマッピングされたメソッド
	 * ツイッター認証が行われていない場合、認証をさせる
	 * 認証がされている場合はトークンを保存し、処理をする
	 * @param oauth_token
	 * @param oauth_verifier
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	String login(@RequestParam(value = "oauth_token", required = false) String oauth_token,
			@RequestParam(value = "oauth_verifier", required = false) String oauth_verifier, Model model) {
		try {
			if (oauth_token == null || oauth_verifier == null || twitter == null || requestToken == null) {
				// 認証がまだ済んでいない場合
				twitter = twitterService.createNewTwitter();
				requestToken = twitter.getOAuthRequestToken();
				model.addAttribute("authorizationURL", requestToken.getAuthorizationURL());
				return "login";
			} else {
				// すでに認証がされている場合
				if(accessToken == null){
					accessToken = twitter.getOAuthAccessToken(requestToken, oauth_verifier);
				}
				//ここからOAuthつかった処理をスタート


				model.addAttribute("oauth_verifier", oauth_verifier);
				model.addAttribute("oauth_token", oauth_token);
			}
		} catch (TwitterException e) {
			// 認証で失敗した場合、ツイッターインスタンスをもう一度作り直し、
			// 再度ログイン処理を行わせる
			twitter = null;
			requestToken = null;
		}
		return "top";
	}
}
