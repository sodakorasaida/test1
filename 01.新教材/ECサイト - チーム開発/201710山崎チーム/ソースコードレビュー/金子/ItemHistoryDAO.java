package com.internousdev.sukesyunshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.sukesyunshop.dto.ItemHistoryDTO;
import com.internousdev.sukesyunshop.util.DBConnector;


public class ItemHistoryDAO {
	//ログイン中かどうかの判定をする
//	public boolean isLogin(String userId) throws SQLException{
//		DBConnector db = new DBConnector();
//		Connection connection = db.getConnection();
//
//		String sql=""
//				+ "SELECT logined "
//				+ "FROM user_info"
//				+ "WHERE user_id =?";
//		PreparedStatement statement = connection.prepareStatement(sql);
//		statement.setString(1,userId);
//
//		ResultSet resultSet = statement.executeQuery();
//
//		while(resultSet.next()){
//
//		}
//	}
	public void deleteItemHistory (String userId) throws SQLException{
		DBConnector db =new DBConnector();
		Connection connection = db.getConnection();

		String sql="DELETE FROM purchase_history_info "
				+ "Where user_id=?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1,userId);

		statement.executeUpdate(sql);

	}



	//商品情報を購入履歴テーブルに持ってくる

	public ArrayList<ItemHistoryDTO> getItemHistoryList(String userId) throws SQLException{
		DBConnector db = new DBConnector();
		Connection connection = db.getConnection();

		ArrayList <ItemHistoryDTO> itemHistoryList =new ArrayList<ItemHistoryDTO>();

		String sql = ""
				+ "SELECT * FROM purchase_history_info "
				+ "INNER JOIN product_info "
				+ "ON purchase_history_info.product_id = product_info.product_id "
				+ "WHERE user_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1,userId);

		System.out.println("kkkkk");

		ResultSet rs= statement.executeQuery();

		System.out.println("kkkss");
		while(rs.next()){
			ItemHistoryDTO dto = new ItemHistoryDTO();
			dto.setUserId(rs.getString("user_id"));
			System.out.println(rs.getString("user_id")+"ooo");
			dto.setProductId(rs.getInt("product_id"));
			dto.setProductName(rs.getString("product_name"));
			dto.setProductNameKana(rs.getString("product_name_kana"));
			dto.setImageFilePath(rs.getString("image_file_path"));
			dto.setPrice(rs.getInt("price"));
			dto.setReleaseCompany(rs.getString("release_company"));
			dto.setReleaseDate(rs.getString("release_date"));

			itemHistoryList.add(dto);

		}
		return itemHistoryList;
	}
}