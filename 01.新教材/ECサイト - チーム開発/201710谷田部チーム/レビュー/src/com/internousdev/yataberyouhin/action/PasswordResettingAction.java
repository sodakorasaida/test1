package com.internousdev.yataberyouhin.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yataberyouhin.dao.UserInfoDAO;
import com.internousdev.yataberyouhin.dto.UserInfoDTO;
import com.internousdev.yataberyouhin.util.ErrorMessageConstants;
import com.opensymphony.xwork2.ActionSupport;


public class PasswordResettingAction extends ActionSupport implements SessionAware,ErrorMessageConstants {
	private String password;
	private String passwordConfirm;
	private String userId;
	private List<UserInfoDTO> userInfoDTOList = new ArrayList<UserInfoDTO>();
	private Map<String,Object> session;
	private ArrayList<String> errMsgList = new ArrayList<>();


	public String execute() {

		String ret = ERROR;
		if(password.equals(passwordConfirm)) {
			UserInfoDAO dao = new UserInfoDAO();
			int count = dao.update(password,userId);

			if(count > 0) {
				ret = SUCCESS;
			} else {
				errMsgList.add("ログインIDが存在しません。");
				ret = ERROR;
			}
			userInfoDTOList = dao.select(password,userId);
			session.put("userInfoList", userInfoDTOList);
		}

		return ret;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordconfirm) {
		this.passwordConfirm = passwordconfirm;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserInfoDTO> getUserInfoDTOList() {
		return userInfoDTOList;
	}

	public void setUserInfoDTOList(List<UserInfoDTO> userInfoDTOList) {
		this.userInfoDTOList = userInfoDTOList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public ArrayList<String> getErrMsgList() {
		return errMsgList;
	}

	public void setErrMsgList(ArrayList<String> errMsgList) {
		this.errMsgList = errMsgList;
	}


}
