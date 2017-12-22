package com.internousdev.yataberyouhin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.yataberyouhin.dto.PurchaseHistoryInfoDTO;
import com.internousdev.yataberyouhin.util.DBConnector;

public class PurchaseHistoryDAO {
	private DBConnector db=new DBConnector();

	/**
	 * 購入履歴情報
	 * @param user_id
	 * @return
	 */

	public ArrayList<PurchaseHistoryInfoDTO> myPageInfo(String userId){
		ArrayList<PurchaseHistoryInfoDTO> purchaseHistorytDTO=new ArrayList<PurchaseHistoryInfoDTO>();
	    Connection con=db.getConnection();

		String sql="select pi.product_name,pi.product_name_kana,pi.price,pi.image_file_path,pi.image_file_name,pi.release_company,pi.release_date,phi.count "
				+ "from purchase_history_info as phi left join product_info as pi on phi.product_id=pi.product_id "
				+ "where user_id=? ";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,userId);

			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				PurchaseHistoryInfoDTO dto=new PurchaseHistoryInfoDTO();
				dto.setProductName(rs.getString("product_name"));
				dto.setProductNameKana(rs.getString("product_name_kana"));
				dto.setPrice(rs.getInt("price"));
				dto.setImageFilePath(rs.getString("image_file_path"));
				dto.setImageFileName(rs.getString("image_file_name"));
				dto.setReleaseDate(rs.getDate("release_date"));
				dto.setReleaseCompany(rs.getString("release_company"));
				dto.setCount(rs.getInt("count"));
			    purchaseHistorytDTO.add(dto);

			}

		}catch(SQLException e){
			e.printStackTrace();
		}
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return purchaseHistorytDTO;

	}

	/**
	 * 購入履歴にカートの情報を挿入
	 * @param userId
	 * @param productId
	 * @throws SQLException
	 */

		public void purchaseHistoryInfo(String userId,int productId,int count) throws SQLException{
			Connection con=db.getConnection();
			String sql = "INSERT INTO purchase_history_info(user_id,product_id,count,insert_date)  VALUES(?,?,?,NOW())";

			try{
				PreparedStatement preparedStatement = con.prepareStatement(sql);
			    preparedStatement.setString(1,userId);
			    preparedStatement.setInt(2, productId);
			    preparedStatement.setInt(3, count);

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
	 * 商品履歴削除
	 *
	 * @return
	 * @throws SQLException
	 */
	public int deleteInfo(String userId) throws SQLException {
		Connection con=db.getConnection();

		String sql = "delete from purchase_history_info where user_id=?";
		PreparedStatement ps;
		int result =0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,userId);

			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}

		return result;
	}
}



