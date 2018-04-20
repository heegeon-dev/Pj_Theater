package view;

import javax.swing.*;
import javax.swing.plaf.synth.SynthSplitPaneUI;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MovieView extends JPanel implements ActionListener {

	JButton btnNewButton, btnNewButton_1, button;

	int row = 3;
	int col = 4;

	int curRow;
	int curCol;

	SimpleDateFormat format1, format2, format3;

	String movietitle, movieRunnigtime, movieStartTime;

	// 각 영화정보를 담은 라벨. 그리고 그 라벨의 시간과 제목을 담을 String 배열
	JLabel[][] movieAll = new JLabel[row][col];
	int RoomNum;

	ArrayList<ArrayList<String[]>> room = new ArrayList<ArrayList<String[]>>();
	String today, thisday, cTime;

	// runningtime을 띄워주기 위한 변수.
	String starttime, endingtime, runningtime;

	// 날짜를 띄워주기 위한 변수
	Calendar curcal = Calendar.getInstance(); // 현재시간
	Calendar thiscal = Calendar.getInstance();
	Date date = new Date();

	final String[] week = { "일", "월", "화", "수", "목", "금", "토" };

	// 상영관_영화제목_시간대 를 담은 파일을 받아올 변수

	public MovieView() {

		// 날짜버튼에 보여주기 위한 포멧
		format1 = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
		// 해당일의 파일읽어오기 위한
		format2 = new SimpleDateFormat("MMdd", Locale.KOREA);
		// 현재시간과 비교하기 위한 포멧
		format3 = new SimpleDateFormat("HH:mm", Locale.KOREA);
		cTime = format3.format(curcal.getTime()); // 현재시간
		thisday = format2.format(curcal.getTime()); // 현재 날짜

		starttime = "9:00";

		addLayout();
		eventProc();
		btnNewButton
				.setText(format1.format(thiscal.getTime()) + " " + week[thiscal.get(Calendar.DAY_OF_WEEK) - 1] + "요일");
		try {
			showInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		movieAll[0][0] = new JLabel("New label");
		movieAll[0][0].setBounds(66, 10, 140, 100);
		panel_2.add(movieAll[0][0]);

		movieAll[0][1] = new JLabel("New label");
		movieAll[0][1].setBounds(230, 10, 140, 100);
		panel_2.add(movieAll[0][1]);

		movieAll[0][2] = new JLabel("New label");
		movieAll[0][2].setBounds(398, 10, 140, 100);
		panel_2.add(movieAll[0][2]);

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

		movieAll[1][0] = new JLabel("New label");
		movieAll[1][0].setBounds(66, 10, 140, 100);
		panel_3.add(movieAll[1][0]);

		movieAll[1][1] = new JLabel("New label");
		movieAll[1][1].setBounds(221, 10, 140, 100);
		panel_3.add(movieAll[1][1]);

		movieAll[1][2] = new JLabel("New label");
		movieAll[1][2].setBounds(397, 10, 140, 100);
		panel_3.add(movieAll[1][2]);

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

		movieAll[2][0] = new JLabel("New label");
		movieAll[2][0].setBounds(66, 10, 140, 100);
		panel_4.add(movieAll[2][0]);

		movieAll[2][1] = new JLabel("New label");
		movieAll[2][1].setBounds(218, 10, 140, 100);
		panel_4.add(movieAll[2][1]);

		movieAll[2][2] = new JLabel("New label");
		movieAll[2][2].setBounds(396, 10, 140, 100);
		panel_4.add(movieAll[2][2]);

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

			}
		}

	}

	public void readScadule() throws Exception {

		// System.out.println(format2.format(curcal.getTime()) +
		// ":"+format2.format(thiscal.getTime()));

		Calendar temp = (Calendar) thiscal.clone();

		for (RoomNum = 1; RoomNum < 4; RoomNum++) {

			File f = new File("scedule\\" + RoomNum + "_" + thisday + ".txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "euc-kr"));
			// FileReader br = new FileReader(f);
			// euc-kr을 utf-8로 바꿔서 읽는 코딩 필요!

			starttime = "09:00";
			endingtime = "";

			String str;

			ArrayList<String[]> movies = new ArrayList<String[]>();

			while ((str = br.readLine()) != null) {
				String[] movie = new String[3];
				// System.out.println(str);
				StringTokenizer st = new StringTokenizer(str, "/");
				st.nextToken();

				movie[0] = st.nextToken();
				temp.setTime(format3.parse(starttime));
				movie[1] = format3.format(temp.getTime());

				movieRunnigtime = st.nextToken();

				temp.add(Calendar.MINUTE, Integer.parseInt(movieRunnigtime));
				endingtime = format3.format(temp.getTime());

				temp.add(Calendar.MINUTE, 30);
				starttime = format3.format(temp.getTime());

				movie[2] = endingtime;
				movies.add(movie);
			}
			room.add(movies);
		}
	}

	public void showInfo() throws Exception {

		readScadule();

		for(int i = 0 ; i < 3 ; i++)
			for(int j = 0 ; j< 4 ; j++)
				movieAll[i][j].setText("");
		
	
		Calendar temp = (Calendar) thiscal.clone();
		// System.out.println(format2.format(curcal.getTime()) + ":"+
		// format2.format(thiscal.getTime()));

		if (format2.format(curcal.getTime()).equals(format2.format(thiscal.getTime()))) {
			temp.setTime(format3.parse(cTime));

		} else {
			temp.setTime(format3.parse("09:00"));
		}
		RoomNum = 1;
		for (; RoomNum < 4; RoomNum++) {
			for (int j = 0, i = 0; j < room.get(RoomNum - 1).size() && i < 4; j++, i++) {
				// 각 라벨에 영화 제목과 러닝타임 받아오기.
				// System.out.println(RoomNum + "" + j);
				// 각 라벨에 영화 시작시간과 끝나는 시간 보여주기					
				
				String[] movie = room.get(RoomNum - 1).get(j);

//				 System.out.println(movie[0]);
//				 System.out.println(movie[1]);
//				 System.out.println(movie[2]);
				
				if (temp.getTime().compareTo(format3.parse(movie[1])) > 0) {
					i--;
					continue;
				} else {
					String movieInfo = "<html>" + movie[1] + "~" + movie[2] + "<br>" + movie[0] + "<br>"
							+ movieRunnigtime + "</html>";
					movieAll[RoomNum - 1][i].setText(movieInfo);
				}
			} // end of for roop_'j' :영화별 회차

			for (int i = 0; i < movieAll.length; i++)
				for (int j = 0; j < movieAll[i].length; j++)
					if (movieAll[i][j].getText().equals("New label")) {
						movieAll[i][j].setText("");
						// 라밸 색 바꿔야함
					}

		}
		RoomNum = 1;
		room.clear();
	}

	// end of for roop_'roomNum' :각 상영관 전체

	// for(int i=0 ; i <room.size() ; i ++)
	// for(int j=0 ; j<room.get(i).size();j++){
	// System.out.println(room.get(i).get(j)[0]);
	// System.out.println(room.get(i).get(j)[1]);
	// }
	// end of showInfo()

	public void viewClear() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				movieAll[i][j].setText("");
			}
		}
	}

	@Override // 액션리스너 for buttons
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub
		Object evt = e.getSource();

		if (evt == btnNewButton_1) {
			thiscal.add(Calendar.DATE, -1);
			// 버튼을 setText( cal 값으로 )
			btnNewButton.setText(
					format1.format(thiscal.getTime()) + " " + week[thiscal.get(Calendar.DAY_OF_WEEK) - 1] + "요일");
			thisday = format2.format(thiscal.getTime());
			try {
				showInfo();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (evt == button) {
			thiscal.add(Calendar.DATE, 1);
			btnNewButton.setText(
					format1.format(thiscal.getTime()) + " " + week[thiscal.get(Calendar.DAY_OF_WEEK) - 1] + "요일");
			thisday = format2.format(thiscal.getTime());
			// viewClear();
			try {
				showInfo();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
		}

	}

	public class MouseHandler extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			Object evt = e.getSource();

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (evt == movieAll[i][j]) {
						curRow = i;
						curCol = j;
						String movieinfo = movieAll[i][j].getText();
						StringTokenizer st = new StringTokenizer(movieinfo, "<html><br></html>~");
						String starttime = st.nextToken();
						String endtime = st.nextToken();
						String movietitle = st.nextToken();
						String date = btnNewButton.getText();
						int selectRoomnum = curRow+1;
						TheaterMain.cardPanel.add("sv",new SeatView(movietitle,starttime,endtime,selectRoomnum,date));
						TheaterMain.card.show(TheaterMain.cardPanel, "sv");
					}
				}
			}



		}

	}

}
