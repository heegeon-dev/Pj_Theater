package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.TheaterDB;

public class PayModel {
	Connection con;
	
	public PayModel(){
		try {
			con=TheaterDB.getConnection();
		} catch (Exception e){
			System.out.println("PayModel DB연결 실패");
			e.printStackTrace();
		}
		
	}

	public int getOfPoint(String tel) throws SQLException {
		String sql = "select point from point where" + tel ;
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return rs.getInt("point");
	}

}
