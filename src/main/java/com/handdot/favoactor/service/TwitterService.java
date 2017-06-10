package com.handdot.favoactor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * TwitterのAPIサービス
 *
 * @author hand-dot
 *
 */
@Service
public class TwitterService {

	@Value("${oauth.consumerKey}")
	private String consumerKey;

	@Value("${oauth.consumerSecret}")
	private String consumerSecret;


	private Twitter twitter;

	/**
	 * 認証をするためのURLを生成します。
	 *
	 * @return 認証用のURL
	 * @throws TwitterException
	 */
	public String getAuthorizationURL() throws TwitterException {
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey(consumerKey);
		builder.setOAuthConsumerSecret(consumerSecret);
		Configuration configuration = builder.build();
		TwitterFactory factory = new TwitterFactory(configuration);

		twitter = factory.getInstance();
		RequestToken requestToken = twitter.getOAuthRequestToken();

		return requestToken.getAuthorizationURL();
	}

	/**
	 * 引数に渡したユーザーのタイムラインを取得します。
	 *
	 * @param user
	 * @return
	 * @throws TwitterException
	 */
	public List<Status> getUserTimeline(User user) throws TwitterException {
		return twitter.getUserTimeline(user.getId());
	}

	/**
	 * スクリーンネームからユーザーを取得します。
	 *
	 * @param screenName
	 *            スクリーンネーム
	 * @return ツイッターユーザー
	 * @throws TwitterException
	 */
	public User getUserFromScreenName(String screenName) throws TwitterException {
		return twitter.showUser(screenName);
	}
}
