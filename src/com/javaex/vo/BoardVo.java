package com.javaex.vo;

public class BoardVo {
	private int no;
	private String title;
	private int userNo;
	private int hit;
	private String regDate;
	private String content;
	private String userName;
	
	public BoardVo() {
		
	}
	
	
	public BoardVo(int no, String title, String content) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
	}


	public BoardVo(int no, String title, int userNo, int hit, String regDate, String content, String userName) {
		super();
		this.no = no;
		this.title = title;
		this.userNo = userNo;
		this.hit = hit;
		this.regDate = regDate;
		this.content = content;
		this.userName = userName;
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

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", userNo=" + userNo + ", hit=" + hit + ", regDate=" + regDate
				+ ", content=" + content + ", userName=" + userName + "]";
	}
	
	
	
	
}
