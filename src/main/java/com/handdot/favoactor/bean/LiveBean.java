package com.handdot.favoactor.bean;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

/**
 * 
 * @author hand-dot
 *
 */
public class LiveBean {

	// ライブの時間
	private final int LIVE_LIMIT_MINUTES = 1;

	// ライブの開始時間
	private Date startDate;

	// ライブの終了時間
	private Date endDate;
	
	
	// 監視対象ツイートのIDリスト？ID,お気に入数,リツイート数のDTOリストでもいいかもしれない

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		this.endDate = DateUtils.addMinutes(startDate, LIVE_LIMIT_MINUTES);
	}

	public Date getEndDate() {
		return endDate;
	}
}
