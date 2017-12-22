package com.internousdev.sukesyunshop.dto;

public class GoMyPageDTO {

	private String familyName;

	private String firstName;

	private String familyNameKana;

	private String firstNameKana;

	private int sex;

	private String email;

	public String getFamilyName(){
		return familyName;
	}

	public void setFamilyName(String familyName){
		this.familyName=familyName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setFirst_Name(String firstName){
		this.firstName=firstName;
	}

	public String getFamilyNameKana(){
		return familyNameKana;
	}

	public void setFamilyNameKana(String familyNameKana){
		this.familyNameKana=familyNameKana;
	}

	public String getFirstNameKana(){
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana){
		this.firstNameKana=firstNameKana;
	}

	public int getSex(){
		return sex;
	}

	public void setSex(int sex){
		this.sex=sex;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

}
