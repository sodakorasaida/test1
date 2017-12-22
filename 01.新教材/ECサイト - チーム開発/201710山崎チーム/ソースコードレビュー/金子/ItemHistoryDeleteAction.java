package com.internousdev.sukesyunshop.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sukesyunshop.dao.ItemHistoryDAO;
import com.internousdev.sukesyunshop.util.SessionName;
import com.opensymphony.xwork2.ActionSupport;

public class ItemHistoryDeleteAction extends ActionSupport implements SessionAware{

	public Map<String,Object> session;

	public String execute(){
		ItemHistoryDAO deleteItemHistoryDAO= new ItemHistoryDAO();
		String userId=session.get(SessionName.getUserId()).toString();
		try {
			deleteItemHistoryDAO.deleteItemHistory(userId);
			return SUCCESS;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return ERROR;
		}

	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO 自動生成されたメソッド・スタブ
		this.session=session;

	}
}
