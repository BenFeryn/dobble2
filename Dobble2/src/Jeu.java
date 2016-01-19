import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;

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
		hauteur = 800;
		largeur = 1000;
		setSize(largeur, hauteur);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			if(screenCard[0].getSelectedSymbole().equals(screenCard[1].getSelectedSymbole())){
				bonnePaire();
			}else{
				mauvaisePaire();
			}
			refreshScore();
		}
	}

	/**
	 * M�thode appel� quand une paire de symbole est trouv�e par le joueur
	 */
	private void bonnePaire() {
		System.out.println("Nice ! GG.");
		for(int i=0;i<Csts.CARTE_FENETRE;i++){
			screenCard[i].getSelectedSymbole().setSelected(false);
		}
		score++;
		initialiseCartes();
		System.out.println("Votre score est de "+score+" points !");
	}

	/**
	 * M�thode appel� quand le joueur selectionne une fausse paire
	 */
	
	private void mauvaisePaire() {
		System.out.println("Kappa.");
		for(int i=0;i<Csts.CARTE_FENETRE;i++){
			screenCard[i].getSelectedSymbole().setSelected(false);
		}
		score--;
		initialiseCartes();
		System.out.println("Votre score est de "+score+" points !");
	}
	
	public void initTexte(){
	}
	
	public void refreshScore(){
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i=0; i < Csts.CARTE_FENETRE; i++){
			for(int j=0; j < Csts.SYMBOLES_CARTE; j++){
				if(screenCard[i].getSymbole(j) == null)break;
				if(screenCard[i].getSymbole(j).isClicked(e.getPoint())){
					System.out.println("yes "+i+" "+j);
					
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
