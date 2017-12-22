package com.internousdev.sukesyunshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.sukesyunshop.dto.CartDTO;
import com.internousdev.sukesyunshop.util.DBConnector;

public class CartDAO {

	private DBConnector db =  new DBConnector();
	private Connection con = db.getConnection();


//productdetailにcatalogで指定した値を送る
	public CartDTO getId(int productId){
		CartDTO getId=new CartDTO();
		String sql=""
				+"SELECT id"
				+"FROM product_info"
				+"where product_id=?";

		try{
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1,productId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				getId.setId(resultSet.getInt("id"));
			}
			} catch(Exception e){
				e.printStackTrace();
			}
			return getId;
	}


//カートの中身を指定
	public ArrayList<CartDTO> getCartList(String userId, boolean loginFlag){
		ArrayList<CartDTO> cartList = new ArrayList<CartDTO>();
		String sql;
		if(loginFlag){
			sql = ""
					+"SELECT *  "
					+"FROM cart_info "
					+ "INNER JOIN product_info "
					+ "ON cart_info.product_id = product_info.product_id "
					+"WHERE user_id=?";
		}else{
			sql = ""
					+"SELECT *  "
					+"FROM cart_info "
					+ "INNER JOIN product_info "
					+ "ON cart_info.product_id = product_info.product_id "
					+"WHERE temp_user_id=?";
		}
		try{
			PreparedStatement preparedStatement =  con.prepareStatement(sql);
			preparedStatement.setString(1,userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				CartDTO cartdto = new CartDTO();
				cartdto.setId(resultSet.getInt("id"));
				cartdto.setUserId(resultSet.getString("user_id"));
				cartdto.setProductId(resultSet.getInt("product_info.product_id"));
				cartdto.setProductNameKana(resultSet.getString("product_info.product_name_kana"));
				cartdto.setProductName(resultSet.getString("product_info.product_name"));
				cartdto.setCategoryId(resultSet.getInt("product_info.category_id"));
				cartdto.setPrice(resultSet.getInt("product_info.price"));
				cartdto.setImageFilePath(resultSet.getString("product_info.image_file_path"));
				cartdto.setImageFileName(resultSet.getString("product_info.image_file_name"));
				cartdto.setReleaseDate(resultSet.getString("product_info.release_date")); // データ型が？
				cartdto.setReleaseCompany(resultSet.getString("product_info.release_company"));
				cartList.add(cartdto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return cartList;
	}


//ログイン状態と仮ログイン状態の処理
	 public boolean addCart(String userId, int productId, boolean loginFlag){
		 String sql;
		if(loginFlag){
			sql=""
						+"INSERT INTO cart_info("
						+"user_id, product_id) "
						+ "VALUE( "
						+ "?,?)";
		}else{
			 sql=""
						+"INSERT INTO cart_info("
						+"temp_user_id, product_id) "
						+ "VALUE( "
						+ "?,?)";
		}
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1,userId);
			preparedStatement.setInt(2,productId);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	 }


//CartDeleteAction  カートの中身を削除
	 public boolean deleteCart(String userId, int productId, boolean loginFlag){
		 String sql;
		if(loginFlag){
			sql=""
					+"DELETE FROM cart_info "
					+"WHERE user_id = ? "
					+ "AND product_id = ?";
		}else{
			sql=""
					+"DELETE FROM cart_info "
					+"WHERE temp_user_id = ?  "
					+ "AND product_id = ?";
		}

			try {
				PreparedStatement preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1,userId);
				preparedStatement.setInt(2,productId);
				preparedStatement.executeUpdate();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		 }

}

