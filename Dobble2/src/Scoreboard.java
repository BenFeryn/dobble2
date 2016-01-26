import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class Scoreboard extends JFrame
{

	private JPanel mainPanel;
	private JLabel[] scoresTexte;
	private JButton backToTheMenu;
	
	public Scoreboard() 
	{
		super("Dobble - Scoreboard");
		String[] scores = GameSave.scoreReader();
		
		((JComponent) getContentPane()).setBorder(new EmptyBorder(10,10,10,10));
		
		mainPanel = new JPanel(new GridLayout(Csts.NB_SCORES,1));
		mainPanel.setBorder(new EmptyBorder(5,0,20,0));
		scoresTexte = new JLabel[Csts.NB_SCORES];
		
		backToTheMenu = new JButton("Close the tab");
		backToTheMenu.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				finish();
			}
		});
		
		for(int i = 0; i < Csts.NB_SCORES ; i++)
		{
			scoresTexte[i] = new  JLabel(i+1+". "+scores[i]);
			scoresTexte[i].setBorder(new EmptyBorder(5,0,5,0));
			mainPanel.add(scoresTexte[i]);
		}
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		getContentPane().add(backToTheMenu, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	protected void finish()
	{
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
	    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}

}
