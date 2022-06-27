package com.tweetapp.model;

import java.util.List;

public class TagResponse {
	private String user;
	private List<String> tweetId;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<String> getTweetId() {
		return tweetId;
	}

	public void setTweetId(List<String> tweetId) {
		this.tweetId = tweetId;
	}

}
