package com.internousdev.yataberyouhin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.yataberyouhin.dto.ContactCompleteDTO;
import com.internousdev.yataberyouhin.util.DBConnector;

public class ContactCompleteDAO {
	List<ContactCompleteDTO> contactCompleteDTOList=new ArrayList<ContactCompleteDTO>();
	public List<ContactCompleteDTO> contactSelect(){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();

		String sql="select * from contact ";

		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				ContactCompleteDTO dto=new ContactCompleteDTO();
				dto.setName(rs.getString("name"));
				dto.setQtype(rs.getString("qtype"));
				dto.setContent(rs.getString("content"));
				contactCompleteDTOList.add(dto);

			}
		}catch(SQLException e){
			e.printStackTrace();
		}try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return contactCompleteDTOList;
	}


	public int contact(String name,String qtype,String content){
		int ret=0;
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();

		String sql="INSERT INTO contact VALUES(?,?,?) ";

		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, qtype);
			ps.setString(3, content);

			int i=ps.executeUpdate();
			if(i>0){
				System.out.println(i+"件登録しました。");
				ret=i;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return ret;
	}

}
