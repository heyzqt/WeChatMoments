package com.heyzqt.wechatmoments.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
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
	private ImageView refreshView;
	private int headerViewHeight;
	private int firstVisibleItem;
	private int paddingTop;

	private View footerView;
	private int footerViewHeight;
	private int visibleLastItem;
	private boolean isEnd;

	private int downY;
	private int moveY;

	private boolean isWholeListShowedAndEnd;
	private int itemCounts;

	private final int PULL_DOWN_REFRESH = 0;
	private final int REFRESHING = 1;
	private final int RELEASE_REFRESH = 2;
	private int refreshState = PULL_DOWN_REFRESH;
	private LoadMoreStatus mLoadMoreStatus = LoadMoreStatus.START_LOAD;

	private RotateAnimation refreshAnimation;
	private RotateAnimation loadAnimation;

	private OnRefreshListener mRefreshListener;

	private final int HEADER_VIEW_SCROLL = 24;

	public static enum LoadMoreStatus {
		/**
		 * Load more
		 */
		START_LOAD,
		/**
		 * Loading
		 */
		LOADING,
		/**
		 * NO more content need to load.
		 */
		LOADED_ALL
	}

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
		initFooterView(context);
		setFooterDividersEnabled(false);
		initAnimation();
		setOnScrollListener(this);
	}

	void initHeaderView(Context context) {
		headerView = LayoutInflater.from(context).inflate(R.layout.listview_header, null);
		mHolder = new ViewHolder(headerView);
		headerView.measure(0, 0);
		headerViewHeight = headerView.getMeasuredHeight();
		addHeaderView(headerView);
		setOnScrollListener(this);
	}

	void initFooterView(Context context) {
		footerView = LayoutInflater.from(context).inflate(R.layout.listview_footer, null);
		footerView.measure(0, 0);
		footerViewHeight = footerView.getMeasuredHeight();
		footerView.setVisibility(View.GONE);
		addFooterView(footerView);
	}

	void initAnimation() {
		refreshAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF
				, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		refreshAnimation.setDuration(1000);
		refreshAnimation.setRepeatMode(Animation.INFINITE);
		refreshAnimation.setInterpolator(new LinearInterpolator());
		refreshAnimation.setFillAfter(true);

		loadAnimation = new RotateAnimation(360, 0, Animation.RELATIVE_TO_SELF
				, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		loadAnimation.setDuration(1000);
		loadAnimation.setRepeatMode(Animation.INFINITE);
		loadAnimation.setInterpolator(new LinearInterpolator());
		loadAnimation.setFillAfter(true);
	}

	private void changeHeaderViewState() {
		switch (refreshState) {
			case PULL_DOWN_REFRESH:
				refreshView.startAnimation(refreshAnimation);
				break;
			case REFRESHING:
				refreshView.clearAnimation();
				refreshView.startAnimation(refreshAnimation);
				break;
			case RELEASE_REFRESH:
				refreshView.startAnimation(loadAnimation);
				break;
		}
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
		if (scrollState == SCROLL_STATE_IDLE) {
			//Scroll to bottom && load status is START_LOAD && refreshState is not REFRESHING.
			if (isEnd && mLoadMoreStatus == LoadMoreStatus.START_LOAD
					&& refreshState != REFRESHING) {
				mLoadMoreStatus = LoadMoreStatus.LOADING;
				footerView.setVisibility(View.VISIBLE);
				if (mRefreshListener != null) {
					mRefreshListener.pullUpLoad();
				}
			}

			if (isWholeListShowedAndEnd && mLoadMoreStatus == LoadMoreStatus.LOADED_ALL) {
				showFooterView(false);
			}
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
			int totalItemCount) {
		this.firstVisibleItem = firstVisibleItem;
		this.visibleLastItem = firstVisibleItem + visibleItemCount - 1;

		//Check if Listview scroll to bottom.
		if (visibleLastItem == (totalItemCount - 1)) {
			isEnd = true;
		} else {
			isEnd = false;
		}

		//因为有header和footer所以要加2
		if (visibleLastItem == (itemCounts + 2 - 1)) {
			isWholeListShowedAndEnd = true;
		} else {
			isWholeListShowedAndEnd = false;
		}

		System.out.println("zhangqing itemCount = " + itemCounts);
		System.out.println("zhangqing 111111 isend = " + isEnd);
		System.out.println("zhangqing 333333 firstVisibleItem = " + firstVisibleItem + ","
				+ "visibleItemCount = "
				+ visibleItemCount + ",totalItemCount = " + totalItemCount
				+ ",visibleLastItem = " + visibleLastItem);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		return super.onTouchEvent(ev);
	}

	public void showFooterView(boolean isShow) {
		if (isShow) {
			footerView.setVisibility(View.VISIBLE);
		} else {
			footerView.setVisibility(View.INVISIBLE);
		}

		if (isWholeListShowedAndEnd) {
			footerView.setPadding(0, -footerViewHeight + 2, 0, 0);
		}
	}

	public void finishLoad(boolean loadAll) {
		if (loadAll) {
			mLoadMoreStatus = LoadMoreStatus.LOADED_ALL;
		} else {
			mLoadMoreStatus = LoadMoreStatus.START_LOAD;
		}
	}

	public void setItemCounts(int itemCounts) {
		this.itemCounts = itemCounts;
	}

	public interface OnRefreshListener {
		void pullDownRefresh();

		void pullUpLoad();
	}

	public void setOnRefreshListener(OnRefreshListener listener) {
		mRefreshListener = listener;
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
