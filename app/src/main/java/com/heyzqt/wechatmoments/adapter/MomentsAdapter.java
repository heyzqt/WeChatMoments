package com.heyzqt.wechatmoments.adapter;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.heyzqt.wechatmoments.R;
import com.heyzqt.wechatmoments.bean.MomentBean;
import com.heyzqt.wechatmoments.util.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heyzqt on 2018/5/13.
 */

public class MomentsAdapter extends BaseAdapter {

	private List<MomentBean> moments;

	public MomentsAdapter(@NonNull List<MomentBean> datas) {
		moments = checkNotNull(datas);
	}

	@Override
	public int getCount() {
		return moments.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout
					.item_listview, parent, false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		//initial personal avatar,name,content
		viewHolder.avatar.setImageResource(R.mipmap.avatar_no_internet);
		viewHolder.name.setText(moments.get(position).getSender().getNick());
		if (TextUtils.isEmpty(moments.get(position).getContent())) {
			viewHolder.content.setVisibility(View.GONE);
		} else {
			viewHolder.content.setVisibility(View.VISIBLE);
			viewHolder.content.setText(moments.get(position).getContent());
		}

		//initial picture GridView
		if (moments.get(position).getImages() == null ||
				moments.get(position).getImages().size() == 0) {
			viewHolder.gridview.setVisibility(View.GONE);
			viewHolder.oneBigPicView.setVisibility(View.GONE);
		} else if (moments.get(position).getImages() != null &&
				moments.get(position).getImages().size() == 1) {
			//Only has one photo
			viewHolder.gridview.setVisibility(View.GONE);
			viewHolder.oneBigPicView.setVisibility(View.VISIBLE);
			GlideApp.with(parent.getContext())
					.load(moments.get(position).getImages().get(0))
					.placeholder(R.mipmap.loading)
					.into(viewHolder.oneBigPicView);
		} else {
			viewHolder.gridview.setVisibility(View.VISIBLE);
			viewHolder.oneBigPicView.setVisibility(View.GONE);
			viewHolder.gridview.setAdapter(new GridViewAdapter(moments.get(position).getImages()));
		}

		//initial time textview and comment button
		viewHolder.time.setText("17 minutes ago");
		viewHolder.commentBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), "click comment btn" + position,
						Toast.LENGTH_SHORT).show();
			}
		});

		//initial likes layout and comments layout
		return convertView;
	}

	static class ViewHolder {
		@BindView(R.id.avatar_img)
		ImageView avatar;

		@BindView(R.id.name)
		TextView name;

		@BindView(R.id.content)
		TextView content;

		@BindView(R.id.gridview)
		GridView gridview;

		@BindView(R.id.one_pic)
		ImageView oneBigPicView;

		@BindView(R.id.send_time)
		TextView time;

		@BindView(R.id.comment_btn)
		ImageView commentBtn;

		@BindView(R.id.likes_layout)
		LinearLayout likesLayout;

		public ViewHolder(View view) {
			ButterKnife.bind(this, view);
		}
	}
}
