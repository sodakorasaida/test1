package com.internousdev.sukesyunshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.sukesyunshop.dto.CatalogDTO;
import com.internousdev.sukesyunshop.util.DBConnector;

public class CatalogDAO {

	private DBConnector dbConnector = new DBConnector();
	private  Connection connection = dbConnector.getConnection();



	public CatalogDTO getItem(int productId){
		CatalogDTO getItem=new CatalogDTO();
		String sql=""
				+ "SELECT  id, category_id,product_id, product_name,product_name_kana,image_file_path,image_file_name, price,release_date,release_company "
				+ " FROM product_info "
				+ "where product_id=?";

			try{
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, productId);
				ResultSet resultSet = preparedStatement.executeQuery();

				if(resultSet.next()){
					getItem.setId(resultSet.getInt("id"));
					getItem.setCategoryId(resultSet.getInt("category_id"));
					getItem.setProductId(resultSet.getInt("product_id"));
					getItem.setProductName(resultSet.getString("product_name"));
					getItem.setProductNameKana(resultSet.getString("product_name_kana"));
					getItem.setImageFilePath(resultSet.getString("image_file_path"));
					getItem.setImageFileName(resultSet.getString("image_file_name"));
					getItem.setPrice(resultSet.getInt("price"));
					getItem.setReleaseDate (resultSet.getString("release_date"));
					getItem.setReleaseCompany(resultSet.getString("release_company"));
					System.out.println(getItem.getProductName());
				}
			} catch(Exception e){
				e.printStackTrace();
			}
				return getItem;
	}


//商品一覧の中身,productail,cart,seachに値を渡している
	public ArrayList<CatalogDTO> getCatalogList() throws SQLException{
		ArrayList<CatalogDTO> list = new ArrayList<CatalogDTO>();
		String sql = ""
				+"SELECT  id, category_id,product_id, product_name,product_name_kana, image_file_path,image_file_name,price "
				+"FROM product_info  ";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		while(resultSet.next()){
			CatalogDTO catalogdto = new CatalogDTO();
			catalogdto.setId(resultSet.getInt("id"));
			catalogdto.setCategoryId(resultSet.getInt("category_id"));
			catalogdto.setProductId(resultSet.getInt("product_id"));
			catalogdto.setProductName(resultSet.getString("product_name"));
			catalogdto.setProductNameKana(resultSet.getString("product_name_kana"));
			catalogdto.setImageFilePath(resultSet.getString("image_file_path"));
			catalogdto.setImageFileName(resultSet.getString("image_file_name"));
			catalogdto.setPrice(resultSet.getInt("price"));
			list.add(catalogdto);
		}
		return list;
	}


//商品詳細にて同じカテゴリーのアイテム表示
	public ArrayList<CatalogDTO> miniList(int categoryId) throws SQLException{
		ArrayList<CatalogDTO> miniList = new ArrayList<CatalogDTO>();
		String sql = ""
				+ "SELECT id, category_id, product_id, product_name, product_name_kana, image_file_path "
				+ "FROM product_info "
				+ "WHERE category_id=? "
				+ "LIMIT 4 ";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, categoryId);
		ResultSet resultSet = preparedStatement.executeQuery();

		while(resultSet.next()){
			CatalogDTO sub = new CatalogDTO();
			sub.setId(resultSet.getInt("id"));
			sub.setCategoryId(resultSet.getInt("category_id"));
			sub.setProductId(resultSet.getInt("product_id"));
			sub.setProductName(resultSet.getString("product_name"));
			sub.setProductNameKana(resultSet.getString("product_name_kana"));
			sub.setImageFilePath(resultSet.getString("image_file_path"));

			miniList.add(sub);
		}
		return miniList;
	}


}

