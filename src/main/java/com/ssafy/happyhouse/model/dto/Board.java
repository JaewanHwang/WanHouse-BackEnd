package com.ssafy.happyhouse.model.dto;

import java.sql.Timestamp;

public class Board {
	private int no;
	private String title;
	private String content;
	private Timestamp lastModified;
	private String memberId;

	public Board(int no, String title, String content, Timestamp lastModified, String memberId) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.lastModified = lastModified;
		this.memberId = memberId;
	}

	public Board(int no, String title, String content, String memberId) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.memberId = memberId;
	}

	public Board(String title, String content, String memberId) {
		super();
		this.title = title;
		this.content = content;
		this.memberId = memberId;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Board [no=" + no + ", title=" + title + ", content=" + content + ", lastModified=" + lastModified
				+ ", memberId=" + memberId + "]";
	}

}
