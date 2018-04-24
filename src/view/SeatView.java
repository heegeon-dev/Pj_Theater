package view;

import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import model.SeatModel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JTextArea;

public class SeatView extends Panel implements MouseListener {

	JButton jb_minus, jb_plus;
	JLabel[][] seat = new JLabel[8][10];
	int person, cnt;
	JLabel lb_person, movieinfo;
	boolean[][] booking = new boolean[8][10];
	ArrayList<String> selectedSeat, selectedSeats;
	String movietitle, starttime, endtime, date;
	int selectRoomnum;
	JLabel lblPrev, lblOK;
	private JLabel lblCGVimg;
	int numOfDay;
	SeatModel model;

	public SeatView(String movietitle, String starttime, String endtime, int selectRoomnum, String date, int numOfDay) {
		selectedSeat = new ArrayList<String>();
		selectedSeats = new ArrayList<String>();
		model = new SeatModel();
		this.movietitle = movietitle;
		this.starttime = starttime;
		this.endtime = endtime;
		this.selectRoomnum = selectRoomnum;
		this.date = date;
		this.numOfDay = numOfDay;
		addLayout();
		initSeat();
		eventProc();
		movieinfo.setText(movietitle + "     " + starttime);
		model = new SeatModel();

		lblCGVimg = new JLabel("New label");
		lblCGVimg.setIcon(new ImageIcon("img\\p1.PNG"));
		lblCGVimg.setBounds(622, 51, 127, 96);
		add(lblCGVimg);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		textArea.setText("좌석 및 인원을 \r\n선택하시고,\r\n확인 버튼을 \r\n눌러주세요.");
		textArea.setForeground(Color.DARK_GRAY);
		textArea.setBounds(622, 155, 182, 103);
		add(textArea);

	}

	private void initSeat() {
		person = 0;
		cnt = 0;
		// selectedSeats = con.selectedSeats(); // 하나의 원소에는 (/row,col) 모양의
		// String

		// testcase
		// selectedSeats = new ArrayList<String>();
		// selectedSeats.add("$1,2");
		// selectedSeats.add("$4,5");
		// selectedSeats.add("$2,3");
		// selectedSeats.add("$1,1");

		// endofTestcase

		try {

			selectedSeats = model.MakeSeatlist(selectRoomnum, date, numOfDay);
		} catch (SQLException e) {
			System.out.println("좌석 초기화 실패");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("예약된 좌석이 없음");
			e.printStackTrace();
			return;

		}

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 10; j++)
				booking[i][j] = false;

		for (int i = 0; i < selectedSeats.size(); i++) {
			int row, col;
			if (selectedSeats.get(i).length() < 3) {
				row = Integer.parseInt(String.valueOf(selectedSeats.get(i).charAt(0))) - 1;
				col = Integer.parseInt(String.valueOf(selectedSeats.get(i).charAt(1))) - 1;
			} else {
				row = Integer.parseInt(String.valueOf(selectedSeats.get(i).charAt(0))) - 1;
				col = Integer.parseInt(String.valueOf(selectedSeats.get(i).charAt(1)) + selectedSeats.get(i).charAt(2))
						- 1;
			}
			System.out.println(col + "" + row);
			seat[row][col].setBackground(Color.DARK_GRAY);
			seat[row][col].setForeground(Color.DARK_GRAY);
			booking[row][col] = true;
		}

	}

	private void eventProc() {
		jb_minus.addMouseListener(this);
		jb_plus.addMouseListener(this);
		lblOK.addMouseListener(this);
		lblPrev.addMouseListener(this);
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
		movieinfo.setForeground(Color.GRAY);
		movieinfo.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		movieinfo.setBounds(158, 0, 298, 48);
		movieinfo.setHorizontalAlignment(SwingConstants.CENTER);
		movieinfo.setVerticalAlignment(SwingConstants.CENTER);
		add(movieinfo);

		lb_person = new JLabel("0");
		lb_person.setFont(new Font("굴림", Font.PLAIN, 53));
		lb_person.setBounds(674, 254, 38, 141);
		add(lb_person);

		jb_minus = new JButton("-");
		jb_minus.setBackground(SystemColor.controlHighlight);
		jb_minus.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		jb_minus.setBounds(622, 308, 40, 40);
		add(jb_minus);

		jb_plus = new JButton("+");
		jb_plus.setBackground(SystemColor.controlHighlight);
		jb_plus.setFont(new Font("맑은 고딕", Font.BOLD, 9));

		jb_plus.setBounds(717, 308, 40, 40);
		add(jb_plus);

		lblPrev = new JLabel("New label");
		lblPrev.setIcon(new ImageIcon("img\\p6.PNG"));
		lblPrev.setBounds(635, 500, 110, 35);
		add(lblPrev);

		lblOK = new JLabel("ok");
		lblOK.setIcon(new ImageIcon("img\\p17.PNG"));
		lblOK.setBounds(660, 355, 60, 40);
		add(lblOK);

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
		} else if (lblOK == ob) {
			if(cnt==0){
				JOptionPane.showMessageDialog(null, "인원 수에 맞는 좌석을 선택해 주세요");
				return;
			}
			System.out.println(movietitle + " " + starttime + " " + endtime + " " + " " + person);
			Collections.sort(selectedSeat);
			System.out.println(selectedSeat.toString());
			if (cnt < person) {
				JOptionPane.showMessageDialog(null, "인원 수에 맞는 좌석을 선택해 주세요");
				return;
			}
			TheaterMain.cardPanel.add("pv",
					new PayView(movietitle, starttime, endtime, selectedSeat, person, selectRoomnum, date, numOfDay));
			TheaterMain.card.show(TheaterMain.cardPanel, "pv");
		} else if (lblPrev == ob) {
			TheaterMain.card.show(TheaterMain.cardPanel, "mv");
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