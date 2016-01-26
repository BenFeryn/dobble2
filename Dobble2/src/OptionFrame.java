import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class OptionFrame extends JFrame implements ActionListener
{

	private int[] possibleTimer = {1,5,15,30,60};
	
	private JLabel titleTimer;
	private JRadioButton checkTimer[];
	private JPanel timerPanel;
	private ButtonGroup radio;
	private JButton save;
	
	public OptionFrame() 
	{
		super("Dobble - Options");
		radio = new ButtonGroup();
		timerPanel = new JPanel(new GridLayout(possibleTimer.length + 1 ,2));
		checkTimer = new JRadioButton[possibleTimer.length];
		
		for(int i = 0;i < possibleTimer.length;i++)
		{
			checkTimer[i] = new JRadioButton(possibleTimer[i]+" secondes");
			radio.add(checkTimer[i]);
			checkTimer[i].setActionCommand(""+possibleTimer[i]);
			timerPanel.add(checkTimer[i]);
			checkTimer[i].addActionListener(this);
			if(possibleTimer[i] == Dobble.parameters.getTimer())
				checkTimer[i].setSelected(true);
		}
		getContentPane().add(timerPanel);
		setVisible(true);
		setLocationRelativeTo(null);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Dobble.parameters.setTimer(Integer.parseInt(arg0.getActionCommand()));
	}

}
