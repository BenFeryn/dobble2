import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe repr�sentant le mode de jeu basique.
 * Deux cartes � l'�cran, d'un c�t� la carte du joueur et de l'autre le reste du paquet.
 * Si le joueur trouve le symbole en commun entre sa carte et celle au dessus du paquet alors la carte du paquet vient se placer et dessus de sa carte
 * Et une nouvelle carte apparait sur le paquet
 * Les cartes sont toutes dans le d�sordre
 * Le joueur devra selectionner les symboles en commun sur les <strong>DEUX CARTES</strong> pour valider son coup
 * 
 * @author Camille
 * @version 2.0
 */
public class Jeu extends JFrame implements MouseListener{

	private static Paquet p;
	
	private DrawableCard screenCard[];
	private JLabel symboles[][];
	private int selectedSymbole[][];
	private boolean selected[];
	
	public static int hauteur, largeur;
	
	/**
	 * Index des cartes, utilis� pour le m�lange
	 */
	private int indexCartes[];
	
	/**
	 * Point qui seront utilis�s pour positionner les cartes sur l'�cran
	 */
	public static Point positionCartes[];
	
	/**
	 * Index commun poour cibler le tableau m�lang�
	 * Il cible la prochaine carte � afficher
	 */
	private int index;
	
	/**
	 * G�n�re un Paquet de carte, la fen�tre de jeu, la souris, les point o� se trouveront les cartes, m�lange les cartes et place les deux premi�res
	 */
	
	private int score;
	
	public Jeu(){
		super("Dobble");
		this.addMouseListener(this);
		initFrame();
		p = new Paquet();
		
		symboles = new JLabel[2][8];
		selectedSymbole = new int[2][2];
		selected = new boolean[2];
		endSelection();
		
		positionCartes = new Point[Csts.CARTE_FENETRE];
		
		positionCartes[0] = new Point((int)getWidth()/4,(int)getHeight()/2);
		positionCartes[1] = new Point((int)(getWidth()*0.75),(int)getHeight()/2);
	
		index = -1;
		initialisationIndexCartes();
		
		screenCard = new DrawableCard[Csts.CARTE_FENETRE];
		initialiseCartes();
		
		score = 0;
		initTexte();
		
	}
	
	private void initFrame(){
		hauteur = 600;
		largeur = 800;
		setSize(largeur, hauteur);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void endSelection(){
		selected[0] = false; selected[1] = false;
	}
	
	/**
	 * Cr�e un tableau d'entier qui sera un tableau d'index pour les cartes
	 * Ce tableau sera m�langer par une aure m�thode
	 * @see melanCarte(int[] ar)
	 */
	private void initialisationIndexCartes(){
		indexCartes = new int[Csts.NB_CARTES];
		for(int i=0;i<Csts.NB_CARTES;i++){
			indexCartes[i] = i;
		}
		melangeCartes(indexCartes);
		for(int i=0;i<indexCartes.length;i++)
			System.out.print(indexCartes[i]+" ");
		System.out.println();
	}
	
	/**
	 * M�lange un tableau d'entiers donn�
	 * @param ar int[]
	 * 		Tableau d'entier � m�langer
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
	 * Initiaise les deux premi�re cartes du jeu
	 * Cette m�thode n'est appel�e qu'une seule fois dans le constructeur
	 */
	private void initialiseCartes(){
		getContentPane().removeAll();
		for(int i=0;i<Csts.CARTE_FENETRE;i++){
			index++;
			screenCard[i] = new DrawableCard(p.getCarte(indexCartes[index]),(int)positionCartes[i].getX(),(int)positionCartes[i].getY(),largeur/6);
			getContentPane().add(screenCard[i]);
			revalidate();
		}
	}
	
	private int rechercheSymbole(int iCarte, int valSymbole){
		int temp = 0;
		for(int k=0;k<Csts.SYMBOLES_CARTE;k++){
		}
		return temp;
	}
	
	private void forceSelection(int iCarte, int valSymbole){
	}
	
	/**
	 * M�thode appel�e constamment par la m�thode joue pour selectionner les symbole s'il le faut
	 * @see joue()
	 */
	private void selection(){
		
	}
	
	/**
	 * M�thode appel�e constamment par le Main pour faire avancer le jeu
	 */
	public void joue(){
		selection();
		
	}

	/**
	 * M�thode appel� quand une paire de symbole est trouv�e par le joueur
	 */
	private void bonnePaire() {
		System.out.println("Nice ! GG.");
		score++;
		nouvelleCartePaquet();
		System.out.println("Votre score est de "+score+" points !");
	}

	/**
	 * M�thode appel� quand le joueur selectionne une fausse paire
	 */
	
	private void mauvaisePaire() {
		System.out.println("Kappa.");
		for(int i=0;i<Csts.CARTE_FENETRE;i++){
		}
		score--;
		nouvelleCartePaquet();
		System.out.println("Votre score est de "+score+" points !");
	}

	/**
	 * M�thode qui ram�re la carte du paquet sur le tas du joueur et affiche la nouvelle carte du paquet
	 */
	private void nouvelleCartePaquet() {
		for(int i=0;i<Csts.CARTE_FENETRE;i++){
			for(int j=0;j<Csts.SYMBOLES_CARTE;j++){
			}
		}
		index++;
	}
	
	public void initTexte(){
	}
	
	public void refreshScore(){
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		Component c = getContentPane().findComponentAt(e.getX(), e.getY());
		if (c instanceof DrawableSymbol) {
			System.out.println("yes");
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
