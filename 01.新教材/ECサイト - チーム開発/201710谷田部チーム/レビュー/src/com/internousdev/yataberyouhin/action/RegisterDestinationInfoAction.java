package com.internousdev.yataberyouhin.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yataberyouhin.dao.DestinationInfoDAO;
import com.internousdev.yataberyouhin.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterDestinationInfoAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;

	private String familyName;

	private String firstName;

	private String familyNameKana;

	private String firstNameKana;

	private String email;

	private String telNumber;

	private String userAddress;

	public String execute() throws SQLException {

		String result = ERROR;

		DestinationInfoDTO dto = new DestinationInfoDTO();
		dto.setUserId(session.get("userId").toString());
		dto.setFullName(familyName, firstName, familyNameKana, firstNameKana);
		dto.setEmail(email);
		dto.setTelNumber(telNumber);
		dto.setUserAddress(userAddress);

		DestinationInfoDAO dao = new DestinationInfoDAO();
		if (dao.registerDestination(dto)) {
			result = SUCCESS;
		}

		return result;
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
	 * @return telNumber
	 */
	public String getTelNumber() {
		return telNumber;
	}

	/**
	 * @param telNumber セットする telNumber
	 */
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	/**
	 * @return userAddress
	 */
	public String getUserAddress() {
		return userAddress;
	}

	/**
	 * @param userAddress セットする userAddress
	 */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
}
