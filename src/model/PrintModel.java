package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import db.TheaterDB;

public class PrintModel {
	Connection con;
	ArrayList<ArrayList<String>> infoList;
	String moviedate, screenId;

	public PrintModel() throws Exception {
		infoList = new ArrayList<ArrayList<String>>();
		con = TheaterDB.getConnection();
	}
	
	public int insertPrint(String movietitle, String starttime, String endtime, ArrayList<String> selectedSeat,
			int person, int selectRoomnum, String date, int numOfDay, String optionOf) throws Exception{
		con.setAutoCommit(false);
//		=========================================================================================
		String sql1 = "INSERT INTO payment ( paynum, sumof, optionof) VALUES ( sq_pay_paynum.nextval, ?, ?)";
		System.out.println("payment -> 실패");
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ps1.setInt(1, person * 10000);
		ps1.setString(2, optionOf);

		int result1 = ps1.executeUpdate();

		if (result1 != 1) {
			con.rollback();
			return -1;
		}
//		=================================================================================================
		
		moviedate = String.valueOf(date.charAt(5)) + date.charAt(6) + date.charAt(8) + date.charAt(9);
		screenId = selectRoomnum + "#" + moviedate + "#" + numOfDay;
		
		String sql2 = "SELECT selectednum FROM screen WHERE screenid = ?";
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setString(1, screenId);
		ResultSet rs2 = ps2.executeQuery();
		int selectednum = 0;
		while(rs2.next()){
			selectednum = rs2.getInt("selectednum");
		}
		
		
		
		String sql3 =  "UPDATE screen     "
		          + "SET selected = ( (SELECT selected FROM screen WHERE screenid = ?) || ? ),    "
		        		 + "selectednum = ?   WHERE screenid = ? ";
				PreparedStatement ps3 = con.prepareStatement(sql3);
				System.out.println(sql3);
				String seat = "";
				for (int i = 0; i < person; i++) {
					
					if (selectedSeat.get(i).length() < 3) {
						int row = (int) (selectedSeat.get(i).charAt(0) - 'A')+1;
						char col = selectedSeat.get(i).charAt(1);
						seat = seat + "$" + row + col;
						System.out.println(seat);
					}else{
						int row = (int) (selectedSeat.get(i).charAt(0) - 'A')+1;
						String col = selectedSeat.get(i).charAt(1)+""+selectedSeat.get(i).charAt(2);
						seat = seat + "$" + row + col;
						System.out.println(seat);
					}
				}
				
				ps3.setString(1, screenId);
				ps3.setString(2, seat);
				ps3.setInt(3, person+selectednum);
				ps3.setString(4, screenId);
				
				int result3 = ps3.executeUpdate();
				
				if (result3 != 1) {
					con.rollback();
					return -1;
				}
//		=================================================================================================
				String seats = "";
				for (int i = 0; i < selectedSeat.size(); i++) {
					seats = seats + "&" + selectedSeat.get(i);
				}
				System.out.println(seats);

				String sql4 = "INSERT INTO booking ( bookno, screenid, runtime, moviedate, people, seat, paynum, movie_no)     "
						+ "VALUES ( SQ_BOOKING_BOOKNO.nextval,?, ?, ?, ?, ?, sq_pay_paynum.currval,(SELECT movie_no FROM movie WHERE TITLE = ?) )";
				System.out.println("booking -> 실패");
				PreparedStatement ps4 = con.prepareStatement(sql4);
				ps4.setString(1, screenId);
				ps4.setString(2, starttime);
				ps4.setString(3, date);
				ps4.setInt(4, person);
				ps4.setString(5, seats);
				ps4.setString(6, movietitle);
				int result4 = ps4.executeUpdate();

				if (result4 != 1) {
					con.rollback();
					return -1;
				}
				System.out.println("부킹 인설트");
//				====================================================================================
				con.commit();
				con.setAutoCommit(true);
		
		rs2.close();
		ps1.close();
		ps2.close();
		ps3.close();
		ps4.close();
		return 0;
	}

