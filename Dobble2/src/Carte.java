import java.awt.Graphics;

/**
 * Cette classe représente un carte composée de plusieurs Symboles
 * @see Symbole
 * 
 * @author Camille
 * @version 1.0
 */
public class Carte {
	
	/* Attributs */

		/**
		 * Ensemble des symboles de la carte.
		 * Pourront être modifiés.
		 * @see setSymbole(Symbole s, int i)
		 */
		private Symbole symboles[];
		
		/**
		 * L'id de la carte sera représenté par un entier corespondant à son emplacement dans le paquet
		 * @see Paquet
		 */
		private int id;
	
	/* Constructeur */
	
		/**
		 * Instanciera une carte avec un nombre de symboles près défini.
		 * Et les différents symboles.
		 * @param id int
		 * 		id de la carte
		 * @param s
		 * 		tableau contenant les numéros de carte
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
		 * 		Carte à copier
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
			 * Retournera une instanciation d'un symbole de la carte avec un indice donné.
			 * @see Symbole
			 * 
			 * @param i int
			 * 		Indice du symbole dans le tableau dans le symbole
			 * @return Symbole
			 * 		Symbole retourné
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
			 * Changera le symbole choisi avec l'indice donné
			 * @see Symbole
			 * 
			 * @param s Symbole
			 * 		Nouveau symbole
			 * @param i int
			 * 		Indice du symbole à changer
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
			
		/* Méthode */
			
			/**
			 * Redéfinition de la méthode toString pour cette classe.
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
