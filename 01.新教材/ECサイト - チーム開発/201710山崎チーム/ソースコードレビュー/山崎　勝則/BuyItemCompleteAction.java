package com.internousdev.sukesyunshop.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sukesyunshop.dao.BuyItemCompleteDAO;
import com.internousdev.sukesyunshop.dao.CartDAO;
import com.internousdev.sukesyunshop.dao.DestinationDAO;
import com.internousdev.sukesyunshop.dto.BuyItemDTO;
import com.internousdev.sukesyunshop.dto.CartDTO;
import com.internousdev.sukesyunshop.dto.DestinationDTO;
import com.internousdev.sukesyunshop.util.SessionName;
import com.opensymphony.xwork2.ActionSupport;

public class BuyItemCompleteAction extends ActionSupport implements SessionAware {

	private String userId;
	private int productId;

	public Map<String, Object> session;

	/**
	 * 宛先情報DAO&宛先情報DTOをインスタンス化
	 */
	private DestinationDAO destDAO = new DestinationDAO();
	private List<DestinationDTO> destDTOList = new ArrayList<DestinationDTO>();
	//TODO あて先情報のリストのインデックスを取得したものの使う先がない。
	private int destNum;

	/**
	 * 商品購入完了DAO＆商品情報DTOをインスタンス化
	 *
	 */
	public List<BuyItemDTO> buyItemDTOList = new ArrayList<BuyItemDTO>();
	private BuyItemCompleteDAO buyItemDAO = new BuyItemCompleteDAO();
	public ArrayList<CartDTO> cartDTO;
	private CartDAO cartDAO = new CartDAO();

	/**
	 * 登録されている宛先情報を参照する 宛先が登録されていない場合はRETURNの値は"lack"へ
	 *
	 * ↓↓次の処理内容へ
	 *
	 * カート情報を参照する 参照した情報を画面に表示
	 *
	 * ↓↓次の処理へ
	 *
	 * 購入完了画面へ遷移する時にカート情報を削除する処理
	 *
	 * ------------ 実行メソッド------------
	 */
	public String execute() {

		// 宛先情報の参照
		userId = session.get(SessionName.getUserId()).toString();
		ArrayList<CartDTO> cartList = cartDAO.getCartList(userId, true);

		try {
			destDTOList = destDAO.destSelect(userId);
			if (destDTOList.size() == 0) {
				return "lack";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return ERROR;
		}

		// HTML側から送られてきた商品情報を登録用のDTO(List)へまとめ購入履歴テーブルへ情報を登録する処理
		int count = cartList.size();
		List<Integer> productIdList = new ArrayList<>();
		for (int i = 0; count > i; i++) {
			productIdList.add(cartList.get(i).getProductId());
		}
		try {
			System.out.println("aaaaaaaaaaa");
			int cc = buyItemDAO.itemInsert(productIdList, userId);
			System.out.println(cc);
		// カート情報の削除ができないです。
			buyItemDAO.itemDelete(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return SUCCESS;

	}

	public ArrayList<CartDTO> getCartList() {
		return cartDTO;
	}

	public void setCartList(ArrayList<CartDTO> cartList) {
		this.cartDTO = cartList;
	}

	public List<DestinationDTO> getDestDTOList() {
		return destDTOList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getDestNum() {
		return destNum;
	}

	public void setDestNum(int destNum) {
		this.destNum = destNum;
	}

	public List<BuyItemDTO> getBuyItemDTOList() {
		return buyItemDTOList;
	}

	public void setBuyItemDTOList(List<BuyItemDTO> buyItemDTOList) {
		this.buyItemDTOList = buyItemDTOList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setDestDTOList(List<DestinationDTO> destDTOList) {
		this.destDTOList = destDTOList;
	}

}
