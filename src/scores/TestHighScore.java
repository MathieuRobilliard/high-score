package scores;

import java.util.Scanner;

public class TestHighScore
{

	public static void main(String[] args)
	{
		System.out.print("Entrez votre pseudo: ");
		Scanner keyboard = new Scanner(System.in);
		String nickname = keyboard.nextLine();
		keyboard.close();
		
		System.out.println(nickname + ", you scored: " + generateScore() + " pts\n");
		
		System.out.println("High Scores:");
		System.out.println("------------");
		
		
		for(String line : new HighScore1("https://thingspeak.com/channels/109360/feed.csv").getScores())
			System.out.println(line);
	}
	
	//This method simulates the result of a game
	private static int generateScore()
	{
		return (int)(1000 * Math.random());
	}
}
