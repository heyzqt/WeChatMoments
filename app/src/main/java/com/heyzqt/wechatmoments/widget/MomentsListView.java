package com.heyzqt.wechatmoments.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by heyzqt on 2018/5/13.
 */

public class MomentsListView extends ListView {

	public MomentsListView(Context context) {
		this(context, null);
	}

	public MomentsListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MomentsListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
}
