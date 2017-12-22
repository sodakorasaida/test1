package com.internousdev.yataberyouhin.dto;

import java.util.Date;

public class UserInfoDTO {

	private int id;

	private String  userId;

	private String password;

	private String familyName;

	private String firstName;

	private String familyNameKana;

	private String firstNameKana;

	/**
	 * 0:男性、1:女性
	 */
	private boolean sex;

	private String email;

	private boolean status;

	private boolean logined;

	private Date register_date;

	private Date update_date;


	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
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
	public boolean isSex() {
		return sex;
	}

	/**
	 * @param sex セットする sex
	 */
	public void setSex(boolean sex) {
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
	 * @return status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status セットする status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return logined
	 */
	public boolean isLogined() {
		return logined;
	}

	/**
	 * @param logined セットする logined
	 */
	public void setLogined(boolean logined) {
		this.logined = logined;
	}

	/**
	 * @return register_date
	 */
	public Date getRegister_date() {
		return register_date;
	}

	/**
	 * @param register_date セットする register_date
	 */
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}

	/**
	 * @return update_date
	 */
	public Date getUpdate_date() {
		return update_date;
	}

	/**
	 * @param update_date セットする update_date
	 */
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public void setUserName (String familyName, String firstName, String familyNameKana, String firstNameKana) {
		this.familyName = familyName;
		this.firstName = firstName;
		this.familyNameKana = familyNameKana;
		this.firstNameKana = firstNameKana;
	}
}
