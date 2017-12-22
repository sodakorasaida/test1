package com.internousdev.yataberyouhin.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yataberyouhin.dao.CartInfoDAO;
import com.internousdev.yataberyouhin.dao.LoginDAO;
import com.internousdev.yataberyouhin.dto.CartInfoDTO;
import com.internousdev.yataberyouhin.dto.UserInfoDTO;
import com.internousdev.yataberyouhin.util.ErrorMessageConstants;
import com.opensymphony.xwork2.ActionSupport;


public class LoginAction extends ActionSupport implements SessionAware,ErrorMessageConstants {
	private String userId;
	private String password;
	private boolean saveLogin;
	private Map<String,Object> session;
	private ArrayList<String> errMsgList = new ArrayList<>();

public String execute() throws SQLException {
	String ret = ERROR;
	UserInfoDTO dto = new UserInfoDTO();
	CartInfoDAO cartInfoDAO = new CartInfoDAO();
	int updateCount = 0;
	LoginDAO dao = new LoginDAO();

	if(userId.equals("")){
		errMsgList.add("ユーザーIDを入力してください。");
	} else if(userId.length() < 1 || userId.length() > 16) {
		errMsgList.add("ユーザーIDは1文字以上16文字以下で入力してください。");
	} else if (!userId.matches("^[a-zA-Z0-9]+$")) {
		errMsgList.add("ユーザーIDは半角英数字で入力してください");
	}

	if(password.equals("")){
		errMsgList.add("パスワードを入力してください。");
	} else if (password.length() < 1 || password.length() > 8) {
		errMsgList.add("パスワードは1文字以上8文字以下で入力してください");
	} else if (password.matches("^[a-zA-Z0-9]+$")) {
		errMsgList.add("パスワードは半角英数字で入力してください");
	}


	if(saveLogin){
	session.put("saveId",userId);
	} else {
		session.remove("saveId");
	}



	if(!userId.equals("") && !password.equals("")) {
	dto = dao.select(userId,password);
	if(userId.equals(dto.getUserId())) {
		if(password.equals(dto.getPassword())) {
			dao.login(dto);
			for(CartInfoDTO cartInfoDTO: cartInfoDAO.showTempUserCartList(session.get("userId").toString())) {
				if (cartInfoDAO.isAlreadyIntoCart(dto.getUserId(), cartInfoDTO.getProductId())) {
					cartInfoDAO.deleteProductFromTempUserCart(session.get("userId").toString(), cartInfoDTO.getProductId());
				}
			}
			updateCount = cartInfoDAO.integrateCart(session.get("userId").toString(),	dto.getUserId());
			System.out.println(updateCount + "件統合しました。");
			ret = SUCCESS;
			session.put("userId", dto.getUserId());
			session.put("loginFlg", true);
		}
	}

	}
	return ret;
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



public boolean isSaveLogin() {
	return saveLogin;
}

public void setSaveLogin(boolean saveLogin) {
	this.saveLogin = saveLogin;
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

public ArrayList<String> getErrMsgList() {
	return errMsgList;
}

public void setErrMsgList(ArrayList<String> errMsgList) {
	this.errMsgList = errMsgList;
}

}
