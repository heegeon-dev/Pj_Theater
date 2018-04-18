package view;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class MovieView extends JPanel implements ActionListener {
	
	JButton btnNewButton, btnNewButton_1, button;
	JLabel lblNewLabel, lblNewLabel_1, label_2, label_3, label_4, 
	label_5, label_6, label_7, label_8, label_9, label_10, label_11, label_12;
	
	public MovieView() {
		addLayout();
		eventProc();
		
	}

	public void addLayout() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setSize(800, 600);
		panel.setBounds(12, 10, 438, 280);
		add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 10, 414, 62);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(160, 10, 97, 31);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("<");
		btnNewButton_1.setBounds(124, 14, 26, 23);
		panel_1.add(btnNewButton_1);

		JButton button = new JButton(">");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(263, 14, 26, 23);
		panel_1.add(button);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 10, 53, 42);
		panel_1.add(panel_5);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 82, 414, 62);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("제1관");
		lblNewLabel.setBounds(12, 10, 35, 42);
		panel_2.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(66, 10, 65, 42);
		panel_2.add(lblNewLabel_1);

		JLabel label_2 = new JLabel("New label");
		label_2.setBounds(150, 10, 65, 42);
		panel_2.add(label_2);

		JLabel label_3 = new JLabel("New label");
		label_3.setBounds(243, 10, 65, 42);
		panel_2.add(label_3);

		JLabel label_4 = new JLabel("New label");
		label_4.setBounds(337, 10, 65, 42);
		panel_2.add(label_4);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 145, 414, 62);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel label = new JLabel("제2관");
		label.setBounds(12, 10, 35, 42);
		panel_3.add(label);

		JLabel label_5 = new JLabel("New label");
		label_5.setBounds(66, 10, 65, 42);
		panel_3.add(label_5);

		JLabel label_6 = new JLabel("New label");
		label_6.setBounds(150, 10, 65, 42);
		panel_3.add(label_6);

		JLabel label_7 = new JLabel("New label");
		label_7.setBounds(243, 10, 65, 42);
		panel_3.add(label_7);

		JLabel label_8 = new JLabel("New label");
		label_8.setBounds(337, 10, 65, 42);
		panel_3.add(label_8);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(12, 208, 414, 62);
		panel.add(panel_4);
		panel_4.setLayout(null);

		JLabel label_1 = new JLabel("제3관");
		label_1.setBounds(12, 10, 35, 42);
		panel_4.add(label_1);

		JLabel label_9 = new JLabel("New label");
		label_9.setBounds(66, 10, 65, 42);
		panel_4.add(label_9);

		JLabel label_10 = new JLabel("New label");
		label_10.setBounds(150, 10, 65, 42);
		panel_4.add(label_10);

		JLabel label_11 = new JLabel("New label");
		label_11.setBounds(243, 10, 65, 42);
		panel_4.add(label_11);

		JLabel label_12 = new JLabel("New label");
		label_12.setBounds(337, 10, 65, 42);
		panel_4.add(label_12);

	}

	public void eventProc(){
		btnNewButton_1.addActionListener(this);
		btnNewButton.addActionListener(this);
		button.addActionListener(this);
		
		MouseHandler mouseLsnr = new MouseHandler();
		lblNewLabel_1.addMouseListener(mouseLsnr);
		label_2.addMouseListener(mouseLsnr);
		label_3.addMouseListener(mouseLsnr);
		label_4.addMouseListener(mouseLsnr);
		label_5.addMouseListener(mouseLsnr);
		label_6.addMouseListener(mouseLsnr);
		label_7.addMouseListener(mouseLsnr);
		label_8.addMouseListener(mouseLsnr);
		label_9.addMouseListener(mouseLsnr);
		label_10.addMouseListener(mouseLsnr);
		label_11.addMouseListener(mouseLsnr);
		label_12.addMouseListener(mouseLsnr);
		
		
	}
	

	
	@Override  //액션리스너 for buttons
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	public class MouseHandler extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("label");
		}

		
	}

}
