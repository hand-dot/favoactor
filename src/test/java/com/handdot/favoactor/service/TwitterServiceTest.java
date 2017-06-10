package com.handdot.favoactor.service;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;

@SpringBootTest
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
public class TwitterServiceTest {
	@Autowired
	TwitterService twitterService;

	/**
	 * スクリーンネームからUserIdを取得するためのテスト
	 * @throws TwitterException
	 */
	@Ignore
	@Test
	public void getUserIdFromScreenNameANDgetUserTimeline() throws TwitterException {
		String screenName = "";
		User user = twitterService.getUserFromScreenName(screenName);
		List<Status>  statuses = twitterService.getUserTimeline(user);
	    for (Status status : statuses) {
	        System.out.println(status.getUser().getName() + ":" +
	                           status.getText());
	    }
	}

	@Ignore
	@Test
	public void getAuthorizationURL() throws TwitterException {
		twitterService.getAuthorizationURL();
	}

}
