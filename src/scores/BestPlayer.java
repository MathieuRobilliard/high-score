package scores;

/**
 * This class is used to compare the scores and to display the player with the best score
 * @author mehdi & ana
 */
public class BestPlayer {
    private String player;
    private int score;
    
    /**
     * Creates a BestPlayer object, described by a name and a score
     * @param player
     * @param score 
     */
    
    public BestPlayer( String player,int score){
        score=this.score;
        player=this.player;
    }
    
    /**
     * method which allows us to take the score of the best player
     * @return score of the BestPlayer
     */
    public int getScore(){
        return this.score;
    }    
    
    /**
     * method who allows us to take the name of the best PLAYER
     * @return player representing the name of the BestPlayer
     */
    public String getPlayer(){
        return this.player;
    }
    /**
     * this method compares the scores of the players and display, depending on the case, 1,-1 or 0
     * @param p represents the object p of a BestPlayer constructor
     * @return 0 if it is the same player
     * @return -1 if the score of the implicit player is less than the score of p
     * @return 1 otherwise
     */
    
    public int compareTo(BestPlayer p){
        if(getPlayer().equals(p.getPlayer())){
          return 0;
        }
        else if(getScore() < p.getScore()){
            return -1;}
        else
            return 1;
    }
    
}
