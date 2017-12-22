package com.internousdev.sukesyunshop.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sukesyunshop.dao.LoginAuthDAO;
import com.internousdev.sukesyunshop.util.SessionName;
import com.internousdev.sukesyunshop.util.Validation;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {

	private String userId;
	private String password;
	private String saveId="";

	private Map<String, Object> session;
	private String userIdMessage;
	private String passwordMessage;

	private final String NAME_USER_ID ="ユーザーID";
	private final String NAME_PASSWORD ="パスワード";


	Validation valid = new Validation();
	//TODO 梅田さんにpassword更新時セッション情報を変更してもらう。

	public String execute(){
		userIdMessage = validation(userId, NAME_USER_ID, 1, 8);
		passwordMessage = validation(password, NAME_PASSWORD, 1, 16);

		if(!userIdMessage.equals("") || !passwordMessage.equals("")) return "back";
		LoginAuthDAO loginDAO = new LoginAuthDAO();
		try{
			if(loginDAO.login(userId, password)){
				session.put(SessionName.getUserId(), userId);
				session.put(SessionName.getUserPassword(), password);
				session.put(SessionName.getLoginFlag(), SessionName.getTrue());

				if(saveId.equals("true")){
					session.put(SessionName.getSaveId(), SessionName.getTrue());
				}else{
					session.put(SessionName.getSaveId(), SessionName.getFalse());
				}

				if(session.get(SessionName.getBuying()) == null){
					return SUCCESS;
				}else{
					return "buy";
				}
			}else{
				passwordMessage = "入力されたパスワードが異なります。";
				return "back";
			}

		}catch(Exception e){
			e.printStackTrace();
		}


		return ERROR;
	}

	private String validation(String target, String targetName, int min, int max){

		if(valid.emptyValid(target)){
			return targetName+"を入力してください";
		}else if(valid.overUnderValid(target, min, max)){
			return targetName+"は"+min+"文字以上"+max+"文字以下で入力してください。";
		}else if (valid.harfEngNumValied(target)){
			return targetName+"は半角英数で入力してください";
		}

		return "";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getSaveId() {
		return saveId;
	}

	public void setSaveId(String saveId) {
		this.saveId = saveId;
	}

	public String getUserIdMessage() {
		return userIdMessage;
	}

	public void setUserIdMessage(String userIdMessage) {
		this.userIdMessage = userIdMessage;
	}

	public String getPasswordMessage() {
		return passwordMessage;
	}

	public void setPasswordMessage(String passwordMessage) {
		this.passwordMessage = passwordMessage;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

}
