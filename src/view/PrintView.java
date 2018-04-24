package view;

	
	import javax.swing.JPanel;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.ImageIcon;
	import javax.swing.JTextField;
	import java.awt.Color;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.border.BevelBorder;
	import javax.swing.border.LineBorder;

import model.PrintModel;

import java.awt.Font;
import javax.swing.SwingConstants;

	public class PrintView extends JPanel {
		JTextField tf_tel;
		JPanel p_receipt;
		JLabel lblPrint,lblMobile,lblSend;	// 버튼으로 사용 
		JLabel lblCGV,lblTelIcon;	// 이미지 아이콘
		JLabel lblPrintInfo1,lblPrintInfo2,lblPrintInfo3,lblPrintInfo4,lblPrintInfo5;
		JLabel lblPrintInfoLine1,lblPrintInfoLine2,lblPrintInfoLine3,lblPrintInfoLine4;
		JLabel lblPrintInfoTitle,lblPrintInfoDate,lblPrintInfoTime,lblPrintInfoTheaterNum,lblPrintInfoSeat;
		JLabel lblPrintInfoMoney,lblPrintInfoMember,lblPrintInfoPoint;
		String movietitle, starttime,  endtime,date;
		ArrayList<String> selectedSeat;
		int person;
		int selectRoomnum, numOfDay;
		
		PrintModel model;
		JLabel lblPrev;
		
		
		public PrintView(String movietitle, String starttime, String endtime, ArrayList<String> selectedSeat,
				int person, int selectRoomnum,String date,int numOfDay) {
			this.movietitle = movietitle;
			this.starttime = starttime;
			this.endtime = endtime;
			this.selectedSeat = selectedSeat;
			this.person = person;
			this.selectRoomnum = selectRoomnum;
			this.date= date;
			this.numOfDay = numOfDay;
			setBackground(Color.WHITE);
			addLayout();
			eventProc();
			connectDB();
		}

		void eventProc(){
			MyMouseListener mc = new MyMouseListener();
			lblPrint.addMouseListener(mc);
			lblMobile.addMouseListener(mc);
			tf_tel.addMouseListener(mc);
			lblSend.addMouseListener(mc);
			lblPrev.addMouseListener(mc);
		}
		
		void connectDB(){
			try {
				model = new PrintModel();
				System.out.println("DB연결 성공");
			} catch (Exception e) {
				System.out.println("DB연결 실패 : "+e.getMessage());
			}
			
		}
		class MyMouseListener extends MouseAdapter{
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Object evt = e.getSource();
				if(evt == lblPrint){
					JOptionPane.showMessageDialog(null, "출력이 완료되었습니다.");
				}else if (evt == lblMobile){
					
						tf_tel.setVisible(!tf_tel.isVisible());
						lblTelIcon.setVisible(!lblTelIcon.isVisible());
						lblSend.setVisible(!lblSend.isVisible());
						tf_tel.setText("전화번호 입력");
				}else if (evt == tf_tel){
					tf_tel.setText("");
				}else if(evt == lblSend){
					try {
						String tel = tf_tel.getText();
						model.insertTel(movietitle, starttime, endtime, selectedSeat, person, selectRoomnum, date, tel, numOfDay);
					} catch (Exception e1) {
						System.out.println("전화번호가 등록 되지 않았습니다. : "+e1.getMessage());
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "전화번호가 전송되었습니다.");
				}else if (evt == lblPrev){
					TheaterMain.card.show(TheaterMain.cardPanel, "pv");
				}

			}
		}	// inner class
		
		
		void addLayout(){
			setLayout(null);
			
			lblCGV = new JLabel("");
			lblCGV.setIcon(new ImageIcon("img\\p1.PNG"));
			lblCGV.setBounds(47, 41, 130, 87);
			add(lblCGV);
			
			p_receipt = new JPanel();
			p_receipt.setBackground(Color.WHITE);
			p_receipt.setBorder(new LineBorder(new Color(64, 64, 64)));
			p_receipt.setBounds(189, 41, 360, 460);
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
			lblPrintInfoTitle.setBounds(12, 98, 174, 27);
			p_receipt.add(lblPrintInfoTitle);
			
			lblPrintInfoDate = new JLabel(date);
			lblPrintInfoDate.setForeground(Color.DARK_GRAY);
			lblPrintInfoDate.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblPrintInfoDate.setBounds(12, 135, 174, 27);
			p_receipt.add(lblPrintInfoDate);
			
			lblPrintInfoTime = new JLabel(starttime +" ~ "+ endtime);
			lblPrintInfoTime.setForeground(Color.DARK_GRAY);
			lblPrintInfoTime.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblPrintInfoTime.setBounds(12, 172, 174, 27);
			p_receipt.add(lblPrintInfoTime);
			
			lblPrintInfoTheaterNum = new JLabel(selectRoomnum+"관");
			lblPrintInfoTheaterNum.setForeground(Color.DARK_GRAY);
			lblPrintInfoTheaterNum.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblPrintInfoTheaterNum.setBounds(12, 209, 45, 27);
			p_receipt.add(lblPrintInfoTheaterNum);
			
			lblPrintInfoSeat = new JLabel(selectedSeat.toString());
			lblPrintInfoSeat.setForeground(Color.DARK_GRAY);
			lblPrintInfoSeat.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblPrintInfoSeat.setBounds(69, 209, 240, 27);
			p_receipt.add(lblPrintInfoSeat);
			
			lblPrintInfoLine2 = new JLabel("===========================");
			lblPrintInfoLine2.setForeground(Color.DARK_GRAY);
			lblPrintInfoLine2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblPrintInfoLine2.setBounds(12, 241, 318, 27);
			p_receipt.add(lblPrintInfoLine2);
			
			lblPrintInfo3 = new JLabel("결제금액");
			lblPrintInfo3.setForeground(Color.DARK_GRAY);
			lblPrintInfo3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblPrintInfo3.setBounds(12, 272, 67, 27);
			p_receipt.add(lblPrintInfo3);
			
			lblPrintInfoMoney = new JLabel(Integer.toString(person*10000));
			lblPrintInfoMoney.setForeground(Color.DARK_GRAY);
			lblPrintInfoMoney.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblPrintInfoMoney.setBounds(91, 272, 67, 27);
			p_receipt.add(lblPrintInfoMoney);
			
			lblPrintInfoMember = new JLabel(String.valueOf(person)+"명");
			lblPrintInfoMember.setForeground(Color.DARK_GRAY);
			lblPrintInfoMember.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblPrintInfoMember.setBounds(170, 272, 67, 27);
			p_receipt.add(lblPrintInfoMember);
			
			lblPrintInfoLine3 = new JLabel("-------------------------------------------");
			lblPrintInfoLine3.setForeground(Color.DARK_GRAY);
			lblPrintInfoLine3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblPrintInfoLine3.setBounds(12, 309, 318, 27);
			p_receipt.add(lblPrintInfoLine3);
			
			lblPrintInfo4 = new JLabel("가용포인트");
			lblPrintInfo4.setForeground(Color.DARK_GRAY);
			lblPrintInfo4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblPrintInfo4.setBounds(12, 334, 81, 27);
			p_receipt.add(lblPrintInfo4);
			
			lblPrintInfoPoint = new JLabel("\"0000점\"");
			lblPrintInfoPoint.setForeground(Color.DARK_GRAY);
			lblPrintInfoPoint.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblPrintInfoPoint.setBounds(91, 334, 67, 27);
			p_receipt.add(lblPrintInfoPoint);
			
			lblPrintInfoLine4 = new JLabel("-------------------------------------------");
			lblPrintInfoLine4.setForeground(Color.DARK_GRAY);
			lblPrintInfoLine4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblPrintInfoLine4.setBounds(12, 362, 318, 27);
			p_receipt.add(lblPrintInfoLine4);
			
			lblPrintInfo5 = new JLabel("교환 및 환불은 상영시간 전까지 영수증 지참");
			lblPrintInfo5.setForeground(Color.DARK_GRAY);
			lblPrintInfo5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblPrintInfo5.setBounds(12, 399, 318, 27);
			p_receipt.add(lblPrintInfo5);
			
			lblPrint = new JLabel("");
			lblPrint.setIcon(new ImageIcon("img\\p7.PNG"));
			lblPrint.setBounds(561, 79, 137, 49);
			add(lblPrint);
			
			lblMobile = new JLabel("");
			lblMobile.setIcon(new ImageIcon("img\\p8.PNG"));
			lblMobile.setBounds(561, 138, 137, 49);
			add(lblMobile);
			
			tf_tel = new JTextField();
			tf_tel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			tf_tel.setBackground(Color.WHITE);
			tf_tel.setForeground(Color.LIGHT_GRAY);
			tf_tel.setText("전화번호 입력");
			tf_tel.setBounds(611, 197, 116, 21);
			add(tf_tel);
			tf_tel.setColumns(10);
			tf_tel.setVisible(false);
			
			
			lblTelIcon = new JLabel("");
			lblTelIcon.setIcon(new ImageIcon("img\\p9.PNG"));
			lblTelIcon.setBounds(583, 197, 27, 21);
			add(lblTelIcon);
			lblTelIcon.setVisible(false);
			
			lblSend = new JLabel("New label");
			lblSend.setIcon(new ImageIcon("img\\p10.PNG"));
			lblSend.setBounds(727, 197, 42, 21);
			add(lblSend);
			
			lblPrev = new JLabel("");
			lblPrev.setIcon(new ImageIcon("img\\p6.PNG"));
			lblPrev.setHorizontalAlignment(SwingConstants.LEFT);
			lblPrev.setBounds(611, 466, 110, 35);
			add(lblPrev);
			lblSend.setVisible(false);
		}// end of addLayout
	}// end of class


