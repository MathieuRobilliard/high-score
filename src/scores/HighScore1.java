package scores;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is used to fetch and send the highscores on ThingSpeak.
 * @author Mehdi & Ana
 *
 */
public class HighScore1
{
	private URL url;
	
	/**
	 * Creates a HighScore object from the url pointing to the csv file containing the scores.
	 * @param url The address of the file with the protocol. For example: "http://myserver.com/scores.csv" or whatever since the URL points to a csv file.
	 */
	public HighScore1(String url)
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
	 * 
	 * @return An ArrayList containing each line of the file except the csv header line
	 */
	public List<String> getScores()
	{
		Scanner input = null;
		ArrayList<String> result = null;
		
		try
		{
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			input = new Scanner(connection.getInputStream());
			
			result = new ArrayList<String>();
			
			input.nextLine();					//We get rid of the csv header line
			while(input.hasNextLine())
				result.add(input.nextLine());
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
}
