package com.internousdev.sukesyunshop.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sukesyunshop.dao.SearchDAO;
import com.internousdev.sukesyunshop.dto.CategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class GoHomeAction extends ActionSupport implements SessionAware {

	private ArrayList<CategoryDTO> cateList;
	public Map<String, Object> session;

	public String execute() {

		// TODO今後ホームに何を表示するかによって取得する値を変更します

		SearchDAO searchDAO = new SearchDAO();
		try {
			setCateList(searchDAO.getCategory());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public ArrayList<CategoryDTO> getCateList() {
		return cateList;
	}

	public void setCateList(ArrayList<CategoryDTO> cateList) {
		this.cateList = cateList;
	}
}