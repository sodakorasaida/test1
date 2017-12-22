package com.internousdev.yataberyouhin.action;

import java.util.ArrayList;

import com.internousdev.yataberyouhin.dao.ProductInfoDAO;
import com.internousdev.yataberyouhin.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductInfoAction extends ActionSupport {

	private ArrayList<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
	private ProductInfoDAO dao = new ProductInfoDAO();

	public String execute() {

		String ret = ERROR;
		productInfoDTOList = dao.selectAll();
		if (productInfoDTOList.size() > 0) {


			ret = SUCCESS;
		} else {
			ret = ERROR;
		}
		return ret;
	}

	public ArrayList<ProductInfoDTO> getProductInfoDTOList() {
		return productInfoDTOList;
	}

	public void setProductInfoDTOList(ArrayList<ProductInfoDTO> productInfoDTOList) {
		this.productInfoDTOList = productInfoDTOList;
	}



}
