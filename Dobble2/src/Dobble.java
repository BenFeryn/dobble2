import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;



@SuppressWarnings("serial")
public class Dobble extends JFrame{
	
	static JButton play;
	static JButton option;
	static JButton scoreboard;
	static JButton leave;
	static Options parameters;
	private JPanel mainPanel;
	
	public Dobble(){
		super("Dobble - Menu");
		getContentPane().setBackground(new Color(200, 200, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/ico/icone.png"));
		//get local graphics environment
		GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
		//get maximum window bounds
		Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();
		int hauteur = (int) maximumWindowBounds.getHeight();
		int largeur = (int) maximumWindowBounds.getWidth();
		setSize(largeur, hauteur);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parameters = new Options();
		//getContentPane().add(new Background("./img/ico/Wallpaperdobble.png"));
		mainPanel = new JPanel(new GridLayout(4, 1));
		mainPanel.setBorder(new EmptyBorder(this.getHeight()/6,this.getWidth()/6,this.getHeight()/6,this.getWidth()/6));
		play = new JButton("Jouer");
		play.setFont(new Font("SANS_SERIF", Font.BOLD, 50));
		play.setBackground(new Color(132,112,255));
		play.setBorder(new LineBorder(new Color(255,255,255)));
		play.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				play.setBackground(new Color(72,61,139));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
				play.setBackground(new Color(132,112,255));
		    }
		});
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Jeu().addWindowListener(new WindowListener() {
					
					@Override
					public void windowOpened(WindowEvent e) {
						setVisible(false);
						
					}
					
					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowClosing(WindowEvent e) {
						GameSave.saveScore();
						play.setEnabled(true);
						setVisible(true);
					}
					
					@Override
					public void windowClosed(WindowEvent e) {
					
					}
					
					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				play.setEnabled(false);
			}
		});
		mainPanel.add(play);
		
		option = new JButton("Option");
		option.setFont(new Font("SANS_SERIF", Font.BOLD, 50));
		option.setBackground(new Color(132,112,255));
		option.setBorder(new LineBorder(new Color(255,255,255)));
		option.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				option.setBackground(new Color(72,61,139));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
				option.setBackground(new Color(132,112,255));
		    }
		});
		option.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				option.setEnabled(false);
				new OptionFrame().addWindowListener(new WindowListener() {
					
					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowClosing(WindowEvent e) {
						option.setEnabled(true);
					}
					
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		});
		scoreboard = new JButton("Scores");
		scoreboard.setFont(new Font("SANS_SERIF", Font.BOLD, 50));
		scoreboard.setBackground(new Color(132,112,255));
		scoreboard.setBorder(new LineBorder(new Color(255,255,255)));
		scoreboard.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				scoreboard.setBackground(new Color(72,61,139));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
				scoreboard.setBackground(new Color(132,112,255));
		    }
		});
		scoreboard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(GameSave.scoreReader() != null)
				{
					scoreboard.setEnabled(false);
					new Scoreboard().addWindowListener(new WindowListener() {
						
						@Override
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void windowClosing(WindowEvent e) {
							scoreboard.setEnabled(true);
						}
						
						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
				}
			}
		});
		mainPanel.add(option);mainPanel.add(scoreboard);
		
		leave = new JButton("Quitter");
		leave.setFont(new Font("SANS_SERIF", Font.BOLD, 50));
		leave.setBorder(new LineBorder(new Color(255,255,255)));
		leave.setBackground(new Color(132,112,255));
		leave.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				leave.setBackground(new Color(72,61,139));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
				leave.setBackground(new Color(132,112,255));
		    }
		});
		leave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mainPanel.add(leave);
		
		getContentPane().add(mainPanel);
		
		//setLocation(100, 100);
		setMinimumSize(new Dimension(500,500));
		setVisible(true);
	}
	
}
