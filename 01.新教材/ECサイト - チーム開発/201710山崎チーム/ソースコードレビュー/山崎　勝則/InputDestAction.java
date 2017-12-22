package com.internousdev.sukesyunshop.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class InputDestAction extends ActionSupport {

	public Map<String, Object> session;

	/*
	 * 宛先情報を登録する画面へ遷移
	 */
	public String execute() {

		return SUCCESS;

	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
