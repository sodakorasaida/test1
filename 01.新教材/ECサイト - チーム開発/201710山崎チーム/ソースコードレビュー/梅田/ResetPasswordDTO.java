package com.internousdev.sukesyunshop.dto;

public class ResetPasswordDTO {

	/*ログインID*/
	private String loginId;

	/*新規パスワード*/
	private String loginPassword;

	/*確認用パスワード*/
	private String confirmPassword;

	/*ログインIDのゲッターセッター*/
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/*新規パスワードのゲッターセッター*/
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	/*確認用のパスワードのゲッターセッター*/
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
