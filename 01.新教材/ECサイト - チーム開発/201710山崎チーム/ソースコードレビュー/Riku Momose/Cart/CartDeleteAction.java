package com.internousdev.sukesyunshop.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sukesyunshop.dao.CartDAO;
import com.internousdev.sukesyunshop.util.SessionName;
import com.opensymphony.xwork2.ActionSupport;

public class CartDeleteAction extends ActionSupport implements SessionAware{

	private CartDAO cartDAO;
	public Map<String, Object> session;
	private int productId;


	public String result = ERROR;
	public String execute(){
		cartDAO = new CartDAO();



		String userId;
		boolean loginFlag = session.get(SessionName.getLoginFlag()).equals(SessionName.getTrue());
		if(loginFlag){
			userId = session.get(SessionName.getUserId()).toString();
		}else{
			userId = session.get(SessionName.getTempUserId()).toString();
		}

			try{
				cartDAO.deleteCart(userId, productId, loginFlag);
				result = SUCCESS;
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
			return result;

	}



	public CartDAO getCartDAO() {
		return cartDAO;
	}
	public void setCartDAO(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

}


