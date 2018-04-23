package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

import db.TheaterDB;
import jdk.nashorn.api.scripting.AbstractJSObject;

public class ScheduleModel {

	TheaterDB db;
	Connection con;
	ArrayList infoList;
	String movieInfo;
	
	public ScheduleModel() {
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

	public ArrayList<ArrayList> getMovieInfo() throws Exception{
		
		ArrayList<ArrayList> list = new ArrayList<ArrayList>();
		String sql = "select m.movie_no movie_no, m.TITLE title, m.RUNINGTIME runningtime "
				+ " from movie m ";
		
		Statement sm = con.createStatement();
		ResultSet rs = sm.executeQuery(sql);
		
		while(rs.next()){
			ArrayList m = new ArrayList();
			m.add(rs.getString("movie_no"));
			m.add(rs.getString("title"));
			m.add(rs.getString("runningtime"));
			
			list.add(m);
		}
		
			return list;
	}

	public void initScreen(int roomNum, String thisday, int numOfDay) {
		try{
		String sql = "INSERT INTO SCREEN(SCREENID,SCREENNO,SEATS,SELECTEDNUM,SELECTED,MOVIEDATE,NUMOFDAY) "
				+ "VALUES(?,?,80,0,'$',?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, roomNum+"#"+thisday.trim()+"#"+numOfDay);
		ps.setInt(2,roomNum);
		ps.setString(3, thisday);
		ps.setInt(4, numOfDay);
		
		ps.executeUpdate();
		}catch(SQLException e ){
			System.out.println(e.getMessage());
			
			
			// TODO: 업데이트 추가하기
			
			return;
		}
	
				
	}
	
	
}
