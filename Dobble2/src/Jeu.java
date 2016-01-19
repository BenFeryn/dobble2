import java.awt.Point;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe repr�sentant le mode de jeu basique.
 * Deux cartes � l'�cran, d'un c�t� la carte du joueur et de l'autre le reste du paquet.
 * Si le joueur trouve le symbole en commun entre sa carte et celle au dessus du paquet alors la carte du paquet vient se placer et dessus de sa carte
 * Et une nouvelle carte apparait sur le paquet
 * Les cartes sont toutes dans le d�sordre
 * Le joueur devra selectionner les symboles en commun sur les <strong>DEUX CARTES</strong> pour valider son coup
 * 
 * @author Camille
 * @version 1.0
 */
public class Jeu {

	private static Fenetre  f;
	private static Paquet p;
	private static Souris souris;
	
	/**
	 * Cartes affich�es � l'�cran
	 */
	private CarteG cartes[];
	
	/**
	 * Index des cartes, utilis� pour le m�lange
	 */
	private int indexCartes[];
	
	/**
	 * Point qui seront utilis�s pour positionner les cartes sur l'�cran
	 */
	private Point positionCartes[];
	
	/**
	 * Index commun poour cibler le tableau m�lang�
	 * Il cible la prochaine carte � afficher
	 */
	private int index;
	
	/**
	 * G�n�re un Paquet de carte, la fen�tre de jeu, la souris, les point o� se trouveront les cartes, m�lange les cartes et place les deux premi�res
	 */
	
	private int score;
	private Texte tScore;
	
	private Texte joueur;
	private Texte paquet;
	
	public Jeu(){
		p = new Paquet();
		f = new Fenetre();
		souris = new Souris(f.getHauteur());
		f.addMouseListener(souris);
		f.addMouseMotionListener(souris);
		cartes = new CarteG[Csts.CARTE_FENETRE];
		positionCartes = new Point[Csts.CARTE_FENETRE];
		
		positionCartes[0] = new Point((int)f.getMilieu().getX()/2,(int)f.getMilieu().getY());
		positionCartes[1] = new Point((int)(f.getMilieu().getX()/2+f.getMilieu().getX()),(int)f.getMilieu().getY());
	
		index = -1;
		initialisationIndexCartes();
		initialiseCartes();
		
		score = 0;
		initTexte();
		
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
		for(int i=0;i<Csts.CARTE_FENETRE;i++){
			index++;
			cartes[i] = new CarteG(p.getCarte(indexCartes[i]), positionCartes[i], f.getLargeur()/6);
			f.ajouter(cartes[i]);
			// TODO � supprimer
			//System.out.println(cartes[i].getCarte());
		}
	}
	
	private int rechercheSymbole(int iCarte, int valSymbole){
		int temp = 0;
		for(int k=0;k<Csts.SYMBOLES_CARTE;k++){
			if(cartes[iCarte].getSymboleG(k).getSymbole().getValeurSymbole() == valSymbole)
				temp = k;
		}
		return temp;
	}
	
	private void forceSelection(int iCarte, int valSymbole){
		int j = 0;
		System.out.println(cartes[iCarte].hasSymbole(valSymbole));
		if(cartes[iCarte].hasSymbole(valSymbole)){
			j = rechercheSymbole(iCarte, valSymbole);
			cartes[iCarte].selectionne(j);
		}else{
			cartes[iCarte].selectionne(j);
		}
		System.out.println("i :"+iCarte+" j : "+j);
		System.out.println("[!] Selection du symbole "+cartes[iCarte].getSymboleG(j).getSymbole().getValeurSymbole()+" de la carte "+(iCarte+1)+"("+cartes[iCarte].getCarte().getId()+")");
	}
	
	/**
	 * M�thode appel�e constamment par la m�thode joue pour selectionner les symbole s'il le faut
	 * @see joue()
	 */
	private void selection(){
		if(souris.getClicGauche()){
			for(int i=0;i<Csts.CARTE_FENETRE;i++){
				for(int j=0;j<Csts.SYMBOLES_CARTE;j++){
					if(cartes[i].getSymboleG(j).intersection(souris.getPosition())){
						System.out.println("[!] Selection du symbole "+cartes[i].getSymboleG(j).getSymbole().getValeurSymbole()+" de la carte "+(i+1)+"("+cartes[i].getCarte().getId()+")");
						cartes[i].selectionne(j);
						int iCarte;
						if(i == 0)
							iCarte = 1;
						else
							iCarte = 0;
						forceSelection(iCarte, cartes[i].getSymboleSelectionne().getSymbole().getValeurSymbole());
					}
				}
			}
		}
	}
	
	/**
	 * M�thode appel�e constamment par le Main pour faire avancer le jeu
	 */
	public void joue(){
		selection();
		if(cartes[0].getSelectionne() && cartes[1].getSelectionne()){
			System.out.println("[!] �a compare");
			if(cartes[0].getSymboleSelectionne().equals(cartes[1].getSymboleSelectionne())){
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
			cartes[i].setSelectionne(false);
			cartes[i].getSymboleSelectionne().setSelectionne(false);
		}
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
			cartes[i].getSymboleSelectionne().setSelectionne(false);
			cartes[i].setSelectionne(false);
		}
		score--;
		nouvelleCartePaquet();
		System.out.println("Votre score est de "+score+" points !");
	}

	/**
	 * M�thode qui ram�re le carte du paquet sur le tas du joueur et affiche la nouvelle carte du paquet
	 */
	private void nouvelleCartePaquet() {
		for(int i=0;i<Csts.CARTE_FENETRE;i++){
			for(int j=0;j<Csts.SYMBOLES_CARTE;j++){
				f.supprimer(cartes[i].getSymboleG(j));
				f.repaint();
			}
			f.supprimer(cartes[i]);
			cartes[i] = new CarteG(p.getCarte(indexCartes[index+i]), positionCartes[i], cartes[i].getRayon());
			f.ajouter(cartes[i]);
		}
		index++;
	}
	
	public void initTexte(){
		tScore = new Texte("Votre score : "+score, new Point(f.getLargeur()/4,f.getHauteur()/12),50);
		f.ajouter(tScore);
		
		joueur = new Texte("Joueur", new Point((int)positionCartes[0].getX()-20,(int)positionCartes[0].getY()+cartes[0].getRayon()+10), 10);
		paquet = new Texte("Paquet", new Point((int)positionCartes[1].getX()-20,(int)positionCartes[1].getY()+cartes[1].getRayon()+10), 10);
		f.ajouter(joueur);
		f.ajouter(paquet);
	}
	
	public void refreshScore(){
		tScore.setTexte("Votre score : "+score);
	}
}
