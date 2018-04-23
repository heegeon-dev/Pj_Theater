package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TheaterMain extends JFrame{
	public static JPanel cardPanel;
	public static CardLayout card;
	StartView sv;
	MovieView mv;
	
	public TheaterMain() {
		card = new CardLayout();
		cardPanel = new JPanel(card);
		JTabbedPane  pane = new JTabbedPane();
		MakeScheduleView msv = new MakeScheduleView();

		cardPanel.add("sv", new StartView());
		cardPanel.add("mv", new MovieView());
		cardPanel.add("bv",new BookingView());
		add(cardPanel);
		
		pane.addTab("고객", cardPanel );
		pane.addTab("관리자", msv);
		add(pane);
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}
	
	public static void main(String[] args) {
		TheaterMain tm = new TheaterMain();
			
	}
	
}
