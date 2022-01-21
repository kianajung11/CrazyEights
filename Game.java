import java.util.LinkedList;

public class Game{
  //game variables
  private Player m_player1;
  private Player m_player2;
  private Dealer m_dealer;
  private LinkedList<Card> m_starterPile;
  private Deck m_gameDeck;
  //statistic variables
  private int m_numWins_Player1;
  private int m_numWins_Player2;
  private int m_numPoints_Player1;
  private int m_numPoints_Player2;
  private int m_loserCardCount;
  private int m_emptyDeckCount;
  private Card m_playedCard;
  private int m_eightCounter;
  private int m_points;
  private Player m_winningPlayer;
  private boolean m_inGame;

  /** The default constructor
  */
  public Game(){
    m_dealer = new Dealer();
    m_starterPile = new LinkedList<Card>();
    m_gameDeck = new Deck();
    Card firstCard = m_gameDeck.deal();
    m_starterPile.addFirst(firstCard);
    m_player1 = new Player(1, m_gameDeck);
    m_player2 = new Player(2, m_gameDeck);
  }

  /**The accessor for starter pile attribute
  * @return a LinkedList representing the starter pile
  */
  public LinkedList<Card> getStarterPile(){
    return m_starterPile;
  }

  /**A method calls take turn on player and adds played card to starter pile
  * @param player - the player whos turn it is
  */
  private void playerTurn(Player player){
    m_playedCard = player.takeTurn(m_starterPile, m_gameDeck);
    if (m_playedCard.getValue() == 8){ //if the played card is an 8, add to the eight counter and change the suit
      m_eightCounter += 1;
      player.newSuit(m_playedCard);
    }
    m_starterPile.addFirst(m_playedCard);
  }

  /**A method that calculates the points of the winning player by adding together the points of the losers hand
  * @return an int representing the number of points
  * @param winner - the winning player
  */
  private int calculatePoints(Player winner){
    m_points = 0;
    if (winner == m_player1){ //if player 1 won, count player 2 cards in hand
      for (Card cardsInHand : m_player2.getPlayerHand()){
        m_loserCardCount += 1;
        if (cardsInHand.getValue() == 8){ //if the played card is an 8, add to the eight counter
          m_points += 50;
        }
        else if (cardsInHand.getValue() == 10 || cardsInHand.getValue() == 11 || cardsInHand.getValue() == 12 || cardsInHand.getValue() == 13){
          m_points += 10;
        }
        else if (cardsInHand.getValue() == 14){
          m_points += 1;
        }
        else {
          m_points += cardsInHand.getValue();
        }
      }
    }
    else if (winner == m_player2){ //if player 2 won, count player 1 cards in hand
      for (Card cardsInHand : m_player1.getPlayerHand()){
        m_loserCardCount += 1;
        if (cardsInHand.getValue() == 8){ //if the played card is an 8, add to the eight counter
          m_points += 50;
        }
        else if (cardsInHand.getValue() == 10 || cardsInHand.getValue() == 11 || cardsInHand.getValue() == 12 || cardsInHand.getValue() == 13){
          m_points += 10;
        }
        else if (cardsInHand.getValue() == 14){
          m_points += 1;
        }
        else {
          m_points += cardsInHand.getValue();
        }
      }
    }
    return m_points;
  }

  /**A method that carries out the rules of the game until one of the players has won
  */
  public void play(){
    m_inGame = true;

    while (m_inGame == true){
      if (m_player1.getPlayerHand().size() == 0 || m_player2.getPlayerHand().size() == 0){
        m_inGame = false; //round is over if either player has no cards left
        break;
      }
      else{ //if there are cards left,
        playerTurn(m_player1); //player 1 takes turn
        if (m_player1.getPlayerHand().size() == 0){ //check if player 1 still has cards in their hand
          break;
        }
        else{ //if player 2 has cards in their hand,
          playerTurn(m_player2); //player 2 takes turn
          if (m_player2.getPlayerHand().size() == 0){ //check if player 2 still has cards in their hand
            break;
          }
        }
      }
      if (m_gameDeck.getSize() == 0){
        m_emptyDeckCount += 1;
        m_inGame = false;
        break;
      }
    }

    //winning conditions
    m_numWins_Player1 = 0;
    m_numWins_Player2 = 0;

    //player 1 wins by getting rid of all their cards
    if (m_player1.getPlayerHand().size() == 0){
      m_numWins_Player1 +=1;
      m_numPoints_Player1 += calculatePoints(m_player1);
    }

    //player 2 wins by getting rid of all their cards
    if (m_player2.getPlayerHand().size() == 0){
     m_numWins_Player2 +=1;
     m_numPoints_Player2 += calculatePoints(m_player2);
    }

    //player 1 has less cards = win
    else if (m_gameDeck.getSize() == 0 && m_player1.getPlayerHand().size() < m_player2.getPlayerHand().size()){
      m_numWins_Player1 +=1;
      m_numPoints_Player1 += calculatePoints(m_player1);
    }

    //player 2 has less cards = win
    else if(m_gameDeck.getSize() == 0 && m_player2.getPlayerHand().size() < m_player1.getPlayerHand().size()){
      m_numWins_Player2 +=1;
      m_numPoints_Player2 += calculatePoints(m_player2);
    }

    //if no one wins, it is a draw (no points for either player)
    else {
      m_numWins_Player1 = 0;
      m_numWins_Player2 = 0;
    }

  }

  /**The accessors wins, draws, and points for each player
  * @return an int representing wins, draws or points
  */
  public int getPlayer1Wins(){
    return m_numWins_Player1;
  }
  public int getPlayer2Wins(){
    return m_numWins_Player2;
  }
  public int getPlayer1Points(){
    return m_numPoints_Player1;
  }
  public int getPlayer2Points(){
    return m_numPoints_Player2;
  }
  public int getLoserCardCount(){
    return m_loserCardCount;
  }
  public int getEmptyDeckCounter(){
    return m_emptyDeckCount;
  }
  public int getEightsCounter(){
    return m_eightCounter;
  }

}
