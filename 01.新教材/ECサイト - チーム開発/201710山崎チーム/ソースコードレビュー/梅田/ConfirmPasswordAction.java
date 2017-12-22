package com.internousdev.sukesyunshop.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sukesyunshop.dao.ResetPasswordDAO;
import com.internousdev.sukesyunshop.dto.ResetPasswordDTO;
import com.internousdev.sukesyunshop.util.Validation;
import com.opensymphony.xwork2.ActionSupport;

public class ConfirmPasswordAction extends ActionSupport implements SessionAware {

	/*ログインID*/
	private String loginId;

	/*新規パスワード*/
	private String loginPassword;

	/*確認用パスワード*/
	private String confirmPassword;

	/*ログインIDと新規パスワードを格納*/
	public Map<String,Object> session;

	/*新規パスワード入力についてのエラーメッセージ*/
	private String passwordMessage;

	/*Validationクラスをインスタンス化*/
	public Validation validation = new Validation();

	public ResetPasswordDAO resetPasswordDAO=new ResetPasswordDAO();

	public ResetPasswordDTO resetPasswordDTO=new ResetPasswordDTO();


	/*---------実行メソッド-----------*/
	public String execute(){

		/*パスワードが未入力*/
		if(validation.emptyValid(loginPassword)){
			passwordMessage="パスワードを入力してください。";
			return ERROR;
		}

		/*パスワードの桁数*/
		if(validation.overUnderValid(loginPassword,1,16)){
			passwordMessage="パスワードは1文字以上16文字以下で入力してください。";
			return ERROR;
		}

		/*パスワードの文字種*/
		if(validation.harfEngNumValied(loginPassword)){
			passwordMessage="パスワードは半角英数字で入力してください。";
			return ERROR;
		}

		/*新規パスワードと確認パスワードを比べる*/
		if(loginPassword.equals(confirmPassword)){
			session.put("loginId",loginId);
			session.put("loginPassword", loginPassword);
			return SUCCESS;
		}else{
			passwordMessage="入力されたパスワードが異なります。";
			return ERROR;
		}
	}


		/*新規パスワードのゲッターセッター*/
		public String getLoginPassword() {
			return loginPassword;
		}

		public void setLoginPassword(String loginPassword) {
			this.loginPassword = loginPassword;
		}

		/*ログインIDのゲッターセッター*/
		public String getLoginId() {
			return loginId;
		}
		public void setLoginId(String loginId) {
			this.loginId = loginId;
		}

		/*確認用パスワードのゲッターセッター*/
		public String getConfirmPassword(){
			return confirmPassword;
		}
		public void setConfirmPassword(String confirmPassword){
			this.confirmPassword=confirmPassword;
		}

		/*パスワード入力エラーメッセージのゲッターセッター*/
		public String getPasswordMessage() {
			return passwordMessage;
		}
		public void setPasswordMessage(String passwordMessage) {
			this.passwordMessage = passwordMessage;
		}


		/*セッション*/
		public Map<String, Object> getSession() {
			return session;
		}
		@Override
		public void setSession(Map<String, Object> session) {
			this.session = session;
		}
}


