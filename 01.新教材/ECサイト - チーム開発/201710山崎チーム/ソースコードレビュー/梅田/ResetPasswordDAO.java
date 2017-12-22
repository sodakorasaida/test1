package com.internousdev.sukesyunshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.sukesyunshop.dto.ResetPasswordDTO;
import com.internousdev.sukesyunshop.util.DBConnector;

public class ResetPasswordDAO {


	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();

	private ResetPasswordDTO resetPasswordDTO=new ResetPasswordDTO();


	/*ログインIDをDBから探すメソッド*/
	public boolean getLoginId(String loginId){

	String sql="SELECT * FROM user_info where user_id=?";

		try{
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);

			ResultSet resultSet=preparedStatement.executeQuery();

		if(resultSet.next()){//元々は return resultSet.next();どっちがいいかは謎
			resetPasswordDTO.setLoginId(resultSet.getString("user_id"));
			return true;
		}

		}catch(Exception e){
			e.printStackTrace();
		}
			return false;
		}

	/*ユーザーIDを元にして新しいパスワードをDBにセットするメソッド*/
	public boolean updatePassword(String loginPassword,String loginId){

	String sql="UPDATE user_info SET password =? where user_id= ?";

		try{
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, loginPassword);
			preparedStatement.setString(2,loginId);

			preparedStatement.executeUpdate();
			return true;

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public ResetPasswordDTO getResetPasswordDTO() {
		return resetPasswordDTO;
		}
}

