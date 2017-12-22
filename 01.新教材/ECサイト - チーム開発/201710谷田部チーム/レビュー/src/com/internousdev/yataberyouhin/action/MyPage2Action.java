package com.internousdev.yataberyouhin.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yataberyouhin.dao.UserInfoDAO;
import com.internousdev.yataberyouhin.dto.UserInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class MyPage2Action extends ActionSupport implements SessionAware{

	public Map<String,Object> session;
	public ArrayList<UserInfoDTO> userInfoDTO=new ArrayList<UserInfoDTO>();

	/**
	 * ユーザー情報取得メソッド
	 */


	public String execute() {
	      String ret=ERROR;
	      UserInfoDAO userInfoDAO=new UserInfoDAO();
		userInfoDTO=userInfoDAO.userInfo(session.get("userId").toString());

		if(userInfoDTO.size()>0){
			ret=SUCCESS;
		}
		return ret;
	}


	public ArrayList<UserInfoDTO> getUserInfoDTO() {
		return userInfoDTO;
	}


	public void setUserInfoDTO(ArrayList<UserInfoDTO> userInfoDTO) {
		this.userInfoDTO = userInfoDTO;
	}


	public Map<String, Object> getSession() {
		return session;
	}


	public void setSession(Map<String, Object> session) {
		this.session = session;
	}






	}


