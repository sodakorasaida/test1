package com.internousdev.yataberyouhin.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yataberyouhin.dao.CartInfoDAO;
import com.internousdev.yataberyouhin.dao.DestinationInfoDAO;
import com.internousdev.yataberyouhin.dto.CartInfoDTO;
import com.internousdev.yataberyouhin.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class PurchaseInfoAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;
	private int totalPrice=0;
	private int count;

	private List<CartInfoDTO> cartInfoDTOList=new ArrayList<CartInfoDTO>();
	private ArrayList<DestinationInfoDTO> destinationInfoListDTO=new ArrayList<DestinationInfoDTO>();


	/**
	 * 決済情報取得メソッド
	 */

	public String execute() throws SQLException{
		String ret=ERROR;
		CartInfoDAO dao=new CartInfoDAO();
		cartInfoDTOList=dao.showUserCartList(session.get("userId").toString());

		for(CartInfoDTO dto:cartInfoDTOList){
			totalPrice +=dto.getPrice() * dto.getCount();


		}

		if (cartInfoDTOList.size() > 0) {
			ret = SUCCESS;
		} else {
			ret = ERROR;
		}
		/**
		 * 宛先情報取得メソッド
		 */
		DestinationInfoDAO DAO=new DestinationInfoDAO();
		destinationInfoListDTO=DAO.obtainingDestinationInfo(session.get("userId").toString());

		if(destinationInfoListDTO.size()>0){
			ret=SUCCESS;
		}else{
			ret=ERROR;
		}
		return ret;
	}



	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<CartInfoDTO> getCartInfoDTOList() {
		return cartInfoDTOList;
	}

	public void setCartInfoDTOList(List<CartInfoDTO> cartInfoDTOList) {
		this.cartInfoDTOList = cartInfoDTOList;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}



	public ArrayList<DestinationInfoDTO> getDestinationInfoListDTO() {
		return destinationInfoListDTO;
	}



	public void setDestinationInfoListDTO(ArrayList<DestinationInfoDTO> destinationInfoListDTO) {
		this.destinationInfoListDTO = destinationInfoListDTO;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}











}
