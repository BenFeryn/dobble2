import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Classe reprï¿½sentant le mode de jeu basique.
 * Deux cartes ï¿½ l'ï¿½cran, d'un cï¿½tï¿½ la carte du joueur et de l'autre le reste du paquet.
 * Si le joueur trouve le symbole en commun entre sa carte et celle au dessus du paquet alors la carte du paquet vient se placer et dessus de sa carte
 * Et une nouvelle carte apparait sur le paquet
 * Les cartes sont toutes dans le dï¿½sordre
 * Le joueur devra selectionner les symboles en commun sur les <strong>DEUX CARTES</strong> pour valider son coup
 * 
 * @author Camille
 * @version 2.0
 */
@SuppressWarnings("serial")
public class Jeu extends JFrame implements MouseListener{

	private static Paquet p;
	
	private DrawableCard screenCard[];
	
	public static int hauteur, largeur;
	
	/**
	 * Index des cartes, utilisï¿½ pour le mï¿½lange
	 */
	private int indexCartes[];
	
	/**
	 * Point qui seront utilisï¿½s pour positionner les cartes sur l'ï¿½cran
	 */
	public static Point positionCartes[];
	
	/**
	 * Index commun pour cibler le tableau mï¿½langï¿½
	 * Il cible la prochaine carte ï¿½ afficher
	 */
	private int index;
	
	/**
	 * Gï¿½nï¿½re un Paquet de carte, la fenï¿½tre de jeu, la souris, les point oï¿½ se trouveront les cartes, mï¿½lange les cartes et place les deux premiï¿½res
	 */
	
	private static int score;
	public static String name;
	private JLabel labelScore;
	
	public Jeu()
	{
		super();
		this.addMouseListener(this);
		initFrame();
		p = new Paquet();
		
		positionCartes = new Point[Csts.CARTE_FENETRE];
		
		positionCartes[0] = new Point((int)getWidth()/4,(int)getHeight()/2);
		positionCartes[1] = new Point((int)(getWidth()*0.75),(int)getHeight()/2);
	
		index = -1;
		initialisationIndexCartes();
		
		screenCard = new DrawableCard[Csts.CARTE_FENETRE];
		initialiseCartes();
		
		score = 0;
		startTimer(Dobble.parameters.getTimer());
		
		labelScore = new JLabel("Score : "+score);
		setTitle("Dobble - Score : "+score);
		//getContentPane().add(labelScore, null);
		//labelScore.setAlignmentX(120);
		//labelScore.setLocation(100, 100);
		refreshScore();
	}
	
	public static int getScore()
	{
		return score;
	}
	
	Runnable runnable = new Runnable() 
	{
		
		@Override
		public void run()
		{
			if(isActive())
				finish();
		}
	};
	
	private void startTimer(int delaySeconds) 
	{
		  Executors.newSingleThreadScheduledExecutor().schedule(
		    runnable,
		    delaySeconds,
		    TimeUnit.SECONDS);
	}
	
	protected void finish()
	{
		do{
			name = JOptionPane.showInputDialog(this, "Votre score est de "+score+"\nQuel est vôtre nom ?(',' et ':' exclus)");
		}while(name.contains(",") || name.contains(":"));
		
		if(name.isEmpty())
			name = "defaut";
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
	    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}

