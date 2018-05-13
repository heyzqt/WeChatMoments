package com.heyzqt.wechatmoments.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.heyzqt.wechatmoments.R;
import com.heyzqt.wechatmoments.bean.MomentBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heyzqt on 2018/5/13.
 */

public class GridViewAdapter extends BaseAdapter {

	List<MomentBean.ImagesBean> imgs;

	public GridViewAdapter(List<MomentBean.ImagesBean> imgs) {
		this.imgs = imgs;
	}

	@Override
	public int getCount() {
		return imgs.size();
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
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout
					.item_gridview, parent, false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.pic.setImageResource(R.drawable.temp_pic);
//		GlideApp.with(parent.getContext())
//				.load(imgs.get(position).getUrl())
//				.placeholder(R.drawable.temp_pic)
//				.into(viewHolder.pic);

		viewHolder.pic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), "pic " + position, Toast.LENGTH_SHORT).show();
			}
		});

		return convertView;
	}

	static class ViewHolder {

		@BindView(R.id.image)
		ImageView pic;

		public ViewHolder(View view) {
			ButterKnife.bind(this, view);
		}
	}
}
