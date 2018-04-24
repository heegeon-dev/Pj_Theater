package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.TheaterDB;

public class MovieModel {

	String reserved;

	TheaterDB db;
	Connection con;
	ArrayList<String> infoList;

	public MovieModel() {
		// infoList = new ArrayList<String>();
		connectDB();

	}

	public void connectDB() {
		try {
			con = db.getConnection();
		} catch (Exception e) {
			System.out.println("DB연결 실패" + e.getMessage());
			e.printStackTrace();
		}
	}

	public String getReSeat(int RoomNum, String thisday, String movieRound) {
		try {
			// String sql = "Select selectednum from screen where
			// screenid='1#0424#3'";
			String sql = "Select selectednum from screen where screenid=?";
			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			String str = RoomNum + "#" + thisday + "#" + movieRound;
			ps.setString(1, str);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reserved = rs.getString("selectednum");
			}
		} catch (SQLException e) {
			System.out.println("예약좌석수 입력실패" + e.getMessage());
			e.printStackTrace();
		}
		if(reserved == null)
			reserved = "0";
		System.out.println(reserved);
		return reserved;

	}

}
