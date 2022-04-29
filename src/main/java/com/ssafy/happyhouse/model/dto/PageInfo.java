package com.ssafy.happyhouse.model.dto;

public class PageInfo {
	private boolean isForward;
	private String url;

	public PageInfo(boolean isForward, String url) {
		super();
		this.isForward = isForward;
		this.url = url;
	}

	public boolean isForward() {
		return isForward;
	}

	public void setForward(boolean isForward) {
		this.isForward = isForward;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "PageInfo [isForward=" + isForward + ", url=" + url + "]";
	}

}
