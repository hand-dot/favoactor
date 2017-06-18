package com.handdot.favoactor.bean;

import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class UserBean {
	// ツイッターインスタンス
	private Twitter twitter;
	// リクエストトークン
	private RequestToken requestToken;
	// アクセストークン
	private AccessToken accessToken;
	// 認証されているか
	private boolean isCertificated = false;
	// ライブ中の場合true
	private boolean isLiving = false;
	// ライブの情報
	private LiveBean liveBean;
	
	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

	public RequestToken getRequestToken() {
		return requestToken;
	}

	public void setRequestToken(RequestToken requestToken) {
		this.requestToken = requestToken;
	}

	public AccessToken getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}

	public boolean isCertificated() {
		return isCertificated;
	}

	public void setCertificated(boolean isCertification) {
		this.isCertificated = isCertification;
	}

	public LiveBean getLiveBean() {
		return liveBean;
	}

	public void setLiveBean(LiveBean liveBean) {
		this.liveBean = liveBean;
	}

	public boolean isLiving() {
		return isLiving;
	}

	public void setLiving(boolean isLiving) {
		this.isLiving = isLiving;
	}
}
