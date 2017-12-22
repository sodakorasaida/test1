package com.internousdev.yataberyouhin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.yataberyouhin.dto.ProductInfoDTO;
import com.internousdev.yataberyouhin.util.DBConnector;

public class ProductInfoDAO {
	public ArrayList<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();

	/**
	 * Product_InfoDTOの中身を全て選択しているメソッド
	 *
	 * @return
	 */
	public ArrayList<ProductInfoDTO> selectAll() {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "SELECT * FROM product_info";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductInfoDTO dto = new ProductInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setStock(rs.getInt("stock"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setStatus(rs.getShort("status"));
				dto.setInsertDate(rs.getDate("insert_date"));
				dto.setUpdateDate(rs.getDate("update_date"));
				productInfoDTOList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productInfoDTOList;
	}

	/**
	 * ProductIdに対応したDTOの中身を引っ張ってくるメソッド
	 *
	 * @param productId
	 * @return
	 */
	public ProductInfoDTO selectByProductId(int productId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ProductInfoDTO dto = new ProductInfoDTO();

		String sql = "SELECT * FROM product_info WHERE product_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, productId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				dto = new ProductInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setStock(rs.getInt("stock"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setStatus(rs.getShort("status"));
				dto.setInsertDate(rs.getDate("insert_date"));
				dto.setUpdateDate(rs.getDate("update_date"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	/**
	 * CategoryIdに対応したDTOの中身を引っ張ってくるメソッド
	 *
	 * @param productId
	 * @return
	 */
	public ArrayList<ProductInfoDTO> selectByCategoryId(int categoryId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ProductInfoDTO dto = new ProductInfoDTO();

		String sql = "SELECT *  FROM product_info WHERE category_id= ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, categoryId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				dto = new ProductInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setStatus(rs.getShort("status"));
				dto.setInsertDate(rs.getDate("insert_date"));
				dto.setUpdateDate(rs.getDate("update_date"));
				productInfoDTOList.add(dto);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productInfoDTOList;
	}

	public ArrayList<ProductInfoDTO> selectByKeyWord(String keyword) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ProductInfoDTO dto = new ProductInfoDTO();

		String sql = "SELECT *  FROM product_info WHERE product_name LIKE '%" + keyword
				+ "%' OR product_name_kana LIKE '%" + keyword + "%'";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				dto = new ProductInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setStatus(rs.getShort("status"));
				dto.setInsertDate(rs.getDate("insert_date"));
				dto.setUpdateDate(rs.getDate("update_date"));
				productInfoDTOList.add(dto);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productInfoDTOList;
	}

	public ArrayList<ProductInfoDTO> selectByKeyWords(String[] keywords) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ProductInfoDTO dto = new ProductInfoDTO();

		String sql = "SELECT *  FROM product_info WHERE ";
		for (int k = 0; k < keywords.length; k++) {
			if (k != 0) {
				sql = sql + " AND (product_name LIKE '%" + keywords[k] + "%' OR product_name_kana LIKE '%" + keywords[k]+ "%') ";
			} else {
				sql = sql + " (product_name LIKE '%" + keywords[k] + "%' OR product_name_kana LIKE '%" + keywords[k]+ "%') ";
			}
		}

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				dto = new ProductInfoDTO();
				dto.setId(rs.getInt("id"));
				dto.setProductId(rs.getInt("product_id"));
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setProductDescription(rs.getString("product_description"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setStatus(rs.getShort("status"));
				dto.setInsertDate(rs.getDate("insert_date"));
				dto.setUpdateDate(rs.getDate("update_date"));
				productInfoDTOList.add(dto);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productInfoDTOList;
	}
	/**
	 * 在庫の数を変更するメソッド
	 * @throws SQLException
	 */
	public int updateStock(int productId,int stock) throws SQLException{
		int ret=0;
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql="UPDATE product_info SET stock=? WHERE product_id=? ";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, stock);
			ps.setInt(2, productId);
			ret=ps.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return ret;

	}

}
