package com.example.homeworkhelp.bean;

import org.litepal.crud.DataSupport;

/**
 * @author Administrator
 * @time 2017/3/14 0014 22:03
 */

public class UserBean extends DataSupport {
	private String userName;
	private String phone;
	private byte[] userHead;

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte[] getUserHead() {
		return userHead;
	}
	
	public void setUserHead(byte[] userHead) {
		this.userHead = userHead;
	}
}
