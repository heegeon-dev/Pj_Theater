package view;

import javax.swing.*;
import javax.swing.plaf.synth.SynthSplitPaneUI;

import model.MovieModel;

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
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import java.awt.Font;

public class MovieView extends JPanel implements ActionListener {

	JButton btnNewButton, btnNewButton_1, button;
	JLabel lblPrev;
	int row = 3;
	int col = 4;
	int curRow;
	int curCol;
	
	MovieModel model;

	SimpleDateFormat format1, format2, format3;

	String movietitle, movieRunnigtime, movieStartTime, reserved;

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
		model = new MovieModel();
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
		btnNewButton.setText(format1.format(thiscal.getTime()) + " " + week[thiscal.get(Calendar.DAY_OF_WEEK) - 1] + "요일");
		try {
			showInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addLayout() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 750, 550);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 10, 720, 100);
		panel.add(panel_1);
		panel_1.setLayout(null);

		btnNewButton = new JButton("New button");
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		btnNewButton.setForeground(Color.LIGHT_GRAY);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(285, 45, 235, 45);
		panel_1.add(btnNewButton);

		btnNewButton_1 = new JButton("<");
		btnNewButton_1.setForeground(Color.LIGHT_GRAY);
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setBounds(198, 45, 75, 45);
		panel_1.add(btnNewButton_1);

		button = new JButton(">");
		button.setForeground(Color.LIGHT_GRAY);
		button.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button.setBackground(Color.DARK_GRAY);
		button.setBounds(539, 45, 75, 45);
		panel_1.add(button);
		
		JLabel lblCGVimg = new JLabel("");
		lblCGVimg.setIcon(new ImageIcon("img\\p1.PNG"));
		lblCGVimg.setBounds(12, 10, 130, 87);
		panel_1.add(lblCGVimg);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBounds(12, 126, 720, 110);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblTheater1img = new JLabel("1");
		lblTheater1img.setIcon(new ImageIcon("img\\p11.PNG"));
		lblTheater1img.setBounds(1, 10, 61, 90);
		panel_2.add(lblTheater1img);

		movieAll[0][0] = new JLabel("New label");
		movieAll[0][0].setBounds(66, 10, 140, 100);
		movieAll[0][0].setHorizontalAlignment(JLabel.CENTER);
		panel_2.add(movieAll[0][0]);

		movieAll[0][1] = new JLabel("New label");
		movieAll[0][1].setBounds(230, 10, 140, 100);
		movieAll[0][1].setHorizontalAlignment(JLabel.CENTER);
		panel_2.add(movieAll[0][1]);

		movieAll[0][2] = new JLabel("New label");
		movieAll[0][2].setBounds(398, 10, 140, 100);
		movieAll[0][2].setHorizontalAlignment(JLabel.CENTER);
		panel_2.add(movieAll[0][2]);

		movieAll[0][3] = new JLabel("New label");
		movieAll[0][3].setBounds(556, 10, 140, 100);
		movieAll[0][3].setHorizontalAlignment(JLabel.CENTER);
		panel_2.add(movieAll[0][3]);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBounds(12, 246, 720, 110);
		panel.add(panel_3);
		panel_3.setLayout(null);

		movieAll[1][0] = new JLabel("New label");
		movieAll[1][0].setBounds(66, 10, 140, 100);
		movieAll[1][0].setHorizontalAlignment(JLabel.CENTER);
		panel_3.add(movieAll[1][0]);

		movieAll[1][1] = new JLabel("New label");
		movieAll[1][1].setBounds(221, 10, 140, 100);
		movieAll[1][1].setHorizontalAlignment(JLabel.CENTER);
		panel_3.add(movieAll[1][1]);

		movieAll[1][2] = new JLabel("New label");
		movieAll[1][2].setBounds(397, 10, 140, 100);
		movieAll[1][2].setHorizontalAlignment(JLabel.CENTER);
		panel_3.add(movieAll[1][2]);

		movieAll[1][3] = new JLabel("New label");
		movieAll[1][3].setBounds(558, 10, 140, 100);
		movieAll[1][3].setHorizontalAlignment(JLabel.CENTER);
		panel_3.add(movieAll[1][3]);
		
		JLabel lblTheater2img = new JLabel("2");
		lblTheater2img.setIcon(new ImageIcon("img\\p12.PNG"));
		lblTheater2img.setBounds(1, 10, 61, 90);
		panel_3.add(lblTheater2img);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.setBounds(12, 366, 720, 110);
		panel.add(panel_4);
		panel_4.setLayout(null);

		movieAll[2][0] = new JLabel("New label");
		movieAll[2][0].setBounds(66, 10, 140, 100);
		movieAll[2][0].setHorizontalAlignment(JLabel.CENTER);
		panel_4.add(movieAll[2][0]);

		movieAll[2][1] = new JLabel("New label");
		movieAll[2][1].setBounds(218, 10, 140, 100);
		movieAll[2][1].setHorizontalAlignment(JLabel.CENTER);
		panel_4.add(movieAll[2][1]);

		movieAll[2][2] = new JLabel("New label");
		movieAll[2][2].setBounds(396, 10, 140, 100);
		movieAll[2][2].setHorizontalAlignment(JLabel.CENTER);
		panel_4.add(movieAll[2][2]);

		movieAll[2][3] = new JLabel("New label");
		movieAll[2][3].setBounds(558, 10, 140, 100);
		movieAll[2][3].setHorizontalAlignment(JLabel.CENTER);
		panel_4.add(movieAll[2][3]);
		
		JLabel lblTheater3img = new JLabel("3");
		lblTheater3img.setIcon(new ImageIcon("img\\p13.PNG"));
		lblTheater3img.setBounds(2, 10, 61, 90);
		panel_4.add(lblTheater3img);
		
		lblPrev = new JLabel("prev");
		lblPrev.setIcon(new ImageIcon("img\\p6.PNG"));
		lblPrev.setBounds(621, 486, 110, 35);
		panel.add(lblPrev);

	}

	public void eventProc() {
		btnNewButton_1.addActionListener(this);
		btnNewButton.addActionListener(this);
		button.addActionListener(this);
		MouseHandler mouseLsnr = new MouseHandler();
		lblPrev.addMouseListener(mouseLsnr);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				movieAll[i][j].addMouseListener(mouseLsnr);

			}
		}

	}

	//영화 스케줄 파일에서 데이터 읽어옴
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
				String[] movie = new String[4];
				// System.out.println(str);
				StringTokenizer st = new StringTokenizer(str, "/");
				movie[3] = st.nextToken();

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

	//위에서 읽어온 영화스케줄을 movie 라벨에 띄워줌
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


				if (temp.getTime().compareTo(format3.parse(movie[1])) > 0) {
					i--;
					continue;
				} else {
					
					// TODO  :예약된 좌석수 받아오기.  
					getReSeat(RoomNum, thisday, String.valueOf(movie[3]));
										
					String movieInfo = "<html>" + movie[3]+"회차"+"<br>" + movie[1] + "~" + movie[2] + "<br>" + movie[0] + "<br>"
							+ reserved+" / 80" + "</html>";
					movieAll[RoomNum - 1][i].setText(movieInfo);
				}
			} // end of for roop_'j' :영화별 회차

			for (int i = 0; i < movieAll.length; i++)
				for (int j = 0; j < movieAll[i].length; j++)
					if (movieAll[i][j].getText().equals("New label")) {
						movieAll[i][j].setText("");
						// 라밸 색 바꿔야함
					}

		}// end of for roop_'roomNum' :각 상영관 전체
		RoomNum = 1;
		room.clear();
		
	}	// end of showInfo()

	public void getReSeat(int RoomNum, String thisday, String movieRound){
		reserved =model.getReSeat(RoomNum, thisday, movieRound);
	}
	
	public void viewClear() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				movieAll[i][j].setText("");
			}
		}
	}

	@Override // 액션리스너 for buttons
	public void actionPerformed(ActionEvent e) {

		Object evt = e.getSource();

		if (evt == btnNewButton_1) {
			thiscal.add(Calendar.DATE, -1);
			// 버튼을 setText( cal 값으로 )
			btnNewButton.setText(format1.format(thiscal.getTime()) + " " + week[thiscal.get(Calendar.DAY_OF_WEEK) - 1] + "요일");
			thisday = format2.format(thiscal.getTime());
			try {
				showInfo();
			} catch (Exception e1) {
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
				e1.printStackTrace();
			}
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
						int numOfDay = Integer.parseInt(String.valueOf(st.nextToken().charAt(0)));
						String starttime = st.nextToken();
						String endtime = st.nextToken();
						String movietitle = st.nextToken();
						String date = btnNewButton.getText();
						int selectRoomnum = curRow+1;
						System.out.println(numOfDay);
						TheaterMain.cardPanel.add("stv",new SeatView(movietitle,starttime,endtime,selectRoomnum,date,numOfDay));
						TheaterMain.card.show(TheaterMain.cardPanel, "stv");
					}
				}
			}
			if ( evt == lblPrev){
				TheaterMain.card.show(TheaterMain.cardPanel, "sv");
			}



		}

	}
}
