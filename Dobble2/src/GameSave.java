import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameSave {
	
	public static void saveScore(){
		int score = Jeu.getScore();
		String name = Jeu.name;
		
		BufferedReader In;
		FileWriter Out;
		
		try //If the file exists
		{
		    In = new BufferedReader(new FileReader(Csts.PATHSCORE));
		    String line = In.readLine();
		    
		    if(line != null)
		    	line += ",";
		    else
		    	line = "";
		     
		    Out = new FileWriter(Csts.PATHSCORE);
		    Out.write(line+name+" : "+score);
		    Out.close();
		} 
		catch (FileNotFoundException fnfe) //If the file doesn't exist
		{
			try {
				Out = new FileWriter(Csts.PATHSCORE);
				Out.write(name+":"+score);
				Out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
