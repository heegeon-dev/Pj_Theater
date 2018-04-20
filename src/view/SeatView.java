package view;

import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.SwingConstants;


import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class SeatView extends Panel implements MouseListener {

	JButton jb_minus, jb_plus, jb_pre, jb_next;
	JLabel[][] seat = new JLabel[8][10];
	int person, cnt;
	JLabel lb_person, movieinfo;
	ArrayList<String> selectedSeats;
	boolean[][] booking = new boolean[8][10];
	ArrayList<String> selectedSeat;
	String movietitle,starttime, endtime,date;
	int selectRoomnum;


	public SeatView(String movietitle, String starttime, String endtime , int selectRoomnum,String date) {
		addLayout();
		initSeat();
		eventProc();
		movieinfo.setText("<html>" + movietitle + "<br>" + starttime + "</html>");
		this.movietitle = movietitle;
		this.starttime = starttime;
		this.endtime = endtime;
		this.selectRoomnum = selectRoomnum;
		this.date = date;
		selectedSeat = new ArrayList<String>();
	}

	private void initSeat() {
		person = 0;
		cnt = 0;
		// selectedSeats = con.selectedSeats(); // 하나의 원소에는 (/row,col) 모양의
		// String

		// testcase
		selectedSeats = new ArrayList<String>();
		selectedSeats.add("$1,2");
		selectedSeats.add("$4,5");
		selectedSeats.add("$2,3");
		selectedSeats.add("$1,1");

		// endofTestcase

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 10; j++)
				booking[i][j] = false;

		for (int i = 0; i < selectedSeats.size(); i++) {
			StringTokenizer st = new StringTokenizer(selectedSeats.get(i), ",$");
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			seat[col][row].setBackground(Color.black);
			booking[col][row] = true;
		}

	}

	private void eventProc() {
		jb_minus.addMouseListener(this);
		jb_plus.addMouseListener(this);
		jb_pre.addMouseListener(this);
		jb_next.addMouseListener(this);
		for (int i = 0; i < seat.length; i++)
			for (int j = 0; j < seat[i].length; j++)
				seat[i][j].addMouseListener(this);

	}

	void addLayout() {
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel jl_Screen = new JLabel("SCREEN");
		jl_Screen.setOpaque(true);
		jl_Screen.setForeground(Color.black);
		jl_Screen.setBackground(Color.LIGHT_GRAY);
		jl_Screen.setBounds(27, 58, 560, 35);
		jl_Screen.setVerticalAlignment(SwingConstants.CENTER);
		jl_Screen.setHorizontalAlignment(SwingConstants.CENTER);
		add(jl_Screen);

		movieinfo = new JLabel("New label");
		movieinfo.setBounds(628, 75, 139, 130);
		add(movieinfo);

		lb_person = new JLabel("0");
		lb_person.setFont(new Font("굴림", Font.PLAIN, 53));
		lb_person.setBounds(674, 254, 38, 141);
		add(lb_person);

		jb_minus = new JButton("-");
		jb_minus.setBounds(605, 304, 54, 47);
		add(jb_minus);

		jb_plus = new JButton("+");

		jb_plus.setBounds(724, 304, 53, 47);
		add(jb_plus);

		jb_pre = new JButton("이전");
		jb_pre.setBounds(605, 477, 75, 59);
		add(jb_pre);

		jb_next = new JButton("다음");
		jb_next.setBounds(702, 477, 75, 59);
		add(jb_next);

		for (int i = 0; i < seat.length; i++)
			for (int j = 0; j < seat[i].length; j++) {
				char row = (char) ('A' + i);
				String col = String.valueOf((j + 1));
				seat[i][j] = new JLabel(row + col);
				seat[i][j].setOpaque(true);
				seat[i][j].setBackground(Color.LIGHT_GRAY);
				seat[i][j].setVerticalAlignment(SwingConstants.CENTER);
				seat[i][j].setHorizontalAlignment(SwingConstants.CENTER);

				if (j < 5) // 27,45 가 시작점
					seat[i][j].setBounds(29 + j * 55, 110 + i * 55, 50, 35);
				else
					seat[i][j].setBounds(39 + j * 55, 110 + i * 55, 50, 35);
				add(seat[i][j]);

			}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object ob = e.getSource();

		if (jb_minus == ob) {
			if (person > 1) {
				person--;
				lb_person.setText(String.valueOf(person));

			}
		} else if (jb_plus == ob) {
			if (person < 10) {
				person++;
				lb_person.setText(String.valueOf(person));
			}
		} else if (jb_next == ob) {
			System.out.println(movietitle+" "+starttime+" "+endtime+" "+" "+person);
			Collections.sort(selectedSeat);
			System.out.println(selectedSeat.toString());
			TheaterMain.cardPanel.add("pv", new PayView(movietitle,starttime,endtime,selectedSeat,person,selectRoomnum,date));
			TheaterMain.card.show(TheaterMain.cardPanel, "pv");
		}

		for (int i = 0; i < seat.length; i++)
			for (int j = 0; j < seat[i].length; j++) {
				if (seat[i][j] == ob) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						if (cnt < person && booking[i][j] == false && booking[i][j] == false) {
							seat[i][j].setBackground(Color.cyan);
							cnt++;
							booking[i][j] = true;
							selectedSeat.add(seat[i][j].getText());
						}
					} else if (e.getButton() == MouseEvent.BUTTON3 && booking[i][j] == true) {
						if (cnt > 0 && booking[i][j] == true) {
							seat[i][j].setBackground(Color.lightGray);
							cnt--;
							booking[i][j] = false;
							selectedSeat.remove(seat[i][j].getText());
							
						}
					}
				}
			}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
