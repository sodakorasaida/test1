package com.internousdev.yataberyouhin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.yataberyouhin.dto.UserInfoDTO;
import com.internousdev.yataberyouhin.util.DBConnector;

public class UserInfoDAO {

	DBConnector db = new DBConnector();
	Connection con = null;


	/**
	 * ユーザー情報を新規登録する
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public boolean registerUser(UserInfoDTO dto) throws SQLException {
		boolean result = false;
		int updateCount = 0;

		String sql = "INSERT INTO user_info(user_id, password, family_name, first_name, family_name_kana, first_name_kana, sex, email, insert_date) "
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, NOW())";

		try {
			con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getUserId());
			ps.setString(2, dto.getPassword());
			ps.setString(3, dto.getFamilyName());
			ps.setString(4, dto.getFirstName());
			ps.setString(5, dto.getFamilyNameKana());
			ps.setString(6, dto.getFirstNameKana());
			ps.setBoolean(7, dto.isSex());
			ps.setString(8, dto.getEmail());

		updateCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		if(updateCount == 1) {
			result = true;
		}

		return result;
	}


	/**
	 * 指定したユーザーのパスワードを変更する
	 * @param password
	 * @param userId
	 * @return
	 */
	public int  update(String password,String userId) {
		int ret = 0;
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "UPDATE user_info SET password = ? WHERE user_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, userId);
			int i = ps.executeUpdate();
			if(i > 0) {
				System.out.println(i + "件変更されました。");
				ret = i;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;

	}



	/**
	 * ログインを試みる
	 * @param password
	 * @param userId
	 * @return
	 */
	public List<UserInfoDTO> select(String password,String userId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "SELECT * FROM user_info WHERE password = ? AND user_id = ?";
		List<UserInfoDTO> userInfoDTOList = new ArrayList<UserInfoDTO>();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, userId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				UserInfoDTO dto = new UserInfoDTO();
				dto.setPassword(rs.getString("password"));
				dto.setUserId(rs.getString("userId"));
				userInfoDTOList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfoDTOList;
	}


	/**
	 * 必要なユーザー情報を引き出している
	 * @param userId
	 * @return
	 */
	public ArrayList<UserInfoDTO> userInfo(String userId){
		ArrayList<UserInfoDTO> userInfoDTO=new ArrayList<UserInfoDTO>();
	    Connection con=db.getConnection();

		String sql="SELECT family_name,first_name,family_name_kana,first_name_kana,sex,email FROM user_info WHERE user_id = ?";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,userId);

			ResultSet rs=ps.executeQuery();

			if(rs.next()){
				UserInfoDTO dto=new UserInfoDTO();
				dto.setFamilyName(rs.getString("family_name"));
				dto.setFirstName(rs.getString("first_name"));
				dto.setFamilyNameKana(rs.getString("family_name_kana"));
				dto.setFirstNameKana(rs.getString("first_name_kana"));
				dto.setSex(rs.getBoolean("sex"));
				dto.setEmail(rs.getString("email"));
				userInfoDTO.add(dto);


			}

		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return userInfoDTO;

	}


	public boolean InsertGuestUser(String userId) throws SQLException {
		boolean result = false;
		int updateCount = 0;
		String sql = "INSERT INTO user_info(user_id, password, family_name, first_name, family_name_kana, first_name_kana, sex, email, insert_date) "
					  + "VALUES(?, 'guest', 'ゲスト', 'ゲスト', 'げすと', 'げすと', 0, 'guest@guest.com', NOW())";

		try {
			con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);

			updateCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		if(updateCount == 1) {
			result = true;
		}

		return result;
	}
}

