import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Cette classe repr�sente un paquet de cartes.
 * Le contenu des cartes est d�termin� dans cette classe.
 * Il devra resp�cter les r�gles du jeu Dooble.
 * 
 * @author Camille
 * @version 1.0
 */
public class Paquet{
	
	/* Attributs */
	
	/**
	 * Instanciation des cartes dans le paquet.
	 * @see Carte
	 * Pourra �tre modifi�
	 * @see setCarte(int i, Carte c)
	 */
	private Carte cartes[];
	private int matriceSymboles[][];
	
	/* Constructeur */
	
	/**
	 * Instanciera les cartes et leur contenu
	 */
	public Paquet(){
		cartes = new Carte[Csts.NB_CARTES];
		
		/*for(int i=0;i<Csts.NB_CARTES;i++){
			int[] c = {0,1,2,3,4,5,6,7};
			cartes[i] = new Carte(i,c);
		}*/
		matriceSymboles = new int[Csts.NB_CARTES][Csts.SYMBOLES_CARTE];
		creationSymboles();
		InitCartes();
		
	}
	
	/* Accesseur */
	
		/* GET */
			
			/**
			 * Retourne une carte du paquet avec un indice donn�
			 * @param i int
			 * 		indice de la carte � retourner
			 * @return Carte
			 * 		Carte retourn�e
			 */
			public Carte getCarte(int i){
				return cartes[i];
			}
	
		/* SET */
			
			/**
			 * Change une carte du paquer en une autre carte avec un indice donn�
			 * @param i int
			 * 		indice de la carte � changer
			 * @param c
			 * 		Nouvelle carte
			 */
			public void setCarte(int i, Carte c){
				cartes[i] = new Carte(c);
			}
	
	/* M�thode */
	
	/**
	 * Red�finition de la m�thode toString pour d�crire le paquet
	 */
	public String toString(){
		String str = new String("");
		
		for(int i=0;i<Csts.NB_CARTES;i++){
			str += cartes[i].toString()+"\n";
		}
		
		return str;
	}
	
	/**
	 * M�lange un tableau d'entiers donn�
	 * @param ar int[]
	 * 		Tableau d'entier � m�langer
	 */
	private static void melangeSymbole(int[] ar){
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--){
			int index = rnd.nextInt(i + 1);
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}
	
	private void InitCartes(){
		for(int i =0;i<Csts.NB_CARTES;i++){
			melangeSymbole(matriceSymboles[i]);
			cartes[i] = new Carte(i, matriceSymboles[i]);
		}
	}
	
	private void creationSymboles(){
			/* Symboles 0 - 7 */
			int indiceSymbole = 0;
				for(int i=0;i<8;i++){
					for(int j = 0;j<7;j++){
						matriceSymboles[i*7+j][indiceSymbole] = i;
					}
				}
			
			indiceSymbole++;
			/* Symboles 8 - 14 */
				for(int b=0;b<7;b++){
					int j=0;
					for(int i=8;i<15;i++){
						matriceSymboles[b*7+j][indiceSymbole] = i;
						j++;
					}
				}
			
				indiceSymbole++;
			/* Symboles 15 - 21 */
				int i=15;
				for(int b=0;b<7;b++){
					//i=15;
					for(int j=0;j<7;j++){
						matriceSymboles[7*b+j][indiceSymbole] = i;
						if(j<6)
							i++;
						if(i==22)
							i=15;
					}
				}
					
				indiceSymbole++;
			/* Symboles 22 - 28 */
				i=22;
				for(int b=0;b<7;b++){
					for(int j=0;j<7;j++){
						matriceSymboles[7*b+j][indiceSymbole] = i;
						if(j==6)
							i--;
						else{
							i++;
						}
						if(i==29)
								i=22;
					}
				}
			
				indiceSymbole++;
			/* Symboles 29 - 35 */
				i=29;
				for(int b=0;b<7;b++){
					for(int j=0;j<7;j++){
						matriceSymboles[7*b+j][indiceSymbole] = i;
						if(j==6)
							i-=2;
						else{
							i++;
						}
						if(i==36)
							i-=7;
						if(i<29)
							i+=7;
					}
				}
				
				indiceSymbole++;
			/* Symmboles 36 - 42 */
				i=36;
				for(int b=0;b<7;b++){
					for(int j=0;j<7;j++){
						matriceSymboles[7*b+j][indiceSymbole] = i;
						if(j==6)
							i-=3;
						else{
							i++;
							if(i>41)
								i-=7;
						}
						if(i<36)
							i+=7;
					}
				}
				
				indiceSymbole++;
			/* Symboles 43 - 49 */
				i=43;
				for(int b=0;b<7;b++){
					for(int j=0;j<7;j++){
						matriceSymboles[7*b+j][indiceSymbole] = i;
						if(j==6)
							i-=4;
						else{
							i++;
							if(i>48)
								i-=7;
						}
						if(i<43)
							i+=7;
					}
				}
			
				indiceSymbole++;
			/* Symboles 50 - 57 */
				i=50;
				for(int b=0;b<7;b++){
					for(int j=0;j<7;j++){
						matriceSymboles[7*b+j][indiceSymbole] = i;
						if(j==6)
							i-=5;
						else{
							i++;
							if(i>56)
								i-=7;
						}
						if(i<50)
							i+=7;
					}
				}
			
			/* dernières cartes 49 - 56 */
				int indiceCarte = 56;
				indiceSymbole = 1;
				for(int j=0;j<Csts.NB_SYMBOLES;j++){
					if(indiceSymbole >= Csts.SYMBOLES_CARTE)
						indiceSymbole = 1;
					
					if(j==8 || j==15 || j==22 || j==29 || j==36 || j==43 || j==50)
						indiceCarte--;
						
					matriceSymboles[indiceCarte][indiceSymbole] = j;
					indiceSymbole++;
				}
			
		}
}