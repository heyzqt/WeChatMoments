package com.heyzqt.wechatmoments.activity.moments;

import android.util.Log;

import com.google.gson.Gson;
import com.heyzqt.wechatmoments.activity.moments.model.MomentsModel;
import com.heyzqt.wechatmoments.bean.MomentBean;
import com.heyzqt.wechatmoments.entity.User;
import com.heyzqt.wechatmoments.util.OkHttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by heyzqt on 2018/5/13.
 */

public class MomentsModelImpl implements MomentsModel {

	private static final String TAG = "MomentsModelImpl";

	@Override
	public void loadUserInfo(String url, final OnLoadUserInfoListener listener) throws
			IOException {

		OkHttpUtils.getFormConn(url, null, new OkHttpUtils.DataCallBack() {
			@Override
			public void requestSuccess(String result) throws Exception {
				Log.i(TAG, "requestSuccess: get user info succeed");
				listener.onSuccess(getUserObj(result));
			}

			@Override
			public void requestFailure(Request request, IOException e) {
				Log.i(TAG, "requestSuccess: get user info failed");
				Log.i(TAG, "requestFailure: error : " + e.toString());
				listener.onFailure(-1);
			}
		});
	}

	@Override
	public void loadMoments(String url, int type, OnLoadMomentsListener listener) {

		//1连接成功，0连接失败
		int statu = 1;
		int error = 404;


		try {
			//模拟网络连接
			Thread.sleep(3000);
			if (statu == 1) {
				listener.onSuccess(getData());
			} else if (statu == 0) {
				listener.onFailure(404);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	List<MomentBean> getData() {
		List<MomentBean> datas = new ArrayList<>();
		for (int i = 0; i < 16; i++) {
			MomentBean moment = new MomentBean();
			moment.setContent("This is a test content");
			MomentBean.ImagesBean imagesBean = new MomentBean.ImagesBean();
			imagesBean.setUrl("images//url");
			MomentBean.SenderBean senderBean = new MomentBean.SenderBean();
			senderBean.setUsername("111");
			senderBean.setNick("Joe Portman");
			senderBean.setAvatar("avatar");
			moment.setSender(senderBean);
			MomentBean.CommentsBean commentsBean = new MomentBean.CommentsBean();
			commentsBean.setContent("goooooood job");
			MomentBean.SenderBean senderBean1 = new MomentBean.SenderBean();
			senderBean1.setNick("Super hero");
			senderBean1.setUsername("outman");
			senderBean1.setAvatar("avatar111");
			datas.add(moment);
		}
		return datas;
	}

	private User getUserObj(String json) {
		User user;
		Gson gson = new Gson();
		user = gson.fromJson(json, User.class);
		return user;
	}
}
