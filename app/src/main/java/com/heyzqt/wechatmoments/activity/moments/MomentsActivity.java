package com.heyzqt.wechatmoments.activity.moments;

import android.view.View;
import android.widget.RelativeLayout;

import com.heyzqt.wechatmoments.R;
import com.heyzqt.wechatmoments.adapter.MomentsAdapter;
import com.heyzqt.wechatmoments.base.BaseActivity;
import com.heyzqt.wechatmoments.bean.MomentBean;
import com.heyzqt.wechatmoments.entity.User;
import com.heyzqt.wechatmoments.widget.MomentsListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MomentsActivity extends BaseActivity<MomentsPresenter> implements
		MomentsContract.View {

	@BindView(R.id.moments_listview)
	MomentsListView mListView;
	@BindView(R.id.no_internet_layout)
	RelativeLayout mNoInternetLayout;

	MomentsAdapter mMomentsAdapter;

	private MomentsPresenter mPresenter;

	@Override
	public int getLayout() {
		return R.layout.activity_main;
	}

	@Override
	public void initView() {
		ButterKnife.bind(this);
	}

	@Override
	public void initPresenter() {
		mPresenter = new MomentsPresenter(this);
		mPresenter.loadUserInfo();
		mPresenter.loadMoments();
	}


	@Override
	public void showUserInfo(User user) {
		mListView.updateHeaderView(user);
	}

	@Override
	public void showListView(List<MomentBean> datas) {
		mMomentsAdapter = new MomentsAdapter(datas);
		mListView.setAdapter(mMomentsAdapter);
	}

	@Override
	public void showLikes() {

	}

	@Override
	public void showComments() {

	}

	@Override
	public void showPullDownRefresh() {

	}

	@Override
	public void showPullUpRefresh() {

	}

	@Override
	public void showLoadFailed() {
		mNoInternetLayout.setVisibility(View.VISIBLE);
	}
}
