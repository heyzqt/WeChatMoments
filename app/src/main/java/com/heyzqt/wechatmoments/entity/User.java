package com.heyzqt.wechatmoments.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by heyzqt on 2018/5/13.
 */

public class User {

	private String nick;

	private String username;

	private String avatar;

	@SerializedName("profile-image")
	private String imageurl;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	@Override
	public String toString() {
		return "User{" +
				"nick='" + nick + '\'' +
				", username='" + username + '\'' +
				", avatar='" + avatar + '\'' +
				", imageurl='" + imageurl + '\'' +
				'}';
	}
}
