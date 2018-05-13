package com.heyzqt.wechatmoments.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by heyzqt on 2018/5/14.
 */

public class MomentsGridView extends GridView {

	public MomentsGridView(Context context) {
		super(context);
	}

	public MomentsGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MomentsGridView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(
				Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
