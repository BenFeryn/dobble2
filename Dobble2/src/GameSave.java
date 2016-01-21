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
		    
		    if(line != null)
		    	line += ",";
		    else
		    	line = "";
		    
		    String[] sco = scoreReader();
		    if(sco.length >= Csts.NB_SCORES)
		    {
		    	insertScore(0,sco,name+" : "+score);
		    }
		    else
		    {
			    Out = new FileWriter(Csts.PATHSCORE);
			    Out.write(line+name+" : "+score);
			    Out.close();
		    }
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
	
	public static int getScore(String s)
	{
		int toReturn = 0;
		try {
			toReturn = ((Number)NumberFormat.getInstance().parse("123e")).intValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(toReturn);
		return toReturn;
	}
	
	public static void insertScore(int i, String[] line, String s){
		System.out.println("taille tab : "+line.length+ " "+i);
		if(i < Csts.NB_SCORES)
		{
			if(line[i] == null)
			{
				line[i] = s;
				System.out.println("if(null)");
			}
			else
			{
				System.out.println("else");
				String temp = s;
				if(getScore(s) > getScore(line[i]))
				{
					System.out.println("if plus grand");
					temp = line[i];
					line[i] = s;
				}
				insertScore(i+1, line, temp);
			}
		}
	}

}
