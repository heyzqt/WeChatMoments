package com.heyzqt.wechatmoments.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.heyzqt.wechatmoments.R;

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

		mHolder.username.setText("heyzqt");

		mHolder.useravatar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), "useravatar", Toast.LENGTH_SHORT).show();
			}
		});

		headerView.getViewTreeObserver().addOnPreDrawListener(
				new ViewTreeObserver.OnPreDrawListener() {
					@Override
					public boolean onPreDraw() {
						Log.i(TAG, "onPreDraw: ");
//						if (headerView.getMeasuredWidth() > 0) {
//							headerViewHeight = headerView.getMeasuredHeight();
//							headerView.getViewTreeObserver().removeOnPreDrawListener(this);
//						}
						return true;
					}
				});
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
			int totalItemCount) {

	}

	class ViewHolder {

		@BindView(R.id.refresh_circle)
		ImageView circle;

		@BindView(R.id.user_name)
		TextView username;

		@BindView(R.id.user_avatar)
		ImageView useravatar;

		public ViewHolder(View view) {
			ButterKnife.bind(this, view);
		}
	}
}
