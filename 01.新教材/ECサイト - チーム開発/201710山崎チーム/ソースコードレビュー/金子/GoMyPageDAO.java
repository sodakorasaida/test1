package com.internousdev.sukesyunshop.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.sukesyunshop.dto.UserInfoDTO;
import com.internousdev.sukesyunshop.util.DBConnector;

public class GoMyPageDAO {
	private DBConnector dbConnector = new DBConnector();

	private Connection connection = dbConnector.getConnection();
	public UserInfoDTO GoMyPageList(String userId) throws SQLException{
	//	DBConnector db = new DBConnector();
	//	Connection connection = db.getConnection();

		UserInfoDTO dto =new UserInfoDTO();
		String sql = "SELECT * FROM user_info WHERE user_id = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, userId);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				dto.setUserId(rs.getString("user_id"));
				dto.setPassword(rs.getString("password"));
				dto.setFamilyName(rs.getString("family_name"));
				dto.setFirstName(rs.getString("first_name"));
				dto.setFamilyNameKana(rs.getString("family_name_kana"));
				dto.setFirstNameKana(rs.getString("first_name_kana"));
				dto.setSex(rs.getInt("sex"));
			    dto.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
}