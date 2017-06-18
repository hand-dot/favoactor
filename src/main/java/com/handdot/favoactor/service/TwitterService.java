package com.handdot.favoactor.service;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.handdot.favoactor.bean.TargetStatusBean;
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
					System.out.println("ツイートの監視を終了します");
					service.shutdown();
				}
				try {
					// 自分のツイートを最新20件取得して1件づつ処理をする
					for (Status status : user.getTwitter().getUserTimeline()) {

						if (status.getCreatedAt().after(startDate)) {// ライブの開始時間よりもあとのツイート
							TargetStatusBean target= new TargetStatusBean(status.getId(),status.getFavoriteCount(),status.getRetweetCount());
							int increaseFavoriteCount = user.getLiveBean().getIncreaseFavoriteCount(target);
							if(0<increaseFavoriteCount){
								System.out.println(target.getId()+"/"+status.getText()+"は"+increaseFavoriteCount+"件新規にお気に入り登録されました。");
							}else{
								System.out.println("お気に入り件数に増加はありませんでした。");
							}
						}
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
