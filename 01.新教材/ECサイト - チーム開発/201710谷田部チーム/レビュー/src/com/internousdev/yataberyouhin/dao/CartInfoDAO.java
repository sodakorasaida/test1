package com.internousdev.yataberyouhin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.yataberyouhin.dto.CartInfoDTO;
import com.internousdev.yataberyouhin.util.DBConnector;
import com.opensymphony.xwork2.ActionSupport;


public class CartInfoDAO extends ActionSupport{

	DBConnector db = new DBConnector();
	Connection con = null;


	/**
	 * ログインユーザーの商品をカートに入れる
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public boolean putProductIntoCart(CartInfoDTO dto) throws SQLException {
		boolean result = false;
		int updateCount = 0;

		String sql = "INSERT INTO cart_info(user_id, product_id, count, insert_date) "
				+ 		"VALUES(?, ?, ?, NOW())";

		try {
			con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getUserId());
			ps.setInt(2, dto.getProductId());
			ps.setInt(3, dto.getCount());

			updateCount = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		if  (updateCount == 1) {
			result = true;
		}
		return result;
	}


	/**
	 * 非ログインユーザーの商品をカートに入れる
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public boolean putProductIntoCartOfGuestUser(CartInfoDTO dto) throws SQLException {
		boolean result = false;
		int updateCount = 0;

		String sql = "INSERT INTO cart_info(temp_user_id, product_id, count, insert_date) "
				+ 		"VALUES(?, ?, ?, NOW())";

		try {
			con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getTempUserId());
			ps.setInt(2, dto.getProductId());
			ps.setInt(3, dto.getCount());

			updateCount = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		if  (updateCount == 1) {
			result = true;
		}
		return result;
	}


	/**
	 * 指定したログインユーザーのカート情報をすべて引き出す
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<CartInfoDTO> showUserCartList(String userId) throws SQLException {
		ArrayList<CartInfoDTO> cartList = new ArrayList<>();
		String sql = "SELECT cart_info.product_id, pi.product_name, pi.product_name_kana, pi.image_file_path, pi.image_file_name, pi.price, pi.release_company, pi.release_date, count "
					+ "FROM cart_info LEFT JOIN product_info as pi ON cart_info.product_id = pi.product_id "
					+ "WHERE user_id = ?";

		try {
			con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CartInfoDTO dto = new CartInfoDTO();
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setPrice(rs.getInt("price"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setCount(rs.getInt("count"));
				cartList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return cartList;
	}


	/**
	 * 指定した非ログインユーザーのカート情報を引き出す
	 * @param tempUserId
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<CartInfoDTO> showTempUserCartList(String tempUserId) throws SQLException {
		ArrayList<CartInfoDTO> cartList = new ArrayList<>();
		String sql = "SELECT cart_info.product_id, cart_info.count, pi.product_name, pi.product_name_kana, pi.image_file_path, pi.image_file_name, pi.price, pi.release_company, pi.release_date "
					+ "FROM cart_info LEFT JOIN product_info as pi ON cart_info.product_id = pi.product_id "
					+ "WHERE temp_user_id = ?";

		try {
			con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tempUserId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CartInfoDTO dto = new CartInfoDTO();
				dto.setProductId(rs.getInt("product_id"));
				dto.setCount(rs.getInt("count"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setPrice(rs.getInt("price"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setReleaseDate(rs.getDate("release_date"));
				cartList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return cartList;
	}


	/**
	 * ログインユーザーのカートの情報を取得
	 * @param userId
	 * @return
	 * @throws SQLException
	 */

	public ArrayList<CartInfoDTO> aquireUserCartInfo(String userId) throws SQLException{
		Connection con=db.getConnection();
		ArrayList<CartInfoDTO> cartList = new ArrayList<>();
		String sql = "SELECT * FROM cart_info WHERE user_id = ?";

		try{
			PreparedStatement preparedStatement = con.prepareStatement(sql);
		    preparedStatement.setString(1, userId);

		    ResultSet rs = preparedStatement.executeQuery();

		    while (rs.next()) {
				CartInfoDTO dto = new CartInfoDTO();
				dto.setProductId(rs.getInt("product_id"));
		    	dto.setCount(rs.getInt("count"));
		    	cartList.add(dto);
		    }

		}catch(Exception e){
			e.printStackTrace();
		}try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}

		return cartList;
	}


	/**
	 * カートの情報を削除
	 * @param userId
	 */

	public void deleteUserCartInfo(String userId) {
		Connection con=db.getConnection();
		String sql = "DELETE FROM cart_info WHERE user_id = ?";

		try{
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, userId);

		preparedStatement.execute();

	}catch(Exception e){
		e.printStackTrace();
	}try{
		con.close();
	}catch(SQLException e){
		e.printStackTrace();
	}

	}

	/**
	 * カート内の商品を削除する
	 * @throws SQLException
	 */
	public boolean deleteProductFromCart(String userId, int productId) throws SQLException {
		boolean result = false;
		int deleteCount = 0;
		String sql = "DELETE FROM cart_info WHERE user_id = ? AND product_id = ?";

		try {
			con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, productId);
			deleteCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		if (deleteCount == 1) {
			result = true;
		}
		return result;
	}


	/**
	 * 一時ユーザーのカート内の商品を削除する
	 * @throws SQLException
	 */
	public boolean deleteProductFromTempUserCart(String tempUserId, int productId) throws SQLException {
		boolean result = false;
		int deleteCount = 0;
		String sql = "DELETE FROM cart_info WHERE temp_user_id = ? AND product_id = ?";

		try {
			con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tempUserId);
			ps.setInt(2, productId);
			deleteCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		if (deleteCount == 1) {
			result = true;
		}
		return result;
	}




	/**
	 * 非ログイン時のカート情報をログインしたユーザーに統合する（非推奨）
	 * @param tempUserId
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public int integrateCart(String tempUserId, String userId) throws SQLException {
		int updateCount = 0;
		String sql = "UPDATE cart_info SET user_id = ?, temp_user_id = null WHERE temp_user_id = ?";

		try {
			con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, tempUserId);

			updateCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return updateCount;
	}


	/**
	 * カートに目標の商品が入っているかどうか確認
	 * @param userId
	 * @param productId
	 * @return
	 * @throws SQLException
	 */
	public boolean isAlreadyIntoCart(String userId, int productId) throws SQLException {
		boolean result = false;
		String sql = "SELECT * FROM cart_info WHERE user_id = ? AND product_id = ?";

		try {
			con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, productId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return result;
	}



	/**
	 * ログインユーザーのカート内商品の購入予定個数を変更
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public int updateUsersCount(CartInfoDTO dto) throws SQLException {
		int updateCount = 0;
		String sql = "UPDATE cart_info SET count = ? WHERE user_id = ?";

		try {
			con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getCount());
			ps.setString(2, dto.getUserId());

			updateCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return updateCount;
	}



	/**
	 * 非ログインユーザーのカート内商品の購入予定個数を変更
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public int updateTempUsersCount(CartInfoDTO dto) throws SQLException {
		int updateCount = 0;
		String sql = "UPDATE cart_info SET count = ? WHERE temp_user_id = ?";

		try {
			con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getCount());
			ps.setString(2, dto.getTempUserId());

			updateCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return updateCount;
	}



}
