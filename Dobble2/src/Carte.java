import java.awt.Graphics;

/**
 * Cette classe repr�sente un carte compos�e de plusieurs Symboles
 * @see Symbole
 * 
 * @author Camille
 * @version 1.0
 */
public class Carte {
	
	/* Attributs */

		/**
		 * Ensemble des symboles de la carte.
		 * Pourront �tre modifi�s.
		 * @see setSymbole(Symbole s, int i)
		 */
		private Symbole symboles[];
		
		/**
		 * L'id de la carte sera repr�sent� par un entier corespondant � son emplacement dans le paquet
		 * @see Paquet
		 */
		private int id;
	
	/* Constructeur */
	
		/**
		 * Instanciera une carte avec un nombre de symboles pr�s d�fini.
		 * Et les diff�rents symboles.
		 * @param id int
		 * 		id de la carte
		 * @param s
		 * 		tableau contenant les num�ros de carte
		 */
		public Carte(int id, int s[]) {
			this.id = id;
			
			symboles = new Symbole[Csts.SYMBOLES_CARTE];
			for(int i=0;i<Csts.SYMBOLES_CARTE;i++){
				symboles[i] = new Symbole(s[i]);
			}
		}
		
		/**
		 * Copiera une carte pour en faire une autre
		 * @param c Carte
		 * 		Carte � copier
		 */
		public Carte(Carte c){
			this.id = c.id;
			
			symboles = new Symbole[Csts.SYMBOLES_CARTE];
			for(int i=0;i<Csts.SYMBOLES_CARTE;i++){
				this.symboles[i] = c.symboles[i];
			}
		}
	
	/* Accesseurs */
		
		/* GET */
		
			/**
			 * Retournera une instanciation d'un symbole de la carte avec un indice donn�.
			 * @see Symbole
			 * 
			 * @param i int
			 * 		Indice du symbole dans le tableau dans le symbole
			 * @return Symbole
			 * 		Symbole retourn�
			 */
			public Symbole getSymbole(int i){
				return symboles[i];
			}
			
			/**
			 * Retournera l'id de la carte
			 * @return id int
			 * 		id de la carte
			 */
			public int getId(){
				return id;
			}
		
		/* SET */
			
			/**
			 * Changera le symbole choisi avec l'indice donn�
			 * @see Symbole
			 * 
			 * @param s Symbole
			 * 		Nouveau symbole
			 * @param i int
			 * 		Indice du symbole � changer
			 */
			public void setSymbole(Symbole s, int i){
				symboles[i] = new Symbole(s);
			}
			
			/**
			 * Changera l'id de la carte
			 * @param i int
			 * 		nouvel id
			 */
			public void setId(int i){
				id = i;
			}
			
		/* M�thode */
			
			/**
			 * Red�finition de la m�thode toString pour cette classe.
			 * Elle affichera le contenu de la carte.
			 */
			public String toString(){
				String str = new String("Contenu de la carte "+id+": \n");
				for(int  i=0;i<Csts.SYMBOLES_CARTE;i++){
					str += symboles[i].toString()+"\n";
				}
				
				return str;
			}

		
}
