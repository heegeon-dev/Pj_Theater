package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PayView extends JPanel {
	JLabel[] lb = new JLabel[3];
	JLabel[] img = new JLabel[3];

	public PayView(int person) {
		addLayout();
		eventProc();
		
		setLayout(null);
	//	add(new payOfCash(person));
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
			for (int i = 0; i < 3; i++) {
				if (ob == lb[i]) {
					System.out.println(lb[i].getText());
					// 화면넘기기
				}
			}

		}

	}

	class payOfCredit extends JPanel {
		payOfCredit(int person) {
			setLayout(new FlowLayout());
			setBackground(Color.red);
			add(new JLabel(person+""));
			setVisible(true);
			setSize(600,400);
			
		}
	}

	class payOfCash extends JPanel {
		payOfCash(int person) {

		}
	}

	class payOfPoint extends JPanel {
		payOfPoint(int person){
			
		}
	}

}
