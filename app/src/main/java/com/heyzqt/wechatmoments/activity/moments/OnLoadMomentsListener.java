package com.heyzqt.wechatmoments.activity.moments;

import com.heyzqt.wechatmoments.bean.MomentBean;

import java.util.List;

/**
 * Created by heyzqt on 2018/5/13.
 */

public interface OnLoadMomentsListener {

	void onSuccess(List<MomentBean> datas);

	void onFailure(int error);
}
