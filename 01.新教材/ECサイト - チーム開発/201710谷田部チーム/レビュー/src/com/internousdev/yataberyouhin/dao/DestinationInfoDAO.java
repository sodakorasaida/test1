package com.internousdev.yataberyouhin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.yataberyouhin.dto.DestinationInfoDTO;
import com.internousdev.yataberyouhin.util.DBConnector;

public class DestinationInfoDAO {

	DBConnector db = new DBConnector();
	Connection con = null;

	/**
	 * 宛先情報をDBに登録する
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public boolean registerDestination(DestinationInfoDTO dto) throws SQLException {

		int updateCount = 0;
		boolean result = false;
		String sql = "INSERT INTO destination_info(user_id, "
												    + "family_name, "
												    + "first_name, "
												    + "family_name_kana, "
												    + "first_name_kana, "
												    + "email, "
												    + "tel_number, "
												    + "user_address, "
												    + "insert_date) "
					 + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, NOW())";

		try {
			con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getUserId());
			ps.setString(2, dto.getFamilyName());
			ps.setString(3, dto.getFirstName());
			ps.setString(4, dto.getFamilyNameKana());
			ps.setString(5, dto.getFirstNameKana());
			ps.setString(6, dto.getEmail());
			ps.setString(7, dto.getTelNumber());
			ps.setString(8, dto.getUserAddress());
			updateCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		if (updateCount == 1) {
			result = true;
		}
		return result;
	}


	/**
	 * 指定したユーザーの宛先情報取得
	 * @throws SQLException
	 */
	public ArrayList<DestinationInfoDTO> obtainingDestinationInfo(String userId) throws SQLException{
		ArrayList<DestinationInfoDTO> destinationList = new ArrayList<DestinationInfoDTO>();

		String sql="SELECT id, family_name, first_name, family_name_kana, first_name_kana, user_address, tel_number, email FROM destination_info WHERE user_id = ?";

		try{
			con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				DestinationInfoDTO dto = new DestinationInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setFamilyName(rs.getString("family_name"));
				dto.setFirstName(rs.getString("first_name"));
				dto.setFamilyNameKana(rs.getString("family_name_kana"));
				dto.setFirstNameKana(rs.getString("first_name_kana"));
				dto.setUserAddress(rs.getString("user_address"));
				dto.setTelNumber(rs.getString("tel_number"));
				dto.setEmail(rs.getString("email"));
				destinationList.add(dto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			con.close();
		}

		return destinationList;
	}

}
