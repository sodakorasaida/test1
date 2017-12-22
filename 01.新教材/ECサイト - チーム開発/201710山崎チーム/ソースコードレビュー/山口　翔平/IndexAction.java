package com.internousdev.sukesyunshop.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sukesyunshop.util.RandomID;
import com.internousdev.sukesyunshop.util.SessionName;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;

	public String execute(){
		RandomID randomId = new RandomID();
		session.put(SessionName.getTempUserId(), randomId.getTempId());
		session.put(SessionName.getLoginFlag(), SessionName.getFalse());
		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
