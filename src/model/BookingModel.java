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
	ArrayList<String> infoList;

	public BookingModel() {

		infoList = new ArrayList<String>();
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

	public ArrayList<String> getinfoByTel(String tel) {

		try {
			String sql = " SELECT m.TITLE title, b.MOVIEDATE moviedate, b.RUNTIME runtime, b.SEAT seat, p.SUMOF sumof, p.OPTIONOF optionof "
					+ " FROM movie m , Booking b , payment p "
					+ " where m.MOVIE_NO = b.MOVIE_NO and b.PAYNUM = p.PAYNUM " + " and b.tel=" + tel;
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			rs.next();
			infoList.add(rs.getString("title"));
			infoList.add(rs.getString("moviedate"));
			infoList.add(rs.getString("runtime"));
			infoList.add(rs.getString("seat"));
			infoList.add(rs.getString("sumof"));
			infoList.add(rs.getString("optionof"));

		} catch (Exception e) {
			System.out.println("예매내역 출력 실패" + e.getMessage());
			e.printStackTrace();
		}

		System.out.println(infoList.toString());
		return infoList;
		// 닫기

	}
}