	public int insertTel(String movietitle, String starttime, String endtime, ArrayList<String> selectedSeat,
			int person, int selectRoomnum, String date, String tel, int numOfDay, String optionOf, int point) throws Exception {

		con.setAutoCommit(false);
		// ===============================================================================================
		System.out.println("시작");
		String sql2 = "INSERT INTO point ( tel, point ) VALUES ( ?,? )";
		System.out.println("point -> tel 실패");
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setString(1, tel);
		ps2.setInt(2, point);
		int result2 = ps2.executeUpdate();

		if (result2 != 1) {
			con.rollback();
			return -1;
		}
		System.out.println("포인트 인설트");
		// =================================================================================================
		String sql3 = "INSERT INTO payment ( paynum, sumof, optionof) VALUES ( sq_pay_paynum.nextval, ?, ?)";
		System.out.println("payment -> 실패");
		PreparedStatement ps3 = con.prepareStatement(sql3);
		ps3.setInt(1, person * 10000);
		ps3.setString(2, optionOf);

		int result3 = ps3.executeUpdate();

		if (result3 != 1) {
			con.rollback();
			return -1;
		}
		System.out.println("페이먼트 인설트");
		
		
		// ==================================================================================================

		// sql 작업중
//		String sql5 = "SELECT screenno, moviedate, numofday FROM screen";
//		PreparedStatement ps5 = con.prepareStatement(sql5);
//		ResultSet rs5 = ps5.executeQuery();

		// while(rs5.next()){
		// System.out.println(rs5.getInt("screenno")+rs5.getString("moviedate")+rs5.getInt("numofday")+"asdf");
		//
		// }

		// if(!rs5.next()){
		// String sql6 = "INSERT INTO "
		// + "screen(screenid, screenno, seats, selected, moviedate, numofday )
		// "
		// + "VALUES (sq_screen_screenid.nextval, ?, ?, ?, ?, ? ) ";
		// PreparedStatement ps6 = con.prepareStatement(sql6);
		// ps6.setInt(1, selectRoomnum);
		// ps6.setInt(2, 80);
		//// ps6.setString(3, selectedSeat.toString());
		// ps6.setString(3, "ddasdf");
		// ps6.setString(4, date);
		// ps6.setInt(5, numOfDay);
		//
		// System.out.println("INSERT 잘됨");
		//
		// int result6 = ps6.executeUpdate();
		// if (result6 != 1) {
		// con.rollback();
		// return -1;
		// }

		// }else{
//		String sql7 = "UPDATE screen SET selected = ?,SELECTEDNUM = ? WHERE SCREENID = ?";
		moviedate = String.valueOf(date.charAt(5)) + date.charAt(6) + date.charAt(8) + date.charAt(9);
		screenId = selectRoomnum + "#" + moviedate + "#" + numOfDay;
		
		String sql4 = "SELECT selectednum FROM screen WHERE screenid = ?";
		PreparedStatement ps4 = con.prepareStatement(sql4);
		ps4.setString(1, screenId);
		ResultSet rs4 = ps4.executeQuery();
		int selectednum = 0;
		while(rs4.next()){
			selectednum = rs4.getInt("selectednum");
		}
		
        String sql7 =  "UPDATE screen     "
          + "SET selected = ( (SELECT selected FROM screen WHERE screenid = ?) || ? ),    "
        		 + "selectednum = ?   WHERE screenid = ? ";
		PreparedStatement ps7 = con.prepareStatement(sql7);
		System.out.println(sql7);
		String seat = "";
		for (int i = 0; i < person; i++) {
			
			if (selectedSeat.get(i).length() < 3) {
				int row = (int) (selectedSeat.get(i).charAt(0) - 'A')+1;
				char col = selectedSeat.get(i).charAt(1);
				seat = seat + "$" + row + col;
				System.out.println(seat);
			}else{
				int row = (int) (selectedSeat.get(i).charAt(0) - 'A')+1;
				String col = selectedSeat.get(i).charAt(1)+""+selectedSeat.get(i).charAt(2);
				seat = seat + "$" + row + col;
				System.out.println(seat);
			}
		}
		
		
		
		ps7.setString(1, screenId);
		ps7.setString(2, seat);
		ps7.setInt(3, person+selectednum);
		ps7.setString(4, screenId);
		System.out.println("screenId:" + screenId);
		System.out.println("seat:" + seat);
		System.out.println("person: "+ person);
				
		int result7 = ps7.executeUpdate();
		System.out.println("실행:" + result7 );
		if (result7 != 1) {
			con.rollback();
			return -1;
		}
		System.out.println("스크린 insert" + result7);
		// }

		// ============================================
		// while(rs5.next()){
		// ArrayList<String> temp = new ArrayList<String>();
		// temp.add(rs5.getString("screenno"));
		// temp.add(rs5.getString("moviedate"));
		// temp.add(rs5.getString("numofday"));
		//
		// infoList.add(temp);
		// }
		// if(infoList.get(0).get(0))
		// ===============================================
		// String sql5 = "UPDATE screen SET SEATS = ? , SELECTEDNUM = ? ,
		// SELECTED = ? WHERE SCREENNO = ? ";
		// System.out.println("screen -> 실패" + selectRoomnum);
		// PreparedStatement ps5 = con.prepareStatement(sql5);
		//
		// ps5.setInt(1, 12314);
		// ps5.setInt(2, 5421);
		// ps5.setString(3, "asdfas");
		// ps5.setInt(4, selectRoomnum);
		// int result5 = ps5.executeUpdate();
		// System.out.println(result5);
		//
		// if (result5 != 1) {
		// con.rollback();
		// return -1;
		// }
		// System.out.println("스크린 업데이트");

		// ==============================================================================

		String seats = "";
		for (int i = 0; i < selectedSeat.size(); i++) {
			seats = seats + "&" + selectedSeat.get(i);
		}
		System.out.println(seats);

		String sql1 = "INSERT INTO booking ( bookno, screenid, runtime, moviedate, people, seat, tel, paynum, movie_no)     "
				+ "VALUES ( SQ_BOOKING_BOOKNO.nextval,?, ?, ?, ?, ?, ?, sq_pay_paynum.currval,(SELECT movie_no FROM movie WHERE TITLE = ?) )";
		System.out.println("booking -> 실패");
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ps1.setString(1, screenId);
		ps1.setString(2, starttime);
		ps1.setString(3, date);
		ps1.setInt(4, person);
		ps1.setString(5, seats);
		ps1.setString(6, tel);
		ps1.setString(7, movietitle);
		int result1 = ps1.executeUpdate();

		if (result1 != 1) {
			con.rollback();
			return -1;
		}
		System.out.println("부킹 인설트");
		// =====================================================================================

		con.commit();
		con.setAutoCommit(true);
		
		rs4.close();
		ps1.close();
		ps2.close();
		ps3.close();
		ps4.close();
		ps7.close();
		
		//ps5.close();
		return 0;
	}

}
