package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.TheaterDB;

public class LoginModel {
	Connection con;
	ArrayList<String> infoList;
	
	public LoginModel() throws Exception{
		con = TheaterDB.getConnection();
		infoList = new ArrayList<String>();
	}
	
	public ArrayList<String> login() throws Exception{
		String sql = "SELECT id, pw FROM login";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			infoList.add(rs.getString("id"));
			infoList.add(rs.getString("pw"));
		}
		System.out.println(infoList.toString() + "출력");
//		rs.close();
//		ps.close();
		
		return infoList;
	}
}
