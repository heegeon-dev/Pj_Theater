package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TheaterMain extends JFrame{
	public static JPanel cardPanel,cardPanel2;
	public static CardLayout card;
	StartView sv;
	public static MovieView mv;
	LoginView lv;
	public TheaterMain() {  
		card = new CardLayout();
//		card2 = new CardLayout();
		cardPanel = new JPanel(card);
		cardPanel2 = new JPanel(card);
		JTabbedPane  pane = new JTabbedPane();
		MakeScheduleView msv = new MakeScheduleView();
		mv = new MovieView();
		cardPanel.add("sv", new StartView());
		cardPanel.add("mv", mv);
		cardPanel.add("bv",new BookingView());
		add(cardPanel);
		
		cardPanel2.add("lv", new LoginView());
		cardPanel2.add("msv", new MakeScheduleView());
		
		pane.addTab("고객", cardPanel);
		pane.addTab("관리자", cardPanel2);
		
		add(pane);
		setSize(800, 650);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public static void main(String[] args) {
		TheaterMain tm = new TheaterMain();
			
	}
	
}
