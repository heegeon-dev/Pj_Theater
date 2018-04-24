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
   
   public ArrayList<String> MakeSeatlist(int screenno,String moviedate , int numOfDay) throws SQLException{
      String date = moviedate.substring(5, 7)+moviedate.substring(8,10);
	  String sql = "SELECT SELECTEDNUM,SELECTED FROM screen WHERE screenid= ?" ;
      System.out.println(sql);
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1,screenno+"#"+date+"#"+numOfDay);
      ResultSet rs = ps.executeQuery();
      rs.next();

      StringTokenizer st = new StringTokenizer(rs.getString("SELECTED"),"$,");
      ArrayList<String> seatList = new ArrayList<String>();

      for(int i = 0 ; i < rs.getInt("SELECTEDNUM");i++){
    	  seatList.add( st.nextToken());
    	  System.out.println(seatList.get(i));
      }
      return seatList;   
   }
   

}