package com.heyzqt.wechatmoments.activity.moments;


import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;

import com.heyzqt.wechatmoments.activity.moments.model.MomentsModel;
import com.heyzqt.wechatmoments.bean.MomentBean;
import com.heyzqt.wechatmoments.entity.User;

import java.io.IOException;
import java.util.List;

/**
 * Created by heyzqt on 2018/5/13.
 */

public class MomentsPresenter implements MomentsContract.Presenter, OnLoadMomentsListener,
		OnLoadUserInfoListener {

	@NonNull
	private final MomentsContract.View mMomentsView;

	private MomentsModel mMomentsModel;
	private List<MomentBean> mMomentBeans;

	private final static String USER_URL = "xxxxxxx";
	private final static String MOMENTS_URL = "xxxxxxx";

	public MomentsPresenter(@NonNull MomentsContract.View addView) {
		mMomentsView = checkNotNull(addView);
		mMomentsModel = new MomentsModelImpl();
	}

	@Override
	public void start() {
	}

	@Override
	public void loadUserInfo() {

	}

	@Override
	public void loadMoments() {
		try {
			mMomentsModel.loadUserInfo(USER_URL, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//mMomentsModel.loadMoments(MOMENTS_URL, 0, this);
		//mMomentsModel.loadMoments("111", 200, this);
	}

	@Override
	public void addLike() {

	}

	@Override
	public void addComment() {

	}

	@Override
	public void onSuccess(List<MomentBean> datas) {
		mMomentsView.showListView(datas);
	}

	@Override
	public void onSuccess(User user) {
		System.out.println("用户数据获取");
	}

	@Override
	public void onFailure(int error) {

	}
}
