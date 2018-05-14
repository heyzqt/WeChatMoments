package com.heyzqt.wechatmoments.activity.moments;


import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;

import com.heyzqt.wechatmoments.activity.moments.model.MomentsModel;
import com.heyzqt.wechatmoments.bean.MomentBean;
import com.heyzqt.wechatmoments.entity.User;
import com.heyzqt.wechatmoments.widget.MomentsListView;

import java.io.IOException;
import java.util.List;

/**
 * Created by heyzqt on 2018/5/13.
 */

public class MomentsPresenter implements MomentsContract.Presenter, OnLoadMomentsListener,
		OnLoadUserInfoListener, MomentsListView.OnRefreshListener {

	@NonNull
	private final MomentsContract.View mMomentsView;

	private MomentsModel mMomentsModel;
	private List<MomentBean> mMomentBeans;

	//Limit listview the count of update items
	private int cachedItemCount = 5;
	private int momentsCount;
	private final int LOADED_ITEMS_COUNT = 5;

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
		try {
			mMomentsModel.loadUserInfo(USER_URL, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadMoments() {
		try {
			mMomentsModel.loadMoments(MOMENTS_URL, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addLike() {

	}

	@Override
	public void addComment() {

	}

	@Override
	public void onSuccess(List<MomentBean> datas) {
		mMomentBeans = datas;
		momentsCount = datas.size();
		mMomentsView.initListView(mMomentBeans.subList(0, cachedItemCount));
	}

	@Override
	public void onSuccess(User user) {
		mMomentsView.initUserInfo(user);
	}

	@Override
	public void onFailure(int error) {
		mMomentsView.showLoadFailed();
	}

	@Override
	public void pullDownRefresh() {
		if (cachedItemCount > mMomentBeans.size()) {
			cachedItemCount = mMomentBeans.size();
		}
		mMomentsView.refreshPullDownData(mMomentBeans.subList(0, cachedItemCount));
	}

	@Override
	public void pullUpLoad() {
		cachedItemCount += LOADED_ITEMS_COUNT;
		if (cachedItemCount > momentsCount) {
			cachedItemCount = momentsCount;
		}

		mMomentsView.loadPullUpData(mMomentBeans.subList(0, cachedItemCount));
	}

	public int getMomentsCount() {
		return momentsCount;
	}

	public int getCachedItemCount() {
		return cachedItemCount;
	}
}
