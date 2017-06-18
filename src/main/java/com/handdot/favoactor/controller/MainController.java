package com.handdot.favoactor.controller;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.handdot.favoactor.bean.LiveBean;
import com.handdot.favoactor.bean.UserBean;
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

	private UserBean user = new UserBean();

	/**
	 * ルートにマッピングされたメソッド ツイッター認証が行われていない場合、認証をさせる 認証がされている場合はトークンを保存し、処理をする
	 * 
	 * @param oauth_token
	 * @param oauth_verifier
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	String login(@RequestParam(value = "oauth_token", required = false) String oauth_token,
			@RequestParam(value = "oauth_verifier", required = false) String oauth_verifier, Model model) {
		try {
			if (oauth_token == null || oauth_verifier == null) {// 認証がまだ済んでいない場合

				user.setTwitter(twitterService.createNewTwitter());
				user.setRequestToken(user.getTwitter().getOAuthRequestToken());
				model.addAttribute("authorizationURL", user.getRequestToken().getAuthorizationURL());
				return "login";
			} else {// すでに認証がされている場合
				if (!user.isCertificated()) {// 初回のみ実行される
					//ログイン画面から遷移しないとここでヌルポが起きる
					user.setAccessToken(user.getTwitter().getOAuthAccessToken(user.getRequestToken(), oauth_verifier));
					user.setCertificated(true);

					// ライブの時間の設定
					LiveBean liveBean = new LiveBean();
					liveBean.setStartDate(new Date());
					user.setLiveBean(liveBean);
					
					// ここからOAuthつかった処理をスタート
					twitterService.watchTweet(user);
				}
				
				model.addAttribute("start_date", user.getLiveBean().getStartDate());
				model.addAttribute("end_date", user.getLiveBean().getEndDate());
				model.addAttribute("oauth_verifier", oauth_verifier);
				model.addAttribute("oauth_token", oauth_token);
			}
		} catch (TwitterException e) {
			// 認証で失敗した場合
			user.setCertificated(false);
			login(null, null, model);
			return "login";
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "top";
	}
}
