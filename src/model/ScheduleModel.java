package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

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
	
	
}
