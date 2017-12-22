package com.internousdev.yataberyouhin.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yataberyouhin.dao.UserInfoDAO;
import com.internousdev.yataberyouhin.dto.UserInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterUserInfoAction extends ActionSupport implements SessionAware {

	Map<String, Object> session;

	private String familyName;

	private String firstName;

	private String familyNameKana;

	private String firstNameKana;

	/**
	 * 0:男性、1:女性
	 */
	private String sex;

	private String email;

	private String  userId;

	private String password;


	public String execute() throws SQLException {

		UserInfoDAO dao = new UserInfoDAO();
		UserInfoDTO dto = new UserInfoDTO();
		dto.setUserName(familyName, firstName, familyNameKana, firstNameKana);
		if(sex.equals("0")) {
			dto.setSex(false);
		} else if (sex.equalsIgnoreCase("1")) {
			dto.setSex(true);
		}
		dto.setEmail(email);
		dto.setUserId(userId);
		dto.setPassword(password);

		if (dao.registerUser(dto)) {
			session.put("userId", userId);
			session.put("logined", true);

			return SUCCESS;

		} else {
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
	 * @return familyName
	 */
	public String getFamilyName() {
		return familyName;
	}


	/**
	 * @param familyName セットする familyName
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}


	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName セットする firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return familyNameKana
	 */
	public String getFamilyNameKana() {
		return familyNameKana;
	}


	/**
	 * @param familyNameKana セットする familyNameKana
	 */
	public void setFamilyNameKana(String familyNameKana) {
		this.familyNameKana = familyNameKana;
	}


	/**
	 * @return firstNameKana
	 */
	public String getFirstNameKana() {
		return firstNameKana;
	}


	/**
	 * @param firstNameKana セットする firstNameKana
	 */
	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}


	/**
	 * @return sex
	 */
	public String isSex() {
		return sex;
	}


	/**
	 * @param sex セットする sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}


	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email セットする email
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}


	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
