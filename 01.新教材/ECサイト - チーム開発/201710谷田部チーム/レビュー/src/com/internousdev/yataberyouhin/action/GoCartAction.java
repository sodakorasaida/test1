package com.internousdev.yataberyouhin.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yataberyouhin.dao.CartInfoDAO;
import com.internousdev.yataberyouhin.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class GoCartAction extends ActionSupport implements SessionAware {

	Map<String, Object> session;

	int deleteProduct = 0;

	ArrayList<CartInfoDTO> cartList = new ArrayList<>();

	int totalPrice;


	public String execute() throws SQLException {
		CartInfoDAO dao = new CartInfoDAO();
		//削除商品があれば削除
		if (deleteProduct != 0) {
			dao.deleteProductFromCart(session.get("userId").toString(), deleteProduct);
		}
		//ログインユーザーのカート情報を引き出す
		if (session.containsKey("loginFlg") && (boolean) session.get("loginFlg")) {
			for (CartInfoDTO dto: dao.showUserCartList(session.get("userId").toString())) {
				cartList.add(dto);
			}
		//非ログインユーザーのカート情報を引き出す
		} else {
			for (CartInfoDTO dto: dao.showTempUserCartList(session.get("userId").toString())) {
				cartList.add(dto);
			}
		}
		//合計金額の算出
		totalPrice = calcTotalPrice(cartList);


		return SUCCESS;
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

	/**
	 * @return deleteProduct
	 */
	public int getDeleteProduct() {
		return deleteProduct;
	}


	/**
	 * @param deleteProduct セットする deleteProduct
	 */
	public void setDeleteProduct(int deleteProduct) {
		this.deleteProduct = deleteProduct;
	}


	/**
	 * @return cartList
	 */
	public ArrayList<CartInfoDTO> getCartList() {
		return cartList;
	}

	/**
	 * @param cartList セットする cartList
	 */
	public void setCartList(ArrayList<CartInfoDTO> cartList) {
		this.cartList = cartList;
	}


	/**
	 * @return totalPrice
	 */
	public int getTotalPrice() {
		return totalPrice;
	}


	/**
	 * @param totalPrice セットする totalPrice
	 */
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	/**
	 * 合計金額を計算するメソッド
	 */
	public int calcTotalPrice(ArrayList<CartInfoDTO> cartList) {
		int totalPrice = 0;
		for(CartInfoDTO dto: cartList) {
			totalPrice += dto.getPrice() * dto.getCount();
		}
		return totalPrice;
	}
}
