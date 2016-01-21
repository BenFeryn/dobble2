import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrawableSymbol {

	private Symbole symbole;
	private Image img;
	private int x, y, x1, y1;
	private int largeur, hauteur;
	private boolean selected;
	
	public DrawableSymbol(Symbole s, int x, int y, int largeur, int hauteur) {
		symbole = s;
		this.x = x - largeur/2;
		this.y = y - hauteur/2;
		this.x1 = x + largeur;
		this.y1 = y + hauteur;
		this.largeur = largeur;
		this.hauteur = hauteur;
		selected = false;
		
		try {
		    java.net.URL url = getClass().getClassLoader().getResource ("./img/smb/"+s.getLienImg((int)(Math.random()*4))+".png");
		    img = ImageIO.read (url);
		}
		catch ( IOException e ) {		
		    System.out.println ("[!] Erreur : L'image est introuvable.\n" + e);
		}
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public void setSelected(boolean flag){
		selected = flag;
	}
	
	public Symbole getSymbole(){
		return symbole;
	}
	
	
	public void paint(Graphics g){
		g.drawImage(img, x, y, largeur, hauteur, null);
		//System.out.println(symbole.getValeurSymbole()+"["+x+","+y+"],["+(x+largeur)+","+(y+hauteur)+"]");
	}
	
	public boolean isClicked(Point p){
		return (p.getX() <= (x1) && 
				p.getX() >= (x) && 
				p.getY() <= (y1) &&
				p.getY() >= (y));
	}
	
	public boolean equals(DrawableSymbol d){
		return symbole.getValeurSymbole() == d.symbole.getValeurSymbole();
	}

}
