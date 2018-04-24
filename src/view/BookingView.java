package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.BookingModel;
import view.PrintView.MyMouseListener;

public class BookingView extends JPanel implements ActionListener {
	JTextField tf_tel;
	JPanel p_receipt;
	JButton lblSend; // 버튼으로 사용
	JLabel lblCGV; // 이미지 아이콘
	JLabel lblPrintInfo1, lblPrintInfo2, lblPrintInfo3, lblPrintInfo4, lblPrintInfo5;
	JLabel lblPrintInfoLine1, lblPrintInfoLine2, lblPrintInfoLine3, lblPrintInfoLine4;
	JLabel lblPrintInfoTitle, lblPrintInfoDate, lblPrintInfoTime, lblPrintInfoTheaterNum, lblPrintInfoSeat;
	JLabel lblPrintInfoMoney, lblPrintInfoMember, lblPrintInfoPoint;
	String movietitle, starttime, endtime, date;
	JButton bt_cancel;
	BookingModel model;
	ArrayList<String> list;
	private JLabel OptionOf;
	private JLabel lblOptionOf;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;

	public BookingView() {
		list = new ArrayList<String>();
		model = new BookingModel();
		addLayout();
		eventProc();
	}

	public void addLayout() {
		setLayout(null);
		setSize(800, 630);
		lblCGV = new JLabel("");
		lblCGV.setIcon(new ImageIcon("img\\p1.PNG"));
		lblCGV.setBounds(47, 23, 130, 87);
		add(lblCGV);

		p_receipt = new JPanel();
		p_receipt.setBackground(Color.WHITE);
		p_receipt.setBorder(new LineBorder(new Color(64, 64, 64)));
		p_receipt.setBounds(189, 23, 360, 507);
		add(p_receipt);
		p_receipt.setLayout(null);

		lblPrintInfo1 = new JLabel("영화입장권");
		lblPrintInfo1.setForeground(Color.DARK_GRAY);
		lblPrintInfo1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfo1.setBounds(12, 10, 88, 27);
		p_receipt.add(lblPrintInfo1);

		lblPrintInfo2 = new JLabel("(영수증겸용)");
		lblPrintInfo2.setForeground(Color.DARK_GRAY);
		lblPrintInfo2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfo2.setBounds(12, 38, 88, 27);
		p_receipt.add(lblPrintInfo2);

		lblPrintInfoLine1 = new JLabel("===========================");
		lblPrintInfoLine1.setForeground(Color.DARK_GRAY);
		lblPrintInfoLine1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfoLine1.setBounds(12, 68, 318, 27);
		p_receipt.add(lblPrintInfoLine1);

		lblPrintInfoTitle = new JLabel(movietitle);
		lblPrintInfoTitle.setForeground(Color.DARK_GRAY);
		lblPrintInfoTitle.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfoTitle.setBounds(91, 105, 174, 27);
		p_receipt.add(lblPrintInfoTitle);

		lblPrintInfoDate = new JLabel(date);
		lblPrintInfoDate.setForeground(Color.DARK_GRAY);
		lblPrintInfoDate.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfoDate.setBounds(91, 142, 174, 27);
		p_receipt.add(lblPrintInfoDate);

		lblPrintInfoTime = new JLabel();
		lblPrintInfoTime.setForeground(Color.DARK_GRAY);
		lblPrintInfoTime.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfoTime.setBounds(91, 179, 174, 27);
		p_receipt.add(lblPrintInfoTime);

		lblPrintInfoTheaterNum = new JLabel();
		lblPrintInfoTheaterNum.setForeground(Color.DARK_GRAY);
		lblPrintInfoTheaterNum.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfoTheaterNum.setBounds(91, 216, 45, 27);
		p_receipt.add(lblPrintInfoTheaterNum);

		lblPrintInfoSeat = new JLabel();
		lblPrintInfoSeat.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblPrintInfoSeat.setForeground(Color.DARK_GRAY);
		lblPrintInfoSeat.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfoSeat.setBounds(91, 250, 240, 27);
		p_receipt.add(lblPrintInfoSeat);

		lblPrintInfoLine2 = new JLabel("===========================");
		lblPrintInfoLine2.setForeground(Color.DARK_GRAY);
		lblPrintInfoLine2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfoLine2.setBounds(12, 307, 318, 27);
		p_receipt.add(lblPrintInfoLine2);

		lblPrintInfo3 = new JLabel("결제금액");
		lblPrintInfo3.setForeground(Color.DARK_GRAY);
		lblPrintInfo3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfo3.setBounds(12, 331, 67, 27);
		p_receipt.add(lblPrintInfo3);

		lblPrintInfoMoney = new JLabel();
		lblPrintInfoMoney.setForeground(Color.DARK_GRAY);
		lblPrintInfoMoney.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfoMoney.setBounds(91, 331, 67, 27);
		p_receipt.add(lblPrintInfoMoney);

		lblPrintInfoMember = new JLabel();
		lblPrintInfoMember.setForeground(Color.DARK_GRAY);
		lblPrintInfoMember.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfoMember.setBounds(91, 282, 67, 27);
		p_receipt.add(lblPrintInfoMember);

		lblPrintInfoLine3 = new JLabel("-------------------------------------------");
		lblPrintInfoLine3.setForeground(Color.DARK_GRAY);
		lblPrintInfoLine3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfoLine3.setBounds(12, 396, 318, 27);
		p_receipt.add(lblPrintInfoLine3);

		lblPrintInfo4 = new JLabel("가용포인트");
		lblPrintInfo4.setForeground(Color.DARK_GRAY);
		lblPrintInfo4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfo4.setBounds(12, 412, 81, 27);
		p_receipt.add(lblPrintInfo4);

		lblPrintInfoPoint = new JLabel();
		lblPrintInfoPoint.setForeground(Color.DARK_GRAY);
		lblPrintInfoPoint.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfoPoint.setBounds(104, 412, 67, 27);
		p_receipt.add(lblPrintInfoPoint);

		lblPrintInfoLine4 = new JLabel("-------------------------------------------");
		lblPrintInfoLine4.setForeground(Color.DARK_GRAY);
		lblPrintInfoLine4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfoLine4.setBounds(12, 433, 318, 27);
		p_receipt.add(lblPrintInfoLine4);

		lblPrintInfo5 = new JLabel("교환 및 환불은 상영시간 전까지 영수증 지참");
		lblPrintInfo5.setForeground(Color.DARK_GRAY);
		lblPrintInfo5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPrintInfo5.setBounds(12, 458, 318, 27);
		p_receipt.add(lblPrintInfo5);

		OptionOf = new JLabel("결제수단");
		OptionOf.setForeground(Color.DARK_GRAY);
		OptionOf.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		OptionOf.setBounds(12, 368, 67, 27);
		p_receipt.add(OptionOf);

		lblOptionOf = new JLabel();
		lblOptionOf.setForeground(Color.DARK_GRAY);
		lblOptionOf.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblOptionOf.setBounds(91, 368, 67, 27);
		p_receipt.add(lblOptionOf);

		label = new JLabel("인원수");
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label.setBounds(12, 282, 67, 27);
		p_receipt.add(label);

		label_1 = new JLabel("영화제목");
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setBounds(12, 105, 120, 27);
		p_receipt.add(label_1);

		label_2 = new JLabel("날짜");
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_2.setBounds(12, 142, 120, 27);
		p_receipt.add(label_2);

		label_3 = new JLabel();
		label_3.setText("시간");
		label_3.setForeground(Color.DARK_GRAY);
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_3.setBounds(12, 179, 120, 27);
		p_receipt.add(label_3);

		label_4 = new JLabel();
		label_4.setText("상영관");
		label_4.setForeground(Color.DARK_GRAY);
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_4.setBounds(12, 216, 45, 27);
		p_receipt.add(label_4);

		label_5 = new JLabel();
		label_5.setText("좌석");
		label_5.setForeground(Color.DARK_GRAY);
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_5.setBounds(12, 250, 53, 27);
		p_receipt.add(label_5);

		tf_tel = new JTextField();
		tf_tel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tf_tel.setBackground(Color.WHITE);
		tf_tel.setForeground(Color.LIGHT_GRAY);
		tf_tel.setText("전화번호 입력");
		tf_tel.setBounds(574, 196, 116, 21);
		add(tf_tel);
		tf_tel.setColumns(10);

		lblSend = new JButton("검색");
		lblSend.setBounds(702, 197, 63, 21);
		add(lblSend);

		bt_cancel = new JButton("처음으로");
		bt_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TheaterMain.card.show(TheaterMain.cardPanel, "sv");
			}
		});

		bt_cancel.setBounds(676, 10, 97, 23);
		add(bt_cancel);
	}

	public void eventProc() {
		MyMouseListener mc = new MyMouseListener();
		tf_tel.addActionListener(this);
		tf_tel.addMouseListener(mc);
		lblSend.addActionListener(this);
	}

	public void getinfoByTel(String tel) {
		list = model.getinfoByTel(tel);
		
		if(list.size()<10){
			JOptionPane.showMessageDialog(null,"전화번호가 잘못되었습니다.");
			clearInfo();
			return;
		}
			
		
		lblPrintInfoTitle.setText(list.get(0)); // 영화제목
		lblPrintInfoDate.setText(list.get(1)); // 영화 날짜
		String runtime = list.get(2);// 영화 상영시간_120
		String start = list.get(3);

		int hour = Integer.parseInt(runtime) / 60;
		int min = Integer.parseInt(runtime) % 60;
		StringTokenizer st = new StringTokenizer(list.get(3), ":");

		int starthour = Integer.parseInt(st.nextToken());
		int startmin = Integer.parseInt(st.nextToken());
		int endhour = starthour + hour;
		int endmin = startmin + min;
		String end = endhour + ":" + endmin;

		lblPrintInfoTime.setText(start + " ~ " + end); // 영화 시작시간
		lblPrintInfoTheaterNum.setText(list.get(4) + "관"); // 관

		String seat = list.get(5);
		st = new StringTokenizer(seat, "& ");
		String temp = "";
		while (st.hasMoreTokens()) {
			if (temp != "")
				temp = temp + "," + st.nextToken();
			else
				temp = st.nextToken();
		}
		lblPrintInfoSeat.setText(temp); // 좌석
		lblPrintInfoMoney.setText(list.get(6)); // 결제금액
		lblPrintInfoMember.setText(list.get(9)); // 인원수
		lblPrintInfoPoint.setText(list.get(7)); // 포인트

		if (list.get(8).equals("credit")) {
			OptionOf.setText("신용카드");
		} else if (list.get(8).equals("cash")) {
			OptionOf.setText("현금");
		} else if (list.get(8).equals("point")) {
			OptionOf.setText("포인트");

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if (evt == tf_tel || evt == lblSend) {

			// DB에서 데이터 입력받아와야함
			getinfoByTel(tf_tel.getText());
		//	clearInfo();

		}
	}
	
	public void clearInfo(){
		lblPrintInfoTitle.setText(""); // 영화제목
		lblPrintInfoDate.setText(""); // 영화 날짜
		lblPrintInfoTime.setText(""); // 영화 시작시간
		lblPrintInfoTheaterNum.setText(""); // 관
		lblPrintInfoSeat.setText(""); // 좌석
		lblPrintInfoMoney.setText(""); // 결제금액
		lblPrintInfoMember.setText(""); // 인원수
		lblPrintInfoPoint.setText(""); // 포인트
	}

	class MyMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			Object evt = e.getSource();
			if (evt == tf_tel) {
				tf_tel.setText("");
				tf_tel.setForeground(Color.black);
			}
		}
		
	}

}
