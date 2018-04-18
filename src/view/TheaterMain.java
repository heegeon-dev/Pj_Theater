package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TheaterMain extends JFrame{
	public static JPanel cardPanel;
	public static CardLayout card;
	StartView sv;
	MovieView mv;
	
	public TheaterMain() {
		card = new CardLayout();
		cardPanel = new JPanel(card);
		
		sv = new StartView();
		mv = new MovieView();
		
		cardPanel.add("sv", sv);
		cardPanel.add("mv", mv);
		add(cardPanel);
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		TheaterMain tm = new TheaterMain();
			
	}
	
}
