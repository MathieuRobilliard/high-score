package scores;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

/**
 * This class is used to fetch and send the highscores on ThingSpeak.
 * @author Mehdi & Ana
 *
 */
public class HighScore2
{
	private URL url;
	
	/**
	 * Creates a HighScore object from the url pointing to the csv file containing the scores.
	 * @param url The address of the file with the protocol. For example: "http://myserver.com/scores.csv" or whatever since the URL points to a csv file.
	 */
	public HighScore2(String url)
	{
		try
		{
			this.url = new URL(url);
		}
		catch (MalformedURLException e)
		{
			System.out.println("URL Error: the url is incorrect!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Send a GET HTTP request to fetch the scores from the URL given to the object's constructor.
	 * @return An ArrayList containing each line of the file except the csv header line
	 */
	public List<String> getScores()
	{
		Scanner input = null;
		ArrayList<String> result = null;
		
		try
		{
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			input = new Scanner(connection.getInputStream());
			
			result = new ArrayList<String>();
			
			input.nextLine();					//We get rid of the csv header line
			while(input.hasNextLine())
			{
				String line = input.nextLine();
				if(!line.isEmpty())
					result.add(line);
			}
		}
		catch(IOException e)
		{
			System.out.println("I/O Error: Connection problem");
			e.printStackTrace();
		}
		
		finally
		{
			if(input != null)
				input.close();
		}
		
		return result;
	}
	
	/**
	 * Extracts the ten best scores from the given(or less if there're less than ten scores).
	 * @return An array containing the ten or less best players
	 */
	public BestPlayer[] tenBestScores(List<String> readScores)
	{
		BestPlayer[] allBest = new BestPlayer[10];
		List<BestPlayer> playerRecords = parsePlayers(readScores);
		Collections.sort(playerRecords, Collections.reverseOrder());
		
		for(int i = 0; i < playerRecords.size() && i < 10; i++)
			allBest[i] = playerRecords.get(i);
		
		return allBest;
	}
	
	private List<BestPlayer> parsePlayers(List<String> readScores)
	{
		ArrayList<BestPlayer> playerRecords = new ArrayList<BestPlayer>(readScores.size());
		
		for(String line : readScores)
		{
			String[] fields = line.split(",");
			playerRecords.add(new BestPlayer(Integer.parseInt(fields[2]), fields[3]));
		}
		
		return playerRecords;
	}
}
