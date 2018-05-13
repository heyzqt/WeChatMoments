package com.heyzqt.wechatmoments.activity.moments;

import com.heyzqt.wechatmoments.base.BasePresenter;
import com.heyzqt.wechatmoments.base.BaseView;
import com.heyzqt.wechatmoments.bean.MomentBean;
import com.heyzqt.wechatmoments.entity.User;

import java.util.List;

/**
 * Created by heyzqt on 2018/5/13.
 */

public class MomentsContract {

	interface View extends BaseView<Presenter> {

		void showUserInfo(User user);

		void showListView(List<MomentBean> datas);

		void showLikes();

		void showComments();

		void showPullDownRefresh();

		void showPullUpRefresh();
	}

	interface Presenter extends BasePresenter {

		void loadUserInfo();

		void loadMoments();

		void addLike();

		void addComment();
	}
}
