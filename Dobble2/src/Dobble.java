import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Dobble extends JFrame{
	
	static JButton play;
	static JButton option;
	static JButton scoreboard;
	static JButton leave;
	private JPanel mainPanel, topPanel, midPanel, botPanel;
	
	public Dobble(){
		super("Dobble - Menu");
		setSize(100, 175);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		play = new JButton("Jouer");
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Jeu();
				play.setEnabled(false);
			}
		});
		topPanel = new JPanel();
		mainPanel.add(play);
		
		option = new JButton("Option");
		option.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Just do it !");
			}
		});
		scoreboard = new JButton("Scores");
		scoreboard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Don't let your dreams be dreams !");
			}
		});
		midPanel = new JPanel();
		mainPanel.add(option);mainPanel.add(scoreboard);
		
		leave = new JButton("Quitter");
		leave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		botPanel = new JPanel();
		mainPanel.add(leave);
		
		getContentPane().add(mainPanel);
		
		setLocation(100, 100);
		setResizable(false);
		setVisible(true);
	}
	
}
