package com.internousdev.yataberyouhin.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yataberyouhin.dao.PurchaseHistoryDAO;
import com.internousdev.yataberyouhin.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class PurchaseHistoryAction extends ActionSupport implements SessionAware{

	public Map<String,Object>session;
	public PurchaseHistoryDAO purchaseHistoryDAO=new PurchaseHistoryDAO();
	public ArrayList<PurchaseHistoryInfoDTO> purchaseHistoryDTO=new ArrayList<PurchaseHistoryInfoDTO>();
	private String message;
	private String deleteFlg="";


	/**
	 * 商品履歴取得メソッド
	 *
	 * @author internous
	 */
	public String execute() throws SQLException {

			if(deleteFlg.equals("")){
				purchaseHistoryDTO=purchaseHistoryDAO.myPageInfo(session.get("userId").toString());


				Iterator<PurchaseHistoryInfoDTO> iterator = purchaseHistoryDTO.iterator();
				if (!(iterator.hasNext())) {
					purchaseHistoryDTO = null;
				}
			}else if(deleteFlg.equals("1")){
			int res=purchaseHistoryDAO.deleteInfo(session.get("userId").toString());
		if(res>0){
			purchaseHistoryDTO=null;
			setMessage("商品情報を正しく削除しました");

		}else if(res==0){
			setMessage("商品情報の削除に失敗しました");
		}
			}
				return SUCCESS;

	}






	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public ArrayList<PurchaseHistoryInfoDTO> getPurchaseHistoryDTO() {
		return purchaseHistoryDTO;
	}
	public void setPurchaseHistoryDTO(ArrayList<PurchaseHistoryInfoDTO> purchaseHistoryDTO) {
		this.purchaseHistoryDTO = purchaseHistoryDTO;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}



}