	private void initFrame()
	{
		setBackground(new Color(150, 150, 255));
		getContentPane().setBackground(new Color(150, 150, 255));
		//get local graphics environment
		GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
		//get maximum window bounds
		Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();
		hauteur = (int) maximumWindowBounds.getHeight();
		largeur = (int) maximumWindowBounds.getWidth();
		setSize(largeur, hauteur);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Crï¿½e un tableau d'entier qui sera un tableau d'index pour les cartes
	 * Ce tableau sera mï¿½langer par une aure mï¿½thode
	 * @see melanCarte(int[] ar)
	 */
	private void initialisationIndexCartes()
	{
		indexCartes = new int[Csts.NB_CARTES];
		for(int i=0;i<Csts.NB_CARTES;i++)
		{
			indexCartes[i] = i;
		}
		melangeCartes(indexCartes);
		for(int i=0;i<indexCartes.length;i++)
			System.out.print(indexCartes[i]+" ");
		
		System.out.println();
	}
	
	/**
	 * Mï¿½lange un tableau d'entiers donnï¿½
	 * @param ar int[]
	 * 		Tableau d'entier ï¿½ mï¿½langer
	 */
	private static void melangeCartes(int[] ar){
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--){
			int index = rnd.nextInt(i + 1);
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}
	
	/**
	 * Initiaise les deux premiï¿½re cartes du jeu
	 * Cette mï¿½thode n'est appelï¿½e qu'une seule fois dans le constructeur
	 */
	private void initialiseCartes()
	{
		index++;
		for(int i=0;i<Csts.CARTE_FENETRE;i++)
		{
			screenCard[i] = new DrawableCard(p.getCarte(indexCartes[index+i]),(int)positionCartes[i].getX(),(int)positionCartes[i].getY(),largeur/6);
			getContentPane().add(screenCard[i]);
			revalidate();
		}
	}
	
	private int rechercheSymbole(int otherCarte, int valSymbole){
		int temp = 0;
		for(int k=0;k<Csts.SYMBOLES_CARTE;k++){
			if(valSymbole == screenCard[otherCarte].getSymbole(k).getSymbole().getValeurSymbole())
				temp = k;
		}
		return temp;
	}
	
	private void forceSelection(int otherCarte, int valSymbole){
		screenCard[otherCarte].getSymbole(rechercheSymbole(otherCarte, valSymbole)).setSelected(true);
		
		if(screenCard[0].isSelected() && screenCard[1].isSelected()){
			System.out.println("[!] wait... ");
			getContentPane().remove(screenCard[0]);
			getContentPane().remove(screenCard[1]);
			if(screenCard[0].getSelectedSymbole().equals(screenCard[1].getSelectedSymbole())){
				bonnePaire();
			}else{
				mauvaisePaire();
			}
			refreshScore();
		}
	}

	/**
	 * Mï¿½thode appelï¿½ quand une paire de symbole est trouvï¿½e par le joueur
	 */
	private void bonnePaire() {
		System.out.println("Nice ! GG.");
		for(int i=0;i<Csts.CARTE_FENETRE;i++){
			screenCard[i].getSelectedSymbole().setSelected(false);
		}
		score++;
		initialiseCartes();
		System.out.println("Votre score est de "+score+" points !");
		getContentPane().setBackground(new Color(150, 255, 150));
	}

	/**
	 * Mï¿½thode appelï¿½ quand le joueur selectionne une fausse paire
	 */
	
	private void mauvaisePaire() {
		System.out.println("Kappa.");
		for(int i=0;i<Csts.CARTE_FENETRE;i++){
			screenCard[i].getSelectedSymbole().setSelected(false);
		}
		if(score > 0)
		 score--;
		initialiseCartes();
		System.out.println("Votre score est de "+score+" points !");
		getContentPane().setBackground(new Color(255, 150, 150));
	}
	
	private void refreshScore()
	{
		labelScore.setText("Score : "+score);
		setTitle("Dobble - Score : "+score);
		revalidate();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i=0; i < Csts.CARTE_FENETRE; i++){
			for(int j=0; j < Csts.SYMBOLES_CARTE; j++){
				if(screenCard[i].getSymbole(j) == null)break;
				if(screenCard[i].getSymbole(j).isClicked(e.getPoint())){
					System.out.println("[!] Selection symbole "+screenCard[i].getSymbole(j).getSymbole().getValeurSymbole()+" de la carte "+screenCard[i].getcarte().getId());
					
					screenCard[i].getSymbole(j).setSelected(true);
					int otherCard;
					if(i == 0)
						otherCard = 1;
					else
						otherCard = 0;
					forceSelection(otherCard, screenCard[i].getSymbole(j).getSymbole().getValeurSymbole());					
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}
