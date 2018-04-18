package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TheaterMain extends JFrame{
	JPanel cardPanel;
	CardLayout card;
	StartView sv;
	MovieView mv;
	
	public TheaterMain() {
		card = new CardLayout();
		cardPanel = new JPanel(card);
		
		sv = new StartView(card, cardPanel);
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
