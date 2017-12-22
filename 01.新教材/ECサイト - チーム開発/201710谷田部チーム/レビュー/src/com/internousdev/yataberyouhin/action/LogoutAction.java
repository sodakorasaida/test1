package com.internousdev.yataberyouhin.action;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yataberyouhin.dao.LoginDAO;
import com.internousdev.yataberyouhin.dto.UserInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware{
	public Map<String,Object> session;

	public String execute() throws SQLException {
		String result = SUCCESS;
		UserInfoDTO dto = new UserInfoDTO();
		dto.setUserId(session.get("userId").toString());
		LoginDAO dao = new LoginDAO();
		dao.logout(dto);
		session.replace("userId", UUID.randomUUID());
		session.replace("loginFlg",false);
		return result;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
