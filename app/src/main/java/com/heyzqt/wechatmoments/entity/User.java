package com.heyzqt.wechatmoments.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by heyzqt on 2018/5/13.
 */

public class User {

	private String nick;

	private String username;

	@SerializedName("avatar")
	private String avatarurl;

	@SerializedName("profile-image")
	private String profileurl;

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

	public String getAvatarurl() {
		return avatarurl;
	}

	public void setAvatarurl(String avatarurl) {
		this.avatarurl = avatarurl;
	}

	public String getProfileurl() {
		return profileurl;
	}

	public void setProfileurl(String profileurl) {
		this.profileurl = profileurl;
	}

	@Override
	public String toString() {
		return "User{" +
				"nick='" + nick + '\'' +
				", username='" + username + '\'' +
				", avatarurl='" + avatarurl + '\'' +
				", profileurl='" + profileurl + '\'' +
				'}';
	}
}
