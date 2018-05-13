package com.heyzqt.wechatmoments.activity.moments;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.heyzqt.wechatmoments.activity.moments.model.MomentsModel;
import com.heyzqt.wechatmoments.bean.MomentBean;
import com.heyzqt.wechatmoments.entity.User;
import com.heyzqt.wechatmoments.util.OkHttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
	public void loadMoments(String url, final OnLoadMomentsListener listener) throws IOException {

		OkHttpUtils.getFormConn(url, null, new OkHttpUtils.DataCallBack() {
			@Override
			public void requestSuccess(String result) throws Exception {
				Log.i(TAG, "requestSuccess: get user info succeed");
				listener.onSuccess(getMomentBeans(result));
			}

			@Override
			public void requestFailure(Request request, IOException e) {
				Log.i(TAG, "requestSuccess: get user info failed");
				Log.i(TAG, "requestFailure: error : " + e.toString());
				listener.onFailure(-1);
			}
		});
	}

	private User getUserObj(String json) {
		User user;
		Gson gson = new Gson();
		user = gson.fromJson(json, User.class);
		return user;
	}

	private List<MomentBean> getMomentBeans(String json) {
		List<MomentBean> momentBeans = new ArrayList<>();
		momentBeans = parseNoHeaderJArray(json);

		Iterator iterator = momentBeans.iterator();
		int remove = 0;
		while (iterator.hasNext()) {
			MomentBean moment = (MomentBean) iterator.next();

			/**
			 * Jump if meet error message.
			 */
			String content = moment.getContent();
			String error = moment.getError();
			String unKnownError = moment.getUnknownError();
			List<MomentBean.ImagesBean> imgs = moment.getImages();
			if (!TextUtils.isEmpty(error) && !error.equals("null")) {
				iterator.remove();
				remove++;
			} else if (!TextUtils.isEmpty(unKnownError) && !unKnownError.equals("null")) {
				iterator.remove();
				remove++;
			} else if (TextUtils.isEmpty(content) && (imgs == null || imgs.size() == 0)) {
				iterator.remove();
				remove++;
			}
		}
		Log.i(TAG, "getMomentBeans: moment num = " + momentBeans.size());
		Log.i(TAG, "getMomentBeans: remove num = " + remove);
		return momentBeans;
	}

	/**
	 * 解析没有数据头的纯数组
	 */
	private List<MomentBean> parseNoHeaderJArray(String strByJson) {
		JsonParser parser = new JsonParser();
		//Convert String to JsonArray
		JsonArray jsonArray = parser.parse(strByJson).getAsJsonArray();

		Gson gson = new Gson();
		ArrayList<MomentBean> momentBeans = new ArrayList<>();

		for (JsonElement user : jsonArray) {
			MomentBean moment = gson.fromJson(user, MomentBean.class);
			momentBeans.add(moment);
			Log.i(TAG, "parseNoHeaderJArray: moment " + moment.toString());
		}
		return momentBeans;
	}
}
