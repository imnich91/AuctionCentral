package view;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StaffPanel extends JPanel implements Observer {
	public StaffPanel(final JFrame theFrame) {
		setBackground(Color.YELLOW);
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
