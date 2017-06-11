package com.handdot.favoactor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class TwitterService {
	@Value("${oauth.consumerKey}")
	private String consumerKey;

	@Value("${oauth.consumerSecret}")
	private String consumerSecret;

	/**
	 * 新しいインスタンスを作成します。
	 * @return ツイッターインスタンス
	 */
	public Twitter createNewTwitter() {
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey(consumerKey);
		builder.setOAuthConsumerSecret(consumerSecret);
		Configuration configuration = builder.build();
		return new TwitterFactory(configuration).getInstance();
	}
}
