import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.Timer;

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
	
	protected Timer timer;
	private static int valueTimer;
	
	private static int score;
	public static String name;
	
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

		setTitle("Dobble - Score : "+score+" - Time left : "+Dobble.parameters.getTimer());
		startTimer();
		revalidate();
	}
	
	public static int getScore()
	{
		return score;
	}
	
	private void startTimer(){
		valueTimer = Dobble.parameters.getTimer();
		int delay = 1000;
		ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		          setTitle("Dobble - Score : "+score+" - Time left : "+valueTimer);
		          valueTimer--;
		          if(valueTimer < 0)
		          {
		        	 timer.stop();
		        	 if(isActive())
		 				finish();
		          }
		      }
		  };
		  timer = new Timer(delay, taskPerformer);
		  timer.start();
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
			revalidate();
			
		}
	}

	/**
	 * Mï¿½thode appelï¿½ quand une paire de symbole est trouvï¿½e par le joueur
	 */
	private void bonnePaire() 
	{
		System.out.println("Nice ! GG.");
		for(int i=0;i<Csts.CARTE_FENETRE;i++)
		{
			screenCard[i].getSelectedSymbole().setSelected(false);
		}
		score++;
		initialiseCartes();
		System.out.println("Votre score est de "+score+" points !");
		blinking(Csts.GAGNE);
	}

	/**
	 * Mï¿½thode appelï¿½ quand le joueur selectionne une fausse paire
	 */
	
	private void mauvaisePaire() 
	{
		System.out.println("Kappa.");
		for(int i=0;i<Csts.CARTE_FENETRE;i++)
		{
			screenCard[i].getSelectedSymbole().setSelected(false);
		}
		if(score > 0)
		 score--;
		initialiseCartes();
		System.out.println("Votre score est de "+score+" points !");
		blinking(Csts.PERDU);
	}
	
	private void blinking(Color c) {
        setBackground(c);
        Timer blinkTimer = new Timer(500, new ActionListener() {
            boolean on=false;
            public void actionPerformed(ActionEvent e) {
                // blink the button background on and off
                setBackground( on ? Csts.NEUTRE : null);
                on = !on;
            }
        });
        blinkTimer.start();
    }


	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i=0; i < Csts.CARTE_FENETRE; i++){
			for(int j=0; j < Csts.SYMBOLES_CARTE; j++){
				if(screenCard[i].getSymbole(j) == null)break;
				if(screenCard[i].getSymbole(j).isClicked(e.getPoint())){
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
