package com.heyzqt.wechatmoments;

import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;

/**
 * Created by heyzqt on 2018/5/13.
 */

public class WeChatApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		ViewTarget.setTagId(R.id.image);
	}
}
