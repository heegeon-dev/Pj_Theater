package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.LoginModel;
import model.PrintModel;
import javax.swing.JPasswordField;

public class LoginView extends JPanel {
	JTextField tf_id;
	JLabel lblLoginDesc,lblUserIcon, lblPw,lblLoginButton,lblLoginTitle;

	LoginModel model;
	JPasswordField pf_pw;
	
	public LoginView() {
		
		addLayout();
		eventProc();
		connectDB();

	}
	
	void eventProc(){
		MyMouseListener mc = new MyMouseListener();
		lblLoginButton.addMouseListener(mc);
		tf_id.addMouseListener(mc);
		pf_pw.addMouseListener(mc);
	}
	
	class MyMouseListener extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			Object evt = e.getSource();
			if(evt == lblLoginButton){
				try {
					if(tf_id.getText().equals(model.login().get(0)) && pf_pw.getText().equals(model.login().get(1))){
						JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
						TheaterMain.card.show(TheaterMain.cardPanel2, "msv");
						tf_id.setForeground(Color.LIGHT_GRAY);
						tf_id.setFont(new Font("맑은 고딕", Font.BOLD, 12));
						tf_id.setText("아이디 입력");
						
						pf_pw.setText("");
					}else{
						JOptionPane.showMessageDialog(null, "로그인 실패");
					}
						
				} catch (Exception e1) {
					System.out.println("로그인 실패 : ");
					e1.printStackTrace();
				}
//				TheaterMain.card.show(TheaterMain.cardPanel, "mv");
			}else if( evt == tf_id ){
				tf_id.setText("");
				tf_id.setForeground(Color.BLACK);
			}else if( evt == pf_pw){

			}
		}
	}
	void connectDB(){
		try {
			model = new LoginModel();
			System.out.println("DB연결 성공");
		} catch (Exception e) {
			System.out.println("DB연결 실패 : "+e.getMessage());
		}
		
	}
	public void addLayout(){
		setBackground(Color.WHITE);
		setLayout(null);
		
		lblLoginDesc = new JLabel("아이디 비밀번호를 입력하신 후, 로그인 버튼을 클릭해 주세요.");
		lblLoginDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginDesc.setForeground(Color.DARK_GRAY);
		lblLoginDesc.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		lblLoginDesc.setBounds(230, 198, 325, 24);
		add(lblLoginDesc);
		
		lblUserIcon = new JLabel("");
		lblUserIcon.setIcon(new ImageIcon("C:img\\p21.PNG"));
		lblUserIcon.setBounds(230, 232, 38, 37);
		add(lblUserIcon);
		
		lblPw = new JLabel("");
		lblPw.setIcon(new ImageIcon("img\\p22.PNG"));
		lblPw.setBounds(230, 271, 38, 37);
		add(lblPw);
		
		lblLoginButton = new JLabel("");
		lblLoginButton.setIcon(new ImageIcon("img\\p23.PNG"));
		lblLoginButton.setBounds(230, 318, 325, 52);
		add(lblLoginButton);
		
		tf_id = new JTextField();
		tf_id.setForeground(Color.LIGHT_GRAY);
		tf_id.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tf_id.setText("아이디 입력");
		tf_id.setBounds(270, 232, 285, 37);
		add(tf_id);
		tf_id.setColumns(10);
		
		lblLoginTitle = new JLabel("CGV 관리자 로그인");
		lblLoginTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginTitle.setOpaque(true);
		lblLoginTitle.setForeground(Color.WHITE);
		lblLoginTitle.setBackground(Color.DARK_GRAY);
		lblLoginTitle.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblLoginTitle.setBounds(230, 152, 325, 43);
		add(lblLoginTitle);
		
		pf_pw = new JPasswordField();
		pf_pw.setForeground(Color.BLACK);
		pf_pw.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		pf_pw.setBounds(270, 271, 285, 37);
		add(pf_pw);
	}
}
