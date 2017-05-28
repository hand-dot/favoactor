package com.handdot.favoactor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

/**
 * TwitterのAPIサービス
 *
 * @author hand-dot
 *
 */
@Service
public class TwitterService {

	private Twitter twitter = TwitterFactory.getSingleton();

	public List<Status> getUserTimeline(User user) throws TwitterException {
		return twitter.getUserTimeline(user.getId());
	}


	public User getUserFromScreenName(String screenName) throws TwitterException{
		return  twitter.showUser(screenName);
	}
}
