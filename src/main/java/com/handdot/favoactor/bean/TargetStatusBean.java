package com.handdot.favoactor.bean;

public class TargetStatusBean {
	// 監視対象ツイートのID
	private long id;
	// お気に入数
	private int favoriteCount;
	// リツイート数
	private int retweetCount;

	public TargetStatusBean(long id, int favoriteCount, int retweetCount) {
		this.id = id;
		this.favoriteCount = favoriteCount;
		this.retweetCount = retweetCount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public int getRetweetCount() {
		return retweetCount;
	}

	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}

	public boolean equals(TargetStatusBean obj) {
		return (this.getId() == obj.getId());
	}
}
