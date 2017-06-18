package com.handdot.favoactor.service;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.handdot.favoactor.bean.UserBean;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * ツイッターの処理を行うサービスです
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

	/**
	 * 新しいインスタンスを作成します。
	 * 
	 * @return ツイッターインスタンス
	 */
	public Twitter createNewTwitter() {
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey(consumerKey);
		builder.setOAuthConsumerSecret(consumerSecret);
		Configuration configuration = builder.build();
		return new TwitterFactory(configuration).getInstance();
	}

	/**
	 * ツイートを制限時間内監視します。
	 * 
	 * @param user
	 * @throws InterruptedException
	 */
	public void watchTweet(UserBean user) throws IllegalStateException, TwitterException {
		// ツイターの認証が済んでいない場合例外を投げる
		if (!user.isCertificated()) {
			throw new IllegalStateException();
		}
		// ライブ中のフラグを立てる
		user.setLiving(true);

		// ライブの開始時間と終了時間
		Date endDate = user.getLiveBean().getEndDate();
		Date startDate = user.getLiveBean().getStartDate();

		// 定期実行サービス
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

		// 定期実行処理
		Runnable command = new Runnable() {
			public void run() {
				Date now = new Date();
				if (now.after(endDate)) {// 今の時間がライブの終了時間よりも後だった場合、処理を止める
					service.shutdown();
				}
				try {
					for (Status status : user.getTwitter().getUserTimeline()) {
						System.out.println(status);
					}
				} catch (TwitterException e) {
					e.printStackTrace();
					// エラーがあった場合処理を止める
					service.shutdown();
				}
			}
		};

		// 処理実行
		service.scheduleAtFixedRate(command, 0, 1, TimeUnit.SECONDS);
	}
}
