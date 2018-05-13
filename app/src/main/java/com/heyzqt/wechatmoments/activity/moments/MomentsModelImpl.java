package com.heyzqt.wechatmoments.activity.moments;

import com.heyzqt.wechatmoments.activity.moments.model.MomentsModel;
import com.heyzqt.wechatmoments.bean.MomentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heyzqt on 2018/5/13.
 */

public class MomentsModelImpl implements MomentsModel {

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
}
