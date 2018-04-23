package view;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.sun.javafx.css.PseudoClassState;

import model.ScheduleModel;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;

public class MakeScheduleView extends JPanel implements ActionListener {
	private JTable scheduleJTable;
	private JTable movieJTable;
	private JTextField selectDate;
	private JTextField txtList;
	JButton showButton, btnInsert;
	ScheduleModel model;

	JLabel roomNum;
	JButton btnPrevious, btnNext;

	ArrayList movieData = new ArrayList();

	Vector<String> columnNames = new Vector<String>();

	MyTableModel movieTablemodel, scheduleTablemodel;

	public MakeScheduleView() {
		model = new ScheduleModel();

		columnNames.add("Movie_no");
		columnNames.add("Movie_Title");
		columnNames.add("RunningTime");

		addLayout();
		eventProc();

		setSize(800, 600);
		setVisible(true);
	}

	public void addLayout() {

		setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);

		roomNum = new JLabel("1");
		roomNum.setBounds(53, 51, 40, 38);
		panel.add(roomNum);

		btnPrevious = new JButton("previous");
		btnPrevious.setBounds(33, 18, 97, 23);
		panel.add(btnPrevious);

		btnNext = new JButton("next");
		btnNext.setBounds(33, 92, 97, 23);
		panel.add(btnNext);

		selectDate = new JTextField();
		selectDate.setForeground(Color.GRAY);
		selectDate.setText("ex.0412");
		selectDate.setBounds(154, 64, 132, 51);
		panel.add(selectDate);
		selectDate.setColumns(10);
		selectDate.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selectDate.setText(" ");

			}
		});

		JLabel SelectDate = new JLabel("선택일자");
		SelectDate.setBounds(154, 37, 69, 15);
		panel.add(SelectDate);

		txtList = new JTextField();
		txtList.setBounds(424, 38, 196, 51);
		txtList.setFont(new Font("굴림", Font.PLAIN, 20));
		txtList.setText("영화 List");
		txtList.setColumns(10);
		panel.add(txtList);

		showButton = new JButton("SHOW");
		showButton.setBounds(627, 52, 97, 23);
		panel.add(showButton);

		add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 125, 760, 300);
		panel.add(panel_1);

		scheduleTablemodel = new MyTableModel();
		panel_1.setLayout(new GridLayout(1, 2));
		scheduleJTable = new JTable(scheduleTablemodel);
		scheduleJTable.setBounds(49, 125, 300, 290);
		panel_1.add(new JScrollPane(scheduleJTable));

		movieTablemodel = new MyTableModel();
		movieJTable = new JTable(movieTablemodel);
		movieJTable.setBounds(408, 125, 300, 290);
		panel_1.add(new JScrollPane(movieJTable));

		btnInsert = new JButton("INSERT");
		btnInsert.setBounds(294, 64, 82, 51);

		panel.add(btnInsert);

	}

	public void eventProc() {
		showButton.addActionListener(this);

		// movieJTable에 마우스리스너 이벤트걸기,
		// 클릭될때마다 클릭된 행을 가져와서 왼쪽 JTable에 띄우기.
		movieJTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				int row = movieJTable.getSelectedRow();
				// 컬럼에 상관없이 어떤 컬럼이든 해당 열의 값을 가져와야하기 때문에 row만 중요.
				ArrayList temp = (ArrayList) movieTablemodel.data.get(row);
				schduling(temp);
			}
		});
		btnPrevious.addActionListener(this);
		btnNext.addActionListener(this);
		selectDate.addActionListener(this);
		btnInsert.addActionListener(this);

	}

	// schedule 테이블에 오른쪽에서 클릭한 영화정보 출력하기
	public void schduling(ArrayList md) {

		scheduleTablemodel.data.add(md);
		scheduleJTable.setModel(scheduleTablemodel);
		scheduleTablemodel.fireTableStructureChanged();

	}

	// movie 테이블의 정보출력할 함수
	public void getMovieInfo() {
		try {
			ArrayList temp = model.getMovieInfo();
			movieTablemodel.data = temp;

			// list = model.getMovieInfo();
			movieJTable.setModel(movieTablemodel);
			movieTablemodel.fireTableStructureChanged();

		} catch (Exception e1) {
			System.out.println("영화정보 출력 실패" + e1.getMessage());
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();

		if (evt == showButton) {
			getMovieInfo();

		} else if (evt == btnNext) {
			if (Integer.parseInt(roomNum.getText()) >= 3)
				return;
			int temp = Integer.parseInt(roomNum.getText()) + 1;
			roomNum.setText(String.valueOf(temp));

		} else if (evt == btnPrevious) {
			if (Integer.parseInt(roomNum.getText()) <= 1)
				return;
			int temp = Integer.parseInt(roomNum.getText()) - 1;
			roomNum.setText(String.valueOf(temp));

		} else if (evt == btnInsert) {
			try {
				fileInput();
				JOptionPane.showMessageDialog(null, "스케줄이 입력되었습니다.");
				clearSchedule();
			} catch (Exception e1) {
				System.out.println("스케줄 입력 실패" + e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	// 입력한 영화관, 날짜, 그리고 ScheduleJTable의 값을 가져와서 file에 데이터 입력하기.
	public void fileInput() throws Exception {

		String thisday = selectDate.getText();
		int RoomNum = Integer.parseInt(roomNum.getText());

		// for (; RoomNum < 4; RoomNum++) {

		String fileName = "scedule\\" + RoomNum + "_" + thisday.trim() + ".txt";

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "euc-kr"));

		ArrayList movieInfo = new ArrayList();
		int ctn = 1;
		for (int i = 0; i < scheduleTablemodel.data.size(); i++) {
			ArrayList movie = (ArrayList) scheduleTablemodel.data.get(i);
			String movieOne = ctn + "/" + movie.get(1) + "/" + movie.get(2) + "\n";
			movieInfo.add(movieOne);
			// movieInfo.add(scheduleTablemodel.data.get(i)+"\n");

			bw.write(String.valueOf(movieInfo.get(i)));
			model.initScreen(RoomNum, thisday, ctn);
			ctn++;
		}

		bw.close();
		System.out.println(scheduleTablemodel.data.size());
	}
	// }

	public void clearSchedule() {
		roomNum.setText("1");
		selectDate.setText("ex.0412");
		// scheduleTablemodel.data = new ArrayList();
		scheduleTablemodel.data.clear();
		scheduleTablemodel.fireTableDataChanged();
	}

	class MyTableModel extends AbstractTableModel {

		ArrayList data = new ArrayList();
		String[] columnNames = { "Movie_No", "Movie_Title", "RunningTime" };

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		@Override
		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		public void setValueAt(Object value, int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			temp.set(col, value);
			fireTableCellUpdated(row, col);
		}

	} // end of INNER CLASS_'MyTableModel'

	public static void main(String[] args) {
		MakeScheduleView view = new MakeScheduleView();

	}
}