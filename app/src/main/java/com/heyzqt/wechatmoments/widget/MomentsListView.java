package com.heyzqt.wechatmoments.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.heyzqt.wechatmoments.R;
import com.heyzqt.wechatmoments.entity.User;
import com.heyzqt.wechatmoments.util.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heyzqt on 2018/5/13.
 */

public class MomentsListView extends ListView implements AbsListView.OnScrollListener {

	private View headerView;
	private int headerViewHeight;
	private ViewHolder mHolder;

	private static final String TAG = "MomentsListView";

	public MomentsListView(Context context) {
		this(context, null);
	}

	public MomentsListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MomentsListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initHeaderView(context);
		setHeaderDividersEnabled(false);
	}

	void initHeaderView(Context context) {
		headerView = LayoutInflater.from(context).inflate(R.layout.listview_header, null);
		mHolder = new ViewHolder(headerView);
		addHeaderView(headerView);
		setOnScrollListener(this);
	}

	public void updateHeaderView(User user) {
		mHolder.username.setText(user.getNick());

		GlideApp.with(mHolder.view.getContext())
				.load(user.getAvatarurl())
				.placeholder(R.mipmap.loading)
				.into(mHolder.useravatar);

		GlideApp.with(mHolder.view.getContext())
				.load(user.getProfileurl())
				.placeholder(R.mipmap.img_test)
				.into(mHolder.profile);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
			int totalItemCount) {

	}

	class ViewHolder {

		View view;

		@BindView(R.id.refresh_circle)
		ImageView circle;

		@BindView(R.id.profile_img)
		ImageView profile;


		@BindView(R.id.user_name)
		TextView username;

		@BindView(R.id.user_avatar)
		ImageView useravatar;

		public ViewHolder(View view) {
			this.view = view;
			ButterKnife.bind(this, view);
		}
	}
}
