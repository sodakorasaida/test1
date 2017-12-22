package com.internousdev.yataberyouhin.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.yataberyouhin.dao.CartInfoDAO;
import com.internousdev.yataberyouhin.dao.ProductInfoDAO;
import com.internousdev.yataberyouhin.dao.PurchaseHistoryDAO;
import com.internousdev.yataberyouhin.dto.CartInfoDTO;
import com.internousdev.yataberyouhin.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class PurchaseCompletionAction extends ActionSupport implements SessionAware{

	private ArrayList<CartInfoDTO> cartInfoDTO = new ArrayList<CartInfoDTO>();
	private  Map<String,Object> session;
	private  ProductInfoDTO productInfoDTO=new ProductInfoDTO();
	private String errMsg;



	public String execute() throws SQLException{
		String result = SUCCESS;
		CartInfoDAO cdao = new CartInfoDAO();
		PurchaseHistoryDAO pdao = new PurchaseHistoryDAO();
		ProductInfoDAO adao=new ProductInfoDAO();

		cartInfoDTO = cdao.aquireUserCartInfo(session.get("userId").toString());
		for (CartInfoDTO dto: cartInfoDTO) {

		    productInfoDTO=adao.selectByProductId(dto.getProductId());
		    if(!(productInfoDTO.getStock() - dto.getCount() < 0)) {
			    adao.updateStock(productInfoDTO.getProductId(),(productInfoDTO.getStock()-dto.getCount()));
				pdao.purchaseHistoryInfo(session.get("userId").toString(), dto.getProductId(),dto.getCount());
		    } else {
		    	errMsg = "在庫を超える数を購入しようとしました。";
		    	cdao.deleteProductFromCart(session.get("userId").toString(), dto.getProductId());
		    	result = ERROR;
		    }
		}
		cdao.deleteUserCartInfo(session.get("userId").toString());

		return result;

	}






	public Map<String,Object> getSession(){
		return session;
	}
	public void setSession(Map<String,Object> session){
		this.session=session;
	}






	public ProductInfoDTO getProductInfoDTO() {
		return productInfoDTO;
	}






	public void setProductInfoDTO(ProductInfoDTO productInfoDTO) {
		this.productInfoDTO = productInfoDTO;
	}






	public String getErrMsg() {
		return errMsg;
	}






	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}


	}
