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
		
		try//If the file exists
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
				String temp = new String("no one : 0,no one : 0,");
				Out = new FileWriter(Csts.PATHSCORE);
				Out.write(name+" : "+score+","+temp);
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
			for(int i=0;i<Csts.NB_SCORES;i++)
			{
				scoreboard[i] = new String("no one : 0");
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return scoreboard;
	}
	
	public static int getScore(String something)
	{
		int result = 0;
	    String[] splitted = something.split(":");
	    for (int i = 0; i < splitted[1].length(); i++) {
	        char character = splitted[1].charAt(i);
	        if (Character.isDigit(character)) {
	        	int temp = Character.getNumericValue(character);
	            result *= 10;
	            result += temp;
	        }
	    }
	    return result;
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
