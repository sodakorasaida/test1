package com.internousdev.yataberyouhin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.yataberyouhin.dto.UserInfoDTO;
import com.internousdev.yataberyouhin.util.DBConnector;


public class LoginDAO {
	public UserInfoDTO select(String userId,String password) /*throws SQLException*/ {
		UserInfoDTO dto = new UserInfoDTO();
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "select * from user_info where user_id=? and password=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				dto.setUserId(rs.getString("user_id"));
				dto.setPassword(rs.getString("password"));

			}
		} catch (SQLException e){
			e.printStackTrace();
		} /*finally {
			con.close();
		}*/
		return dto;
	}

	public boolean login(UserInfoDTO dto) throws SQLException {
		boolean result = false;
		int updateCount = 0;
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "UPDATE user_info SET logined=1 WHERE user_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getUserId());
			updateCount = ps.executeUpdate();
			if(updateCount > 0) {
				System.out.println("ログイン済み");
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return result;
	}

	public boolean logout(UserInfoDTO dto) throws SQLException {
		boolean result = false;
		int updateCount = 0;
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "UPDATE user_info SET logined=0 WHERE user_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getUserId());
			updateCount = ps.executeUpdate();
			if(updateCount > 0) {
				System.out.println("未ログイン");
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return result;
	}

}

