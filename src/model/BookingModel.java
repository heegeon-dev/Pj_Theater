package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import db.TheaterDB;

public class BookingModel {

	TheaterDB db;
	Connection con;

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

	public ArrayList<String> getinfoByTel(String tel) {
		ArrayList<String> infoList= new ArrayList<String>();
		try {

			String sql = " SELECT m.TITLE title, b.MOVIEDATE MOVIEDATE, m.RUNINGTIME RUNINGTIME, "
					+ " b.RUNTIME RUNTIME, s.SCREENNO SCREENNO, b.SEAT SEAT, p.SUMOF SUMOF, "
					+ " pt.POINT POINT, p.OPTIONOF OPTIONOF, b.people people "
					+ " FROM movie m, booking b , screen s , payment p ,point pt "
					+ " WHERE b.MOVIE_NO = m.MOVIE_NO and " + " b.PAYNUM = p.PAYNUM and  b.SCREENID = s.SCREENID and "
					+ " b.TEL = pt.TEL and   b.tel = ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tel);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				infoList.add(rs.getString("title"));// 0
				infoList.add(rs.getString("MOVIEDATE"));// 1
				infoList.add(rs.getString("RUNINGTIME"));// 2 _120
				infoList.add(rs.getString("RUNTIME"));// 3
				infoList.add(rs.getString("SCREENNO"));// 4
				infoList.add(rs.getString("SEAT"));// 5
				infoList.add(rs.getString("SUMOF"));// 6
				infoList.add(rs.getString("POINT"));// 7
				infoList.add(rs.getString("OPTIONOF")); // 8
				infoList.add(rs.getString("people"));// 9

			}
		} catch (Exception e) {
			System.out.println("예매내역 출력 실패" + e.getMessage());
			e.printStackTrace();
		}

		System.out.println(infoList.toString());
		return infoList;
		// 닫기

	}
}
