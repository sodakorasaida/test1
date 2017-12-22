package com.internousdev.sukesyunshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.sukesyunshop.dto.CatalogDTO;
import com.internousdev.sukesyunshop.dto.CategoryDTO;
import com.internousdev.sukesyunshop.util.DBConnector;

public class SearchDAO {

	private DBConnector connector = new DBConnector();
	private Connection connection = connector.getConnection();

	public ArrayList<CatalogDTO> searchCatalog(int category, String searchText) throws SQLException{
		ArrayList<CatalogDTO> list = new ArrayList<>();

		String sql;
		PreparedStatement statement = null;
		String wc = "%";

		if(category == 0 && searchText.equals("")){
			sql = ""
				+ "SELECT * "
				+ "FROM product_info ";
		}else if(category == 0){
			sql = ""
					+ "SELECT * "
					+ "FROM product_info "
					+ "WHERE ( product_name LIKE ? "
					+ "OR product_name_kana LIKE ? ) ";
			statement = connection.prepareStatement(sql);
			statement.setString(1, wc+searchText+wc);
			statement.setString(2, wc+searchText+wc);
		}else if(searchText.equals("")){
			sql = ""
					+ "SELECT * "
					+ "FROM product_info "
					+ "WHERE category_id = ? ";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, category);
		}else{
			sql = ""
					+ "SELECT * "
					+ "FROM product_info "
					+ "WHERE category_id = ? "
					+ "AND ( product_name LIKE ? "
					+ "OR product_name_kana LIKE ? ) ";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, category);
			statement.setString(2, wc+searchText+wc);
			statement.setString(3, wc+searchText+wc);
		}

		ResultSet resultSet = statement.executeQuery();

		while(resultSet.next()){
			CatalogDTO dto = new CatalogDTO();
			dto.setId(resultSet.getInt("id"));
			dto.setCategoryId(resultSet.getInt("category_id"));
			dto.setProductId(resultSet.getInt("product_id"));
			dto.setProductName(resultSet.getString("product_name"));
			dto.setProductNameKana(resultSet.getString("product_name_kana"));
			dto.setImageFilePath(resultSet.getString("image_file_path"));
			dto.setImageFileName(resultSet.getString("image_file_name"));
			dto.setPrice(resultSet.getInt("price"));
			dto.setReleaseDate (resultSet.getString("release_date"));
			dto.setReleaseCompany(resultSet.getString("release_company"));
			list.add(dto);
		}

		return list;

	}


	public ArrayList<CategoryDTO> getCategory() throws SQLException{
		ArrayList<CategoryDTO> list = new ArrayList<>();
		String sql = ""
				+ "SELECT * "
				+ "FROM m_category ";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()){
			CategoryDTO dto = new CategoryDTO();
			dto.setCategoryId(resultSet.getInt("category_id"));
			dto.setCategoryName(resultSet.getString("category_name"));
			dto.setCategoryDescription(resultSet.getString("category_description"));
			list.add(dto);
		}
		return list;
	}
}
