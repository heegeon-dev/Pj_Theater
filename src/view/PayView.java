package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PayView extends JPanel {
	JLabel[] lb = new JLabel[3];
	JLabel[] img = new JLabel[3];
	String movietitle, starttime, endtime, date;
	ArrayList<String> selectedSeat;
	int person;
	int selectRoomnum;
	JButton ok;
	JDialog pay;

	public PayView(String movietitle, String starttime, String endtime, ArrayList<String> selectedSeat, int person,
			int selectRoomnum, String date) {
		this.movietitle = movietitle;
		this.starttime = starttime;
		this.endtime = endtime;
		this.selectedSeat = selectedSeat;
		this.person = person;
		this.selectRoomnum = selectRoomnum;
		this.date = date;
		addLayout();
		eventProc();
	}

	void eventProc() {
		MouseHandler hlr = new MouseHandler();
		for (int i = 0; i < 3; i++)
			lb[i].addMouseListener(hlr);
	}

	void addLayout() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("결제 수단을 선택해 주세요");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 33));
		lblNewLabel.setBounds(50, 80, 700, 80);
		lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		for (int i = 0; i < 3; i++) {
			img[i] = new JLabel("image");
			img[i].setBounds(200 + 150 * i, 207, 160, 121);
			lb[i] = new JLabel();
			lb[i].setBounds(200 + 150 * i, 398, 160, 121);
			add(img[i]);
			add(lb[i]);
		}

		lb[0].setText("포인트 결제");
		lb[1].setText("현금결제");
		lb[2].setText("신용카드 결제");

	}

	class MouseHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Object ob = e.getSource();
			if (ob == lb[0]) {
				pay = new payOfCredit();

			} else if (ob == lb[1]) {
				pay = new payOfCash();

			} else if (ob == lb[2]) {
				pay = new payOfPoint();

			} else if (ob == ok) {
				// 화면넘기기
				pay.dispose();
				TheaterMain.cardPanel.add("ptv",
						new PrintView(movietitle, starttime, endtime, selectedSeat, person, selectRoomnum, date));
				TheaterMain.card.show(TheaterMain.cardPanel, "ptv");

			}

		}

	}

	class payOfCredit extends JDialog {
		payOfCredit() {
			setLayout(new BorderLayout());
			JPanel center = new JPanel();
			ok = new JButton("확인");
			MouseHandler hlr = new MouseHandler();
			ok.addMouseListener(hlr);
			center.setLayout(new GridLayout(1, 2));
			center.add(new JLabel("결제금액 : "));
			center.add(new JLabel(person * 10000 + ""));
//			center.add(new JLabel("받은금액 : "));
//			center.add(new JTextField("백만원"));
//			center.add(new JLabel("거스름돈 : "));
//			center.add(new JLabel(1000000 - person * 10000 + ""));
			add(center, BorderLayout.CENTER);
			add(ok, BorderLayout.SOUTH);
			setVisible(true);
			setSize(300, 100);
		}
	}

	class payOfCash extends JDialog {
		payOfCash() {
			setLayout(new BorderLayout());
			JPanel center = new JPanel();
			ok = new JButton("확인");
			MouseHandler hlr = new MouseHandler();
			ok.addMouseListener(hlr);
			center.setLayout(new GridLayout(3, 2));
			center.add(new JLabel("결제금액 : "));
			center.add(new JLabel(person * 10000 + ""));
			center.add(new JLabel("받은금액 : "));
			center.add(new JTextField("백만원"));
			center.add(new JLabel("거스름돈 : "));
			center.add(new JLabel(1000000 - person * 10000 + ""));
			add(center, BorderLayout.CENTER);
			add(ok, BorderLayout.SOUTH);
			setVisible(true);
			setSize(300, 200);

		}
	}

	class payOfPoint extends JDialog {
		payOfPoint() {
			setLayout(new BorderLayout());
			JPanel center = new JPanel();
			ok = new JButton("확인");
			MouseHandler hlr = new MouseHandler();
			ok.addMouseListener(hlr);
			center.setLayout(new GridLayout(3, 2));
			center.add(new JLabel("결제금액 : "));
			center.add(new JLabel(person * 10000 + ""));
			center.add(new JLabel("포인트 : "));
			center.add(new JTextField("백만원"));
			center.add(new JLabel("남은 포인트 : "));
			center.add(new JLabel(1000000 - person * 10000 + ""));
			add(center, BorderLayout.CENTER);
			add(ok, BorderLayout.SOUTH);
			setVisible(true);
			setSize(300, 200);
		}
	}

}
