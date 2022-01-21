import java.util.LinkedList;

public class Simulation{

  private int m_numGames;
  private Game CrazyEights;
  private int m_totalWins_Player1;
  private int m_totalWins_Player2;
  private int m_totalPoints_Player1;
  private int m_totalPoints_Player2;
  private double m_loserCardCount;
  private int m_numEmptyDecks;
  private double m_numEightsPlayed;

  /**A method that creates and simulates a new game n amount of times
  * @param numGames - the number of games you want to simulate
  */
  public void simulate(int numGames){
    m_numGames = numGames;
    for (int i = 0; i < m_numGames; ++i){
      Game CrazyEights = new Game();
      CrazyEights.play();
      calculate(CrazyEights);
    }
  }

  /**A method that computes the aggregate statistics from all games
  * @param CrazyEights - the game you want to get the statistics of
  */
  public void calculate(Game CrazyEights){
    m_totalWins_Player1 += CrazyEights.getPlayer1Wins();
    m_totalWins_Player2 += CrazyEights.getPlayer2Wins();
    m_totalPoints_Player1 += CrazyEights.getPlayer1Points();
    m_totalPoints_Player2 += CrazyEights.getPlayer2Points();
    m_loserCardCount = CrazyEights.getLoserCardCount();
    m_numEmptyDecks += CrazyEights.getEmptyDeckCounter();
    m_numEightsPlayed = CrazyEights.getEightsCounter();
  }

  /**A method reports the statistics
  * @return a String representing the stats
  */
  public String report(){
    String s = "";
    s += "Number of games won by Player 1: " + m_totalWins_Player1 + "\n";
    s += "Number of games won by Player 2: " + m_totalWins_Player2 + "\n";
    s += "Number of points collected by Player 1: " + m_totalPoints_Player1 + "\n";
    s += "Number of points collected by Player 2: " + m_totalPoints_Player2 + "\n";
    s += "Average number of cards held by the losing player: " + (m_loserCardCount/m_numGames) + "\n";
    s += "The number of times the stock became empty during " + m_numGames + " games: " + m_numEmptyDecks + "\n";
    s += "The average number of times an Eight was played as a wild card in each game: " + (m_numEightsPlayed/m_numGames);
    return s;
  }

  /**The main method
  * @param args
  */
  public static void main(String[] args){
    Simulation CrazyEightsTest = new Simulation();
    int n = Integer.parseInt(args[0]);
    CrazyEightsTest.simulate(n);
    System.out.println(CrazyEightsTest.report());
  }

}
