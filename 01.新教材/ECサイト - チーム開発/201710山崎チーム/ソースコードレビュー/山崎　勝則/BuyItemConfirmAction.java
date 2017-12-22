package com.internousdev.sukesyunshop.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sukesyunshop.dao.CartDAO;
import com.internousdev.sukesyunshop.dao.DestinationDAO;
import com.internousdev.sukesyunshop.dao.TempBuyItemConfirmDAPO;
import com.internousdev.sukesyunshop.dto.CartDTO;
import com.internousdev.sukesyunshop.dto.DestinationDTO;
import com.internousdev.sukesyunshop.util.SessionName;
import com.opensymphony.xwork2.ActionSupport;

public class BuyItemConfirmAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private List<CartDTO> cartList = new ArrayList<CartDTO>();
	private CartDAO cartDAO = new CartDAO();

	private DestinationDAO destDAO = new DestinationDAO();
	private List<DestinationDTO> destDTOList = new ArrayList<DestinationDTO>();

	public String execute() {

		boolean loginFlag = session.get(SessionName.getLoginFlag()).equals(SessionName.getTrue());

		if (loginFlag) {
			String userId = session.get(SessionName.getUserId()).toString();
			String tempUserId = session.get(SessionName.getTempUserId()).toString();
			TempBuyItemConfirmDAPO dao = new TempBuyItemConfirmDAPO();
			try {
				dao.convertId(userId, tempUserId);
				cartList = cartDAO.getCartList(userId, true);
				destDTOList = destDAO.destSelect(userId);
			} catch (SQLException e) {
				e.printStackTrace();
				return ERROR;
			}

			return SUCCESS;
		}

		session.put(SessionName.getBuying(), SessionName.getTrue());
		return "login";
	}

	public List<CartDTO> getCartList() {
		return cartList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public List<DestinationDTO> getDestDTOList() {
		return destDTOList;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
