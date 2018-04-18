package view;

import javax.swing.JFrame;


public class TheaterMain extends JFrame{
	public TheaterMain() {
		
		
		StartView sv2 = new StartView();
		
		add(sv2.contentPane);
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		TheaterMain tm = new TheaterMain();
			
	}
	
}
