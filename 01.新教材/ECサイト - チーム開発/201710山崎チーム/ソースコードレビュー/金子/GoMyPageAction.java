package com.internousdev.sukesyunshop.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sukesyunshop.dao.GoMyPageDAO;
import com.internousdev.sukesyunshop.dto.UserInfoDTO;
import com.internousdev.sukesyunshop.util.SessionName;
import com.opensymphony.xwork2.ActionSupport;

public class GoMyPageAction extends ActionSupport implements SessionAware{

	public Map<String,Object> session;
	public UserInfoDTO dto;

public String execute(){
	dto =new UserInfoDTO();
	String userId =(String) session.get(SessionName.getUserId());

	System.out.println(dto.getUserId());
	try{
		GoMyPageDAO dao =new GoMyPageDAO();
		dto=dao.GoMyPageList(userId);
		return SUCCESS;
	}catch (SQLException e){
		e.printStackTrace();
		return ERROR;
	}
}

/**
 * @return session
 */
public Map<String, Object> getSession() {
	return session;
}

/**
 * @param session セットする session
 */
public void setSession(Map<String, Object> session) {
	this.session = session;
}

/**
 * @return dto
 */
public UserInfoDTO getDto() {
	return dto;
}

/**
 * @param dto セットする dto
 */
public void setDto(UserInfoDTO dto) {
	this.dto = dto;
}
}