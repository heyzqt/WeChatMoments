package com.heyzqt.wechatmoments.activity.moments;

import android.os.Handler;
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
	public void initUserInfo(User user) {
		mListView.updateHeaderView(user);
	}

	@Override
	public void initListView(List<MomentBean> datas) {
		mMomentsAdapter = new MomentsAdapter(datas);
		mListView.setAdapter(mMomentsAdapter);
		mListView.setOnRefreshListener(mPresenter);
		mListView.setItemCounts(mPresenter.getMomentsCount());
	}

	@Override
	public void showLikes() {

	}

	@Override
	public void showComments() {

	}

	@Override
	public void refreshPullDownData(List<MomentBean> datas) {

	}

	@Override
	public void loadPullUpData(final List<MomentBean> datas) {

		final List<MomentBean> temp = datas;

		mListView.showFooterView(true);
		//使分页加载的效果更明显
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mMomentsAdapter.setData(temp);
				if (temp.size() < mPresenter.getMomentsCount()) {
					mListView.finishLoad(false);
				} else if (temp.size() == mPresenter.getMomentsCount()) {
					mListView.finishLoad(true);
				}
				mListView.showFooterView(false);
			}
		}, 4000);
	}

	@Override
	public void showLoadFailed() {
		mNoInternetLayout.setVisibility(View.VISIBLE);
	}
}
