package scores;

public class BestPlayer implements Comparable<BestPlayer>
{
	private int score;
	private String player;
	
	/**
	 * Constructs a best player from a score and its nickname.
	 * @param score The score you want to give to the player
	 * @param player The nickname you want to give to the player
	 */
	public BestPlayer(int score, String player)
	{
		this.score = score;
		this.player = player;
	}
	
	/**
	 * Compares the score of the current BestPlayer with another BestPlayer's score
	 * @param player The other BestPlayer you want to compare to.
	 * @return 0 if the scores are equals. -1 if the other player's score is better. 1 in any other case.
	 */
	public int compareTo(BestPlayer player)
	{
		if(player == null)
			throw new NullPointerException();
		
		if(!(player instanceof BestPlayer))
			throw new ClassCastException();
		
		int result;
		
		if(score == player.score)
			result = 0;
		else if(score < player.score)
			result = -1;
		else
			result = 1;
		
		return result;
	}
	
	public String toString()
	{
		return player + ": " + score;
	}
}
