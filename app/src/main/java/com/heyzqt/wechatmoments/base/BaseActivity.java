package com.heyzqt.wechatmoments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by heyzqt on 2018/5/13.
 */

public abstract class BaseActivity<T> extends AppCompatActivity {

	protected T mPresenter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayout());
		initView();
		initPresenter();
	}

	public abstract int getLayout();

	public abstract void initView();

	public abstract void initPresenter();
}
