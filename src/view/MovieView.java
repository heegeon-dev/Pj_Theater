package view;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MovieView extends JPanel implements ActionListener {

	JButton btnNewButton, btnNewButton_1, button;

	int row = 3;
	int col = 4;
	JLabel[][] movieAll = new JLabel[row][col];
	String[][] movieInfo = new String[row][col];
	String today;

	Calendar cal = Calendar.getInstance();
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd",Locale.KOREA);
	final String[] week = { "일", "월", "화", "수", "목", "금", "토" };
	
	// JLabel lblNewLabel, lblNewLabel_1, label_2, label_3, label_4, label_5,
	// label_6, label_7, label_8, label_9, label_10,
	// label_11, label_12;

	public MovieView() {
		addLayout();
		eventProc();

		for(int i =0;i<row;i++){
			for(int j=0;j<col;j++){
				movieInfo[i][j]=new String();
			}
		}
		
		btnNewButton.setText(format1.format(cal.getTime())+ " "+week[cal.get(Calendar.DAY_OF_WEEK) - 1] + "요일");

	}

	public void addLayout() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 750, 550);
		panel.setBackground(Color.PINK);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 10, 720, 100);
		panel.add(panel_1);
		panel_1.setLayout(null);

		btnNewButton = new JButton("New button");
		btnNewButton.setBounds(285, 10, 235, 79);
		panel_1.add(btnNewButton);

		btnNewButton_1 = new JButton("<");
		btnNewButton_1.setBounds(206, 12, 67, 75);
		panel_1.add(btnNewButton_1);

		button = new JButton(">");
		button.setBounds(539, 12, 75, 75);
		panel_1.add(button);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(29, 10, 101, 79);
		panel_1.add(panel_5);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 126, 720, 130);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("제1관");
		lblNewLabel.setBounds(12, 10, 50, 100);
		panel_2.add(lblNewLabel);

		// lblNewLabel_1 = new JLabel("New label");
		// lblNewLabel_1.setBounds(66, 10, 140, 100);
		// panel_2.add(lblNewLabel_1);

		movieAll[0][0] = new JLabel("New label");
		movieAll[0][0].setBounds(66, 10, 140, 100);
		panel_2.add(movieAll[0][0]);

		// label_2 = new JLabel("New label");
		// label_2.setBounds(230, 10, 140, 100);
		// panel_2.add(label_2);

		movieAll[0][1] = new JLabel("New label");
		movieAll[0][1].setBounds(230, 10, 140, 100);
		panel_2.add(movieAll[0][1]);

		// label_3 = new JLabel("New label");
		// label_3.setBounds(398, 10, 140, 100);
		// panel_2.add(label_3);

		movieAll[0][2] = new JLabel("New label");
		movieAll[0][2].setBounds(398, 10, 140, 100);
		panel_2.add(movieAll[0][2]);

		// label_4 = new JLabel("New label");
		// label_4.setBounds(556, 10, 140, 100);
		// panel_2.add(label_4);

		movieAll[0][3] = new JLabel("New label");
		movieAll[0][3].setBounds(556, 10, 140, 100);
		panel_2.add(movieAll[0][3]);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 266, 720, 130);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel label = new JLabel("제2관");
		label.setBounds(12, 10, 50, 100);
		panel_3.add(label);

		// label_5 = new JLabel("New label");
		// label_5.setBounds(66, 10, 140, 100);
		// panel_3.add(label_5);

		movieAll[1][0] = new JLabel("New label");
		movieAll[1][0].setBounds(66, 10, 140, 100);
		panel_3.add(movieAll[1][0]);

		// label_6 = new JLabel("New label");
		// label_6.setBounds(221, 10, 140, 100);
		// panel_3.add(label_6);

		movieAll[1][1] = new JLabel("New label");
		movieAll[1][1].setBounds(221, 10, 140, 100);
		panel_3.add(movieAll[1][1]);

		// label_7 = new JLabel("New label");
		// label_7.setBounds(397, 10, 140, 100);
		// panel_3.add(label_7);

		movieAll[1][2] = new JLabel("New label");
		movieAll[1][2].setBounds(397, 10, 140, 100);
		panel_3.add(movieAll[1][2]);

		// label_8 = new JLabel("New label");
		// label_8.setBounds(558, 10, 140, 100);
		// panel_3.add(label_8);

		movieAll[1][3] = new JLabel("New label");
		movieAll[1][3].setBounds(558, 10, 140, 100);
		panel_3.add(movieAll[1][3]);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(12, 407, 720, 130);
		panel.add(panel_4);
		panel_4.setLayout(null);

		JLabel label_1 = new JLabel("제3관");
		label_1.setBounds(12, 10, 50, 100);
		panel_4.add(label_1);

		// label_9 = new JLabel("New label");
		// label_9.setBounds(66, 10, 140, 100);
		// panel_4.add(label_9);

		movieAll[2][0] = new JLabel("New label");
		movieAll[2][0].setBounds(66, 10, 140, 100);
		panel_4.add(movieAll[2][0]);

		// label_10 = new JLabel("New label");
		// label_10.setBounds(218, 10, 140, 100);
		// panel_4.add(label_10);

		movieAll[2][1] = new JLabel("New label");
		movieAll[2][1].setBounds(218, 10, 140, 100);
		panel_4.add(movieAll[2][1]);

		// label_11 = new JLabel("New label");
		// label_11.setBounds(396, 10, 140, 100);
		// panel_4.add(label_11);

		movieAll[2][2] = new JLabel("New label");
		movieAll[2][2].setBounds(396, 10, 140, 100);
		panel_4.add(movieAll[2][2]);

		// label_12 = new JLabel("New label");
		// label_12.setBounds(558, 10, 140, 100);
		// panel_4.add(label_12);

		movieAll[2][3] = new JLabel("New label");
		movieAll[2][3].setBounds(558, 10, 140, 100);
		panel_4.add(movieAll[2][3]);

	}

	public void eventProc() {
		btnNewButton_1.addActionListener(this);
		btnNewButton.addActionListener(this);
		button.addActionListener(this);

		MouseHandler mouseLsnr = new MouseHandler();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				movieAll[i][j].addMouseListener(mouseLsnr);
				movieInfo[i][j] = "";
			}
		}

	}



	@Override // 액션리스너 for buttons
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub
		System.out.println("button");

		Object evt = e.getSource();

		if (evt == btnNewButton_1) {
			cal.add(Calendar.DATE,-1);
			// 버튼을 setText( cal 값으로 )
			btnNewButton.setText(format1.format(cal.getTime())+" " +week[cal.get(Calendar.DAY_OF_WEEK) - 1] + "요일");
		}else if(evt == button){
			cal.add(Calendar.DATE,+1);
			btnNewButton.setText(format1.format(cal.getTime())+ " "+week[cal.get(Calendar.DAY_OF_WEEK) - 1] + "요일");
		}


	}

	public class MouseHandler extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("label");
		}

	}

}
