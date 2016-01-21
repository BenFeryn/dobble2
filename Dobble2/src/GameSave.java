import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameSave {
	
	public static void saveScore(String name){
		int score = Jeu.getScore();
		try
		{ 
			if(!new File(Csts.PATHSCORE).exists())
			{
				FileWriter lu = new FileWriter(Csts.PATHSCORE);
				BufferedWriter out = new BufferedWriter(lu);
				out.write(name+" : "+score); 
				out.close();
			}else
			{
				Scanner in = new Scanner(Csts.PATHSCORE);
				String temp = in.nextLine();
				FileWriter lu = new FileWriter(Csts.PATHSCORE);
				BufferedWriter out = new BufferedWriter(lu);
				out.write(temp+","+name+" : "+score); 
				out.close();
			}
		}
		catch (IOException er) {
			System.out.println("Saving failed.");;
		}
	}

}
