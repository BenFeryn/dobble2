import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
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
		((JComponent) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
		
		titleTimer = new JLabel("Change the timer : ");
		titleTimer.setBorder(new EmptyBorder(0,0,20,0));
		getContentPane().add(titleTimer,BorderLayout.NORTH);
		
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
		getContentPane().add(timerPanel,BorderLayout.CENTER);
		
		save = new JButton("Close the tab");
		save.addActionListener(this);
		getContentPane().add(save, BorderLayout.SOUTH);
		
		setVisible(true);
		setLocationRelativeTo(null);
		pack();
	}

		protected void finish() 
		{
			WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
		}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == save)
		{
			finish();
		}else
			Dobble.parameters.setTimer(Integer.parseInt(arg0.getActionCommand()));
	}

}
