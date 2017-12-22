package com.internousdev.yataberyouhin.action;

import java.util.ArrayList;
import java.util.List;

import com.internousdev.yataberyouhin.dao.ContactCompleteDAO;
import com.internousdev.yataberyouhin.dto.ContactCompleteDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ContactCompleteAction extends ActionSupport{
	private String name;
	private String qtype;
	private String content;

	List<ContactCompleteDTO> contactCompleteDTOList=new ArrayList<ContactCompleteDTO>();

	public String execute(){
		String ret=SUCCESS;
	     ContactCompleteDAO dao=new ContactCompleteDAO();


		int count=dao.contact(name, qtype, content);
		if(count>0){
			contactCompleteDTOList=dao.contactSelect();

		}
		return ret;

}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQtype() {
		return qtype;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<ContactCompleteDTO> getContactCompleteDTOList() {
		return contactCompleteDTOList;
	}

	public void setContactCompleteDTOList(List<ContactCompleteDTO> contactCompleteDTOList) {
		this.contactCompleteDTOList = contactCompleteDTOList;
	}
}
