package com.heyzqt.wechatmoments.activity.moments;


import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;

import com.heyzqt.wechatmoments.activity.moments.model.MomentsModel;
import com.heyzqt.wechatmoments.bean.MomentBean;

import java.util.List;

/**
 * Created by heyzqt on 2018/5/13.
 */

public class MomentsPresenter implements MomentsContract.Presenter, OnLoadMomentsListener {

	@NonNull
	private final MomentsContract.View mMomentsView;

	private MomentsModelImpl mMomentsModel;

	private MomentsModel mModel;

	private List<MomentBean> mMomentBeans;

	public MomentsPresenter(@NonNull MomentsContract.View addView) {
		mMomentsView = checkNotNull(addView);
		mMomentsModel = new MomentsModelImpl();
	}

	@Override
	public void start() {
	}

	@Override
	public void loadMoments() {
		mMomentsModel.loadMoments("111", 200, this);
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
	public void onFailure(int error) {

	}
}
