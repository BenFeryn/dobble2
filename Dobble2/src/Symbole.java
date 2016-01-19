/**
 * Cette classe répresente un symbole dans une carte.
 * Un symbole est une image.
 * Le nom de l'image sera sous la forme Symbole_n
 * @see Carte
 * 
 * @version 1.0
 * @author Camille
 */
public class Symbole {
	
	/* Attributs */
		
		/**
		 * Cette valeur permettra de trouver le lien de l'image
		 * Peut être modifiée
		 * @see setValeurSymbole(int t)
		 */
		private int valeurSymbole;
		
		/**
		 * Le lien vers l'image du symbole.
		 * Peut être modifié.
		 * @see setLienImg(String str)
		 */
		private String lienImg[];
		
		
		/* Constructeur */
		

		/**
		 * Instanciera un symbole avec la valeur donnée grâce au nom de l'image qui sera "Symbole_valeurSymbole"
		 * @param i int
		 * 		Entier correspondant à une image
		 */
		public Symbole(int i) {
			valeurSymbole = i;
			lienImg = new String[4];
			for(int j=0;j<4;j++)
				lienImg[j] = new String("Symbole_"+valeurSymbole+"_"+j);
		}
		
		public Symbole(Symbole s) {
			this.valeurSymbole = s.valeurSymbole;
			this.lienImg = s.lienImg;
		}
	
	/* Accesseurs */
		
		/* GET */

			/**
			 * Récupérer la valeur du symbole
			 * @return int
			 * 		valeur du symbole
			 */
			public int getValeurSymbole(){
				return valeurSymbole;
			}
			
			/**
			 * Retourner le lien de l'image
			 * @return lienImg String
			 * 		Lien vers l'image du symbole
			 */
			public String getLienImg(int i){
				return lienImg[i];
			}
		
		/* SET */
			
			/**
			 * Changer la valeur du symbole et le lien
			 * @param t int
			 * 		Nouvelle valeur symbole
			 */
			public void setValeurSymbole(int t){
				valeurSymbole = t;
				for(int j=0;j<4;j++)
					lienImg[j] = new String("Symbole_"+valeurSymbole+"_"+j);
			}
		
	/* Méthodes */
			
			/**
			 * Redéfinition de la méthode equals pour cette classe
			 * @param s Symbole
			 * 		Symbole à comparer
			 * @return boolean
			 * 		Si c'est egale ou non
			 */
			public boolean equals(Symbole s){
				return this.valeurSymbole == s.valeurSymbole;
			}
			
			/**
			 * Redéfinition de la méthode toString.
			 * Retour la valeur du symbole
			 */
			public String toString(){
				return "Valeur du symbole : "+valeurSymbole;
			}
	
}
