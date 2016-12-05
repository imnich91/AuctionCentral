package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author imnic
 *
 */
public class StaffPanel extends JPanel implements Observer {
	
	private JPanel myCenterPanel;
	
	private JPanel myButtonPanel;
		
	public StaffPanel(final JFrame theFrame) {
		setLayout(new BorderLayout());
		setupCenterPanel();
		setupSouthPanel();
		
	}
	
	private void setupCenterPanel() {
		myCenterPanel = new JPanel();
	}
	
	private void setupSouthPanel() {
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
