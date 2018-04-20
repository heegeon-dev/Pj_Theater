package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import db.TheaterDB;

public class PrintModel {
	Connection con;

	public PrintModel() throws Exception {
		con = TheaterDB.getConnection();
	}

	public int insertTel(String movietitle, String starttime, String endtime, ArrayList<String> selectedSeat,
			int person, int selectRoomnum, String date, String tel) throws Exception {

		con.setAutoCommit(false);
		
		System.out.println("시작");
		String sql2 = "INSERT INTO point ( tel ) VALUES ( ? )";
		System.out.println("point -> tel 실패");
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setString(1, tel);
		int result2 = ps2.executeUpdate();

		if (result2 != 1) {
			con.rollback();
			return -1;
		}
		System.out.println("포인트 인설트");

		String sql3 = "INSERT INTO payment ( paynum ) VALUES ( sq_pay_paynum.nextval )";
		System.out.println("payment -> 실패");
		PreparedStatement ps3 = con.prepareStatement(sql3);
		int result3 = ps3.executeUpdate();

		if (result3 != 1) {
			con.rollback();
			return -1;
		}
		System.out.println("페이먼트 인설트");

		String sql5 = "UPDATE screen SET SEATS = ? , SELECTEDNUM = ? , SELECTED = ?  WHERE SCREENNO = ? ";
		System.out.println("screen -> 실패" + selectRoomnum);
		PreparedStatement ps5 = con.prepareStatement(sql5);

		ps5.setInt(1, 12314);
		ps5.setInt(2, 5421);
		ps5.setString(3, "asdfas");
		ps5.setInt(4, selectRoomnum);
		int result5 = ps5.executeUpdate();
		System.out.println(result5);

		if (result5 != 1) {
			con.rollback();
			return -1;
		}
		System.out.println("스크린 업데이트");
		// String sql4 = "INSERT INTO movie ( movie_no, title, runingtime )
		// VALUES ( sq_movie_movieno.nextval, ?, ? )";
		// System.out.println("movie -> 실패");
		// PreparedStatement ps4 = con.prepareStatement(sql4);
		// ps4.setString(1, movietitle);
		// SimpleDateFormat format3 = new SimpleDateFormat("HH:mm",
		// Locale.KOREA);
		// Calendar cal = Calendar.getInstance();
		// ps4.setInt(2, 120);
		// int result4 = ps4.executeUpdate();
		// if(result4 != 1){
		// con.rollback();
		// return -1;
		// }
		String seats="";
		for (int i = 0; i < selectedSeat.size(); i++) {
			 seats = seats+"&"+selectedSeat.get(i);
		}
		System.out.println(seats);
		String sql1 = "INSERT INTO booking ( bookno, screenno, runtime, moviedate, people, seat, tel, paynum)     "
				+ "VALUES ( SQ_BOOKING_BOOKNO.nextval, ?, ?, ?, ?, ?, ?, sq_pay_paynum.currval )";
		System.out.println("booking -> 실패");
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ps1.setInt(1, selectRoomnum);
		ps1.setString(2, starttime);
		ps1.setString(3, date);
		ps1.setInt(4, person);
		ps1.setString(5, seats);
		ps1.setString(6, tel);

		int result1 = ps1.executeUpdate();

		if (result1 != 1) {
			con.rollback();
			return -1;
		}
		System.out.println("부킹 인설트");

				
		con.commit();
		con.setAutoCommit(true);
		ps1.close();
		ps2.close();
		ps3.close();
		// ps4.close();
		ps5.close();
		return 0;
	}

}
