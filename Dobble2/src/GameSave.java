import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

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
		    
		    String[] sco = scoreReader();
		    insertScore(0,sco,name+" : "+score+",");
		    	
		    for(int i = 0;i<sco.length;i++){
		    	if(sco[i] != null)
		    		line += sco[i];
		    }
		    	
		    Out = new FileWriter(Csts.PATHSCORE);
			Out.write(line);
			Out.close();
		    
		} 
		catch (FileNotFoundException fnfe) //If the file doesn't exist
		{
			try 
			{
				Out = new FileWriter(Csts.PATHSCORE);
				Out.write(name+":"+score);
				Out.close();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static String[] scoreReader()
	{
		String[] scoreboard = new String[Csts.NB_SCORES];
		BufferedReader In;
		
		try //If the file exists
		{
		    In = new BufferedReader(new FileReader(Csts.PATHSCORE));
		    
			String line = In.readLine();
			if(line != null)
				scoreboard = line.split(",");
		}    
		catch (FileNotFoundException fnfe) //If the file doesn't exist
		{
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return scoreboard;
	}
	
	public static int getScore(String something)
	{
		int length = something.length();
	    String result = "";
	    for (int i = 0; i < length; i++) {
	        Character character = something.charAt(i);
	        if (Character.isDigit(character)) {
	        	System.out.println("char " + character);
	            result += character;
	        }
	    }
	    return Integer.parseInt(result);
	}
	
	public static void insertScore(int i, String[] line, String s){
		System.out.println(line.length);
		if(i < line.length)
		{
			if(line[i] == null)
			{
				System.out.println("if null");
				line[i] = new String(s);
			}
			else
			{
				System.out.println("else");
				String temp = new String(s);
				if(getScore(s) > getScore(line[i]))
				{
					System.out.println("si plus grand");
					temp = new String(line[i]);
					line[i] = new String(s);
				}
				insertScore(i+1, line, temp);
			}
		}
	}

}
