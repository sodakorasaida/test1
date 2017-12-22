package com.internousdev.sukesyunshop.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sukesyunshop.dao.CatalogDAO;
import com.internousdev.sukesyunshop.dto.CatalogDTO;
import com.opensymphony.xwork2.ActionSupport;


public class CatalogAction extends ActionSupport implements SessionAware {

	public Map<String, Object> session;
	private CatalogDAO catalogDAO = new CatalogDAO();
	private ArrayList<CatalogDTO> list;
	private String emptyMessage;


	private String result = ERROR;
	public String execute(){

		try{
			list=catalogDAO.getCatalogList();
				if(list.size() == 0){
				emptyMessage = "検索結果がありません";
			}
			result = SUCCESS;
			} catch (Exception e){
				e.printStackTrace();
			}
			return result;

	}



	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public ArrayList<CatalogDTO> getList() {
		return list;
	}
	public void setList(ArrayList<CatalogDTO> list) {
		this.list = list;
	}

	public String getEmptyMessage() {
		return emptyMessage;
	}
	public void setEmptyMessage(String emptyMessage) {
		this.emptyMessage = emptyMessage;
	}


}