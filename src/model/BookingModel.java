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
			SimpleDateFormat format3 = new SimpleDateFormat("HH:mm", Locale.KOREA);
			Calendar c = Calendar.getInstance();

			String sql = " select m.TITLE TITLE, s.MOVIEDATE MOVIEDATE, "
					+ " b.RUNTIME RUNTIME, b.SEAT SEAT, p.SUMOF SUMOF, p.OPTIONOF OPTIONOF "
					+ " from movie m, booking b, screen s, payment p "
					+ " where m.movie_no = b.movie_no and b.screenid = s.screenid and " + " b.paynum = p.paynum "
					+ " and tel = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tel);

			ResultSet rs = ps.executeQuery();

			rs.next();
			infoList.add(rs.getString("TITLE"));
			infoList.add(rs.getString("MOVIEDATE"));
			String start = rs.getString("RUNTIME");

			System.out.println(start);// 21:30
			System.out.println();
			format3.parse(start);
			c.setTime(format3.parse(start));
			c.add(Calendar.HOUR_OF_DAY, 2);
			String end = format3.format(c.getTime());
			
			String runtime = start +" ~ " + end;
			infoList.add(runtime);
			//infoList.add(rs.getString("RUNTIME"));

			infoList.add(rs.getString("SEAT"));
			infoList.add(rs.getString("SUMOF"));
			infoList.add(rs.getString("OPTIONOF"));

		} catch (Exception e) {
			System.out.println("예매내역 출력 실패" + e.getMessage());
			e.printStackTrace();
		}

		System.out.println(infoList.toString());
		return infoList;
		// 닫기

	}
}
