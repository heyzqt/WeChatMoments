package com.heyzqt.wechatmoments.activity.moments;

import com.heyzqt.wechatmoments.entity.User;

/**
 * Created by heyzqt on 2018/5/13.
 */

public interface OnLoadUserInfoListener {

	void onSuccess(User user);

	void onFailure(int error);
}
