package com.internousdev.sukesyunshop.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sukesyunshop.dao.CatalogDAO;
import com.internousdev.sukesyunshop.dto.CatalogDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDetailAction extends ActionSupport implements SessionAware {

	public int productId;
	public CatalogDTO dto;
	public Map<String, Object> session;
	public ArrayList<CatalogDTO> miniList;
	public CatalogDAO catalogdao = new CatalogDAO();


	private String result = ERROR;
	public String execute(){

		try{
			dto=catalogdao.getItem(productId);
			miniList=catalogdao.miniList(dto.getCategoryId());

			result=SUCCESS;
			}catch(SQLException e){
				e.printStackTrace();
			}
			return result;

		}


		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}

		public Map<String,Object> getSession () {
			return session;
		}
		public void setSession(Map<String, Object> arg0) {
				this.session = arg0;
		}

}


