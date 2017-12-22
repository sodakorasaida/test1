package com.internousdev.yataberyouhin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.yataberyouhin.dto.MCategoryDTO;

import com.internousdev.yataberyouhin.util.DBConnector;

public class MCategoryDAO {
	public ArrayList<MCategoryDTO> mCategoryDTOList=new ArrayList<MCategoryDTO>();
	public ArrayList<MCategoryDTO> selectAll(){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();

		String sql="SELECT * FROM m_category";


		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				MCategoryDTO dto = new MCategoryDTO();
				dto.setId(rs.getInt("id"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setCategoryName(rs.getString("category_name"));
				dto.setCategoryDescription(rs.getString("category_description"));
				dto.setInsertDate(rs.getDate("insert_date"));
				dto.setUpdateDate(rs.getDate("update_date"));
				mCategoryDTOList.add(dto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mCategoryDTOList;
	}


}
