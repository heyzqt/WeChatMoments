package com.heyzqt.wechatmoments.activity.moments.model;

import com.heyzqt.wechatmoments.activity.moments.OnLoadMomentsListener;
import com.heyzqt.wechatmoments.activity.moments.OnLoadUserInfoListener;

import java.io.IOException;

/**
 * Created by heyzqt on 2018/5/13.
 */

public interface MomentsModel {

	void loadUserInfo(String url, OnLoadUserInfoListener listener) throws IOException;

	void loadMoments(String url, OnLoadMomentsListener listener) throws IOException;
}
