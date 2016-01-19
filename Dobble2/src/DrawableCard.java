import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class DrawableCard extends JPanel{
	
	private Carte carte;
	private DrawableSymbol symboles[];
	private int x, y;
	private int rayon;
	private static double pos[] = {0,0.6,0.75,0.5,0.7};
	
	public DrawableCard(Carte c, int x, int y, int rayon){
		carte = new Carte(c);
		this.x = x-rayon;
		this.y = y-rayon;
		this.rayon = rayon;
		symboles = new DrawableSymbol[Csts.SYMBOLES_CARTE];
	}
	
	private void ajoutSymboles(Graphics g){
		placerSymbole();
		for(int i=0;i<Csts.SYMBOLES_CARTE;i++){
			symboles[i].paint(g);
		}
	}
	
	private void placerSymbole(){
		for(int i=0;i<Csts.SYMBOLES_CARTE;i++){
			int x1,y1=0;
			int temp = 0;
			if(i < 5){
				temp = i;
				if(i == 0)
					temp = 0;
			}else{
				temp = i-4; 
			}

			x1 = (int)((Math.cos(i*Math.PI/3.5)*(rayon*pos[temp]))+x+rayon);
			y1 = (int)((Math.sin(i*Math.PI/3.5)*(rayon*pos[temp]))+y+rayon);
			symboles[i] = new DrawableSymbol(carte.getSymbole(i),x1,y1, 50, 50);
		}
	}
	
	public void paint(Graphics g){
		g.setColor(new Color(138,43,226));
		g.fillOval(x-5, y-5, (rayon*2)+10, (rayon*2)+10);
		g.setColor(new Color(240,230,140));
		g.fillOval(x, y, rayon*2, rayon*2);
		ajoutSymboles(g);
	}
	
}