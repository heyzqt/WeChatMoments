package com.heyzqt.wechatmoments.activity.moments.model;

import com.heyzqt.wechatmoments.activity.moments.OnLoadMomentsListener;

/**
 * Created by heyzqt on 2018/5/13.
 */

public interface MomentsModel {
	void loadMoments(String url, int type, OnLoadMomentsListener listener);
}
