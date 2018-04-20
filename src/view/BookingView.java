package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;

import model.BookingModel;


public class BookingView extends JPanel implements ActionListener {
	
	 JTextField tfTel;
	 JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5;

	 BookingModel model;
	
	 String tel;
	 ArrayList list;
	 
	 
	public BookingView() {
		addLayout();
		eventProc();
	}
	
	public void addLayout(){
		setLayout(null);
		
		JButton printButton = new JButton("예매 내역 출력");
		printButton.setFont(new Font("굴림", Font.PLAIN, 14));
		printButton.setBounds(35, 60, 166, 61);
		add(printButton);
		
		tfTel = new JTextField();
		tfTel.setBounds(69, 158, 132, 21);
		add(tfTel);
		tfTel.setColumns(10);
		
		JLabel insertTel = new JLabel("tel");
		insertTel.setBounds(35, 161, 32, 15);
		add(insertTel);
		
		JLabel movietitle = new JLabel("영화명");
		movietitle.setBounds(239, 50, 70, 40);
		add(movietitle);
		
		textField = new JTextField();
		textField.setBounds(321, 50, 400, 40);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(321, 139, 400, 40);
		add(textField_1);
		
		JLabel date = new JLabel("날짜");
		date.setBounds(239, 139, 70, 40);
		add(date);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(321, 221, 400, 40);
		add(textField_2);
		
		JLabel runningtime = new JLabel("상영시간");
		runningtime.setBounds(239, 221, 70, 40);
		add(runningtime);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(321, 305, 400, 40);
		add(textField_3);
		
		JLabel seatInfo = new JLabel("좌석");
		seatInfo.setBounds(239, 305, 70, 40);
		add(seatInfo);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(321, 396, 400, 40);
		add(textField_4);
		
		JLabel money = new JLabel("결제금액");
		money.setBounds(239, 396, 70, 40);
		add(money);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(321, 497, 400, 40);
		add(textField_5);
		
		JLabel payBy = new JLabel("결제수단");
		payBy.setBounds(239, 497, 70, 40);
		add(payBy);
	}
	
	public void eventProc(){
		tfTel.addActionListener(this);
		textField.addActionListener(this);
		textField_1.addActionListener(this);
		textField_2.addActionListener(this);
		textField_3.addActionListener(this);
		textField_4.addActionListener(this);
		textField_5.addActionListener(this);
	}

	public void getinfoByTel(String tel){
		 list=model.getinfoByTel(tel);
		 
		 textField.setText(list.get(0).toString());
		 textField_1.setText(list.get(1).toString());
		 textField_2.setText(list.get(2).toString());
		 textField_3.setText(list.get(3).toString());
		 textField_4.setText(list.get(4).toString());
		 textField_5.setText(list.get(5).toString());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt==tfTel){
			getinfoByTel(tfTel.getText());
			
			// DB에서 데이터 입력받아와야함.
		}
	}
}
