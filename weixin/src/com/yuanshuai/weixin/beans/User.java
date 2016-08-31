package com.yuanshuai.weixin.beans;

import java.util.UUID;

public class User {

	private String id;
	private String name;
	private String openId;
	private boolean robot;
	private long addTime;
	private String note;

	public User() {
		this.id = UUID.randomUUID().toString();
		this.name = "";
		this.openId = "";
		this.robot = false;
		this.addTime = System.currentTimeMillis();
		this.note = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public boolean getRobot() {
		return robot;
	}

	public void setRobot(boolean robot) {
		this.robot = robot;
	}

	public long getAddTime() {
		return addTime;
	}

	public void setAddTime(long addTime) {
		this.addTime = addTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
