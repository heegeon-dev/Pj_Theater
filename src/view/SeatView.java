package view;

import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;

public class SeatView extends Panel{
	public SeatView() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel jl_Screen = new JLabel("SCREEN");
		jl_Screen.setOpaque(true);
		jl_Screen.setForeground(Color.black);
		jl_Screen.setBackground(Color.LIGHT_GRAY);
		jl_Screen.setBounds(27, 58, 560, 35);
		jl_Screen.setVerticalAlignment(SwingConstants.CENTER);
		jl_Screen.setHorizontalAlignment(SwingConstants.CENTER);
		add(jl_Screen);
		
		JLabel[][] seat = new JLabel[8][10];
		
		for(int i = 0 ; i<8 ; i ++)
			for(int j = 0 ; j < 10 ; j++){
				char row = (char)('A'+i);
				String col =String.valueOf((j+1));
				seat[i][j].setText(row+col);
				if(j>5)								//27,45 가 시작점
					seat[i][j].setBounds(27+i,); 
				add(seat[i][j]);
				
			}
		
	}
}
