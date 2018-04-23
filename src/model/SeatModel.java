package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import db.TheaterDB;

public class SeatModel {
	TheaterDB db;
	Connection con;

	public SeatModel() {
		try {
			con = db.getConnection();
		} catch (Exception e) {
			System.out.println("디비 연결 실패");
			e.printStackTrace();
		}

	}
	
	public String[] MakeSeatlist(int screenno,String moviedate , int numOfDay) throws SQLException{
		String sql = "SELECT SELECTEDNUM,SELECTED FROM screen WHERE screenno = ? and moviedate = ? and numofday = ? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, screenno);
		ps.setString(2, moviedate);
		ps.setInt(3, numOfDay);
		ResultSet rs = ps.executeQuery();
		rs.next();
		if(rs == null)
			return null;
		StringTokenizer st = new StringTokenizer(rs.getString("SELECTED"),"$,");
		String[] seatList = new String[rs.getInt("SELECTEDNUM")];

		for(int i = 0 ; i < rs.getInt("SELECTEDNUM");i++)
			seatList[i] = st.nextToken();
		return seatList;	
	}
	

}
