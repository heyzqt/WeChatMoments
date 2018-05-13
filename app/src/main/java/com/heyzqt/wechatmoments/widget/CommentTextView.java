package com.heyzqt.wechatmoments.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heyzqt.wechatmoments.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heyzqt on 3/15/2018.
 */

public class CommentTextView extends LinearLayout {

	private ViewHolder mViewHolder;

	public CommentTextView(Context context) {
		this(context, null);
	}

	public CommentTextView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CommentTextView(Context context, @Nullable AttributeSet attrs, int
			defStyleAttr) {
		super(context, attrs, defStyleAttr);
		View view = LayoutInflater.from(context).inflate(R.layout.comment_layout, this);
		mViewHolder = new ViewHolder(view);

		if (attrs != null) {
			TypedArray attributes = context.obtainStyledAttributes(attrs,
					R.styleable.CommentTextView);
			initAttrs(context, attributes);
		}
	}

	private void initAttrs(Context context, TypedArray attrs) {
		String title = attrs.getString(R.styleable.CommentTextView_title_text);
		int titlecolor = attrs.getColor(R.styleable.CommentTextView_title_text_color,
				ContextCompat.getColor(context, R.color.font_sender_name));
		float titlesize = attrs.getDimension(R.styleable.CommentTextView_title_text_size,
				getResources().getDimension(R.dimen.font_normal));
		int titlesizetype = attrs.getInteger(R.styleable
				.CommentTextView_content_text_size_type, TypedValue.COMPLEX_UNIT_SP);
		mViewHolder.title.setText(title);
		mViewHolder.title.setTextColor(titlecolor);
		mViewHolder.title.setTextSize(titlesizetype, titlesize);

		String content = attrs.getString(R.styleable.CommentTextView_content_text);
		int contentcolor = attrs.getColor(R.styleable.CommentTextView_content_text_color,
				ContextCompat.getColor(context, R.color.font_black));
		float contentsize = attrs.getDimension(R.styleable
						.CommentTextView_content_text_size,
				getResources().getDimension(R.dimen.font_normal));
		int contentsizetype = attrs.getInteger(R.styleable
				.CommentTextView_content_text_size_type, TypedValue.COMPLEX_UNIT_SP);
		mViewHolder.content.setText(content);
		mViewHolder.content.setTextColor(contentcolor);
		mViewHolder.content.setTextSize(titlesizetype, titlesize);
	}

	public void setTitleText(String title) {
		mViewHolder.title.setText(title);
	}

	public void setContentText(String content) {
		mViewHolder.content.setText(content);
	}

	class ViewHolder {
		@BindView(R.id.title)
		TextView title;
		@BindView(R.id.content)
		TextView content;

		public ViewHolder(View view) {
			ButterKnife.bind(this, view);
		}
	}
}