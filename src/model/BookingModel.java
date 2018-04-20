package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.TheaterDB;

public class BookingModel {

	TheaterDB db;
	Connection con;
	ArrayList infoList;

	public BookingModel() {

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

	public ArrayList getinfoByTel(String tel) {

		try {
			String sql = "SELECT m.TITLE, b.MOVIEDATE, b.RUNTIME, b.SEAT, p.SUMOF, p.OPTIONOF "
					+ " FROM movie m, Booking b, payment p " + " where m.MOVIE_NO=b.MOVIE_NO and b.PAYNUM=p.PAYNUM "
					+ "and where b.tel=?";
			System.out.println(sql);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(0, tel);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				infoList.add(rs.getString("TITLE"));
				infoList.add(rs.getDate("MOVIEDATE"));
				infoList.add(rs.getString("RUNTIME"));
				infoList.add(rs.getString("SEAT"));
				infoList.add(rs.getInt("SUMOF"));
				infoList.add(rs.getString("OPTIONOF"));
			}

		} catch (Exception e) {
			System.out.println("예매내역 출력 실패" + e.getMessage());
			e.printStackTrace();
		}
		return infoList;
		// 닫기

	}
}
