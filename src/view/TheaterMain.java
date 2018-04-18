package view;

import javax.swing.JFrame;

public class TheaterMain extends JFrame{
	public TheaterMain() {
		
		
		SeatView sv = new SeatView();
		
		this.add(sv);
		setSize(800,600);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		TheaterMain tm = new TheaterMain();
		
		
		
	}

}
