package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.PayModel;

import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class PayView extends JPanel {
	JLabel[] lb = new JLabel[3];
	JLabel[] img = new JLabel[3];
	JLabel lblPrev, lblCash, lblCredit, lblPoint;
	String movietitle, starttime, endtime, date;
	ArrayList<String> selectedSeat;
	int person,point,money;
	int selectRoomnum;
	JButton ok;
	JDialog pay;
	JTextField tf_tel;
	JLabel lb_point;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	int numOfDay;
	PayModel model;

	public PayView(String movietitle, String starttime, String endtime, ArrayList<String> selectedSeat, int person,
			int selectRoomnum, String date, int numOfDay) {
		setBackground(Color.WHITE);
		this.movietitle = movietitle;
		this.starttime = starttime;
		this.endtime = endtime;
		this.selectedSeat = selectedSeat;
		this.person = person;
		this.selectRoomnum = selectRoomnum;
		this.date = date;
		this.numOfDay = numOfDay;
		model = new PayModel();
		addLayout();
		eventProc();
	}

	void eventProc() {
		MouseHandler hlr = new MouseHandler();
		enterkeylistener hlr2 = new enterkeylistener();
		label.addMouseListener(hlr);
		label_1.addMouseListener(hlr);
		label_2.addMouseListener(hlr);
		lblPrev.addMouseListener(hlr);
		tf_tel.addActionListener(hlr2);
	}

	void addLayout() {
		setLayout(null);
		lb_point = new JLabel();
		tf_tel = new JTextField();
		JLabel lblNewLabel = new JLabel("결제 수단을 선택해 주세요");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		lblNewLabel.setBounds(58, 79, 700, 80);
		lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);

		lblPrev = new JLabel("");
		lblPrev.setIcon(new ImageIcon("img\\p6.PNG"));
		lblPrev.setBounds(625, 489, 110, 35);
		add(lblPrev);

		lblCash = new JLabel("");
		lblCash.setBackground(Color.WHITE);
		lblCash.setIcon(new ImageIcon("img\\p18.PNG"));
		lblCash.setBounds(344, 207, 117, 121);
		add(lblCash);

		lblCredit = new JLabel("");
		lblCredit.setBackground(Color.WHITE);
		lblCredit.setIcon(new ImageIcon("img\\p19.PNG"));
		lblCredit.setBounds(200, 207, 129, 121);
		add(lblCredit);

		lblPoint = new JLabel("");
		lblPoint.setBackground(Color.WHITE);
		lblPoint.setIcon(new ImageIcon("img\\p20.PNG"));
		lblPoint.setBounds(473, 207, 144, 121);
		add(lblPoint);

		label = new JLabel("신용 카드 결제");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label.setBackground(Color.LIGHT_GRAY);
		label.setForeground(Color.DARK_GRAY);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(200, 328, 129, 121);
		add(label);

		label_1 = new JLabel("현금 결제");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_1.setBackground(Color.LIGHT_GRAY);
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(344, 328, 117, 121);
		add(label_1);

		label_2 = new JLabel("포인트 결제");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_2.setBackground(Color.LIGHT_GRAY);
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(473, 328, 144, 121);
		add(label_2);

		// for (int i = 0; i < 3; i++) {
		// img[i] = new JLabel("");
		// img[i].setBounds(200 + 150 * i, 207, 160, 121);
		// lb[i] = new JLabel();
		// lb[i].setBounds(200 + 150 * i, 398, 160, 121);
		// lb[i].setHorizontalAlignment(SwingConstants.CENTER);
		// lb[i].setForeground(Color.DARK_GRAY);
		// add(img[i]);
		// add(lb[i]);
		// }
		// img[0].setIcon(new
		// ImageIcon("C:\\Users\\student\\Desktop\\무인발권기\\16.PNG"));
		// img[1].setIcon(new
		// ImageIcon("C:\\Users\\student\\Desktop\\무인발권기\\14.PNG"));
		// img[2].setIcon(new
		// ImageIcon("C:\\Users\\student\\Desktop\\무인발권기\\17.PNG"));

		// lb[0].setText("신용카드 결제");
		// lb[1].setText("현금결제");
		// lb[2].setText("포인트 결제");

	}

	class MouseHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Object ob = e.getSource();
			if (ob == label) {
				pay = new payOfCredit();

			} else if (ob == label_1) {
				pay = new payOfCash();

			} else if (ob == label_2) {
				pay = new payOfPoint();

			} else if (ob == ok) {
				// 화면넘기기
				try {
					model.PayOfPoint(tf_tel.getText(),point-person*1000);
				} catch (SQLException e1) {
					System.out.println("포인트계산실패");
					e1.printStackTrace();
				}
				pay.dispose();
				TheaterMain.cardPanel.add("ptv", new PrintView(movietitle, starttime, endtime, selectedSeat, person,
						selectRoomnum, date, numOfDay));
				TheaterMain.card.show(TheaterMain.cardPanel, "ptv");

			} else if (ob == lblPrev)
				TheaterMain.card.show(TheaterMain.cardPanel, "stv");
		}

	}

	class enterkeylistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object ob = e.getSource();

			if (ob == tf_tel) {
				try {
					point = model.getOfPoint(tf_tel.getText());
					lb_point.setText(String.valueOf(point));
				} catch (SQLException e1) {
					System.out.println("포인트를 불러올 수 없습니다.");
					e1.printStackTrace();
				}
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
			// center.add(new JLabel("받은금액 : "));
			// center.add(new JTextField("백만원"));
			// center.add(new JLabel("거스름돈 : "));
			// center.add(new JLabel(1000000 - person * 10000 + ""));
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
			center.setLayout(new GridLayout(4, 2));
			center.add(new JLabel("전화번호:"));
			tf_tel = new JTextField();
			center.add(tf_tel);
			center.add(new JLabel("결제금액 : "));
			center.add(new JLabel(person * 10000 + ""));
			center.add(new JLabel("포인트 : "));
			center.add(lb_point);
			center.add(new JLabel("남은 포인트 : "));
			center.add(new JLabel((point - person * 10000) + ""));
			add(center, BorderLayout.CENTER);
			add(ok, BorderLayout.SOUTH);
			setVisible(true);
			setSize(300, 200);
		}
	}

}
