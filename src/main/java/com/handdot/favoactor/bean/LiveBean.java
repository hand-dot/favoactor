package com.handdot.favoactor.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

/**
 *
 * @author hand-dot
 *
 */
public class LiveBean {

	// ライブの時間
	private final int LIVE_LIMIT_MINUTES = 3;

	// ライブの開始時間
	private Date startDate;

	// ライブの終了時間
	private Date endDate;

	// 監視対象ツイートの情報リスト
	private Map<Long, TargetStatusBean> targetStatusBeanMap = new HashMap<>();

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

	public Map<Long, TargetStatusBean> getTargetStatusBeanMap() {
		return targetStatusBeanMap;
	}

	public void setTargetStatusBeanMap(Map<Long, TargetStatusBean> targetStatusBeanMap) {
		this.targetStatusBeanMap = targetStatusBeanMap;
	}

	/**
	 * お気に入り数の増加分を返します
	 *
	 * @param targetStatusBean
	 * @return
	 */
	public int getIncreaseFavoriteCount(TargetStatusBean targetStatusBean) {
		if (this.targetStatusBeanMap.containsKey(targetStatusBean.getId())) {// このツイートはマップに持っている場合
			// 前回とのお気に入りの差分
			int result = targetStatusBean.getFavoriteCount()
					- this.targetStatusBeanMap.get(targetStatusBean.getId()).getFavoriteCount();

			// 前回との差分がマイナスだった場合、すでに持っているツイートを更新せず、0を返却する
			if (result < 0) {
				return 0;
			}

			// すでに持っているツイートを更新
			this.targetStatusBeanMap.put(targetStatusBean.getId(), targetStatusBean);

			// 前回とのお気に入りの差分を返却
			return result;

		} else {// このツイートをマップに持っていない場合
			// マップに追加
			this.targetStatusBeanMap.put(targetStatusBean.getId(), targetStatusBean);

			// お気に入り数を返却
			return targetStatusBean.getFavoriteCount();
		}
	}

	public void clearTargetStatusBeanMap() {
		this.targetStatusBeanMap.clear();
	}
}
