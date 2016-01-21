import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Scoreboard extends JFrame
{

	private JPanel mainPanel;
	private JPanel[] scoresPanel;
	private JLabel[] scoresTexte;
	private JButton backToTheMenu;
	
	public Scoreboard() 
	{
		super("Dobble - Scoreboard");
		String[] scores = GameSave.scoreReader();
		
		int nbScores = scores.length;
		
		mainPanel = new JPanel(new GridLayout(nbScores+1,1));
		scoresPanel = new JPanel[nbScores];
		scoresTexte = new JLabel[nbScores];
		
		backToTheMenu = new JButton("Close the tab");
		backToTheMenu.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				finish();
			}
		});
		
		for(int i = 0; i < nbScores ; i++)
		{
			scoresTexte[i] = new  JLabel(scores[i]);
			scoresPanel[i] = new JPanel(); scoresPanel[i].add(scoresTexte[i]);
			mainPanel.add(scoresPanel[i]);
		}
		mainPanel.add(backToTheMenu);
		getContentPane().add(mainPanel);
		
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
