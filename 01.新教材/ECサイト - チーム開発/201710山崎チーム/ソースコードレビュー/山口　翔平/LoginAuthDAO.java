package com.internousdev.sukesyunshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.sukesyunshop.dto.UserInfoDTO;
import com.internousdev.sukesyunshop.util.DBConnector;

public class LoginAuthDAO {

	private DBConnector connector = new DBConnector();
	private Connection connection = connector.getConnection();


	//引き数のIDがログイン中か否かの判定する
	public boolean isLogin(String userId) throws SQLException{

		String sql = ""
				+ "SELECT logined "
				+ "FROM user_info "
				+ "WHERE user_id = ?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, userId);

		ResultSet resultSet = statement.executeQuery();

		return resultSet.next();
	}

	//引き数のuserIdとpasswordのセットが存在しているか判定する
	public boolean checkIdPass(String userId, String password) throws SQLException{

		String sql = ""
				+ "SELECT logined "
				+ "FROM user_info "
				+ "WHERE user_id = ? "
				+ "AND password = ? ";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, userId);
		statement.setString(2, password);

		ResultSet resultSet = statement.executeQuery();

		return resultSet.next();

	}

	//ユーザー情報を取得する
	public UserInfoDTO getUserInfo(String userId, String password){
		UserInfoDTO dto = new UserInfoDTO();

		String sql = ""
				+ "SELECT * "
				+ "FROM user_info "
				+ "WHERE user_id = ? "
				+ "AND password = ? ";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userId);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				dto.setUserId(userId);
				dto.setPassword(password);
				dto.setFamilyName(resultSet.getString("family_name"));
				dto.setFirstName(resultSet.getString("first_name"));
				dto.setFamilyNameKana(resultSet.getString("family_name_kana"));
				dto.setFirstNameKana(resultSet.getString("first_name_kana"));
				dto.setSex(resultSet.getInt("sex"));
				dto.setEmail(resultSet.getString("email"));
				dto.setStatus(resultSet.getInt("status"));
				dto.setLogined(resultSet.getInt("logined"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			dto = null;
		}

		return dto;
	}

	//引き数のuserIdとpasswordのアカウントをログイン状態にする。
	public boolean login(String userId, String password) throws SQLException{

		if(!checkIdPass(userId, password)){
			return false;
		}

		String sql = ""
				+ "UPDATE user_info "
				+ "SET logined = 1 "
				+ "WHERE user_id = ? "
				+ "AND password = ? ";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, userId);
		statement.setString(2, password);

		statement.executeUpdate();

		return true;
	}

	//引き数のuserIdとpasswordのアカウントをログアウト状態にする。
	public boolean logout(String userId, String password){

		String sql = ""
				+ "UPDATE user_info "
				+ "SET logined = 0 "
				+ "WHERE user_id = ? "
				+ "AND password = ? ";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userId);
			statement.setString(2, password);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
