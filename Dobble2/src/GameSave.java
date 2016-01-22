import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
	    	String tabScores[] = scoreReader();
	    	
		    if(tabScores.length < Csts.NB_SCORES)
		    {
		    	line += new String(name+" : "+score+",");
		    }
		    else
		    {
		    	line = insertScore(tabScores, name+" : "+score);
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
				Out.write(name+":"+score+",");
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
	
	@SuppressWarnings("resource")
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
	
	public static String insertScore(String[] line, String s)
	{
		String toReturn = "";
		String temp = new String(s);
		for(int i=0;i<Csts.NB_SCORES;i++)
		{
			if(line[i] == null)
			{
				toReturn += temp+",";
				break;
			}
			if(getScore(temp) > getScore(line[i]))
			{
				toReturn+= temp+",";
				temp = new String(line[i]);
			}
			else
			{
				toReturn += line[i]+",";
			}
		}
		return toReturn;
	}

}
