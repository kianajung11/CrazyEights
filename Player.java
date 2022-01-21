import java.util.LinkedList;
import java.util.Random;
import java.util.ArrayList;

public class Player{
  /**The player*/
  private int m_player;
  /**The game deck*/
  private Deck m_gameDeck;
  /**A dealt card*/
  private Card m_dealtCard;
  /**A LinkedList of cards that represents the players hand*/
  private LinkedList<Card> m_playerHand;
  /**A LinkedList of cards that represents the starter pile*/
  private LinkedList<Card> m_starterPile;
  /**A LinkedList of cards that represents the cards in a player's hand that matches the starter pile*/
  private LinkedList<Card> m_playableCards;
  /**A LinkedList of cards that represents the cards in a player's hand that is an eight*/
  private LinkedList<Card> m_crazyEights;
  /**The card the player played*/
  private Card m_chosenCard;
  /**A random generator*/
  private final static Random randomGenerator = new Random();


  /**The default constructor
  */
  public Player(){
    m_player = 0;
    m_gameDeck = new Deck();
  }

  /**An overloaded constructor that creates a player from a number and deck of cards
  * @param player - the player number (1 or 2)
  * @param gameDeck - the deck of cards that is being played
  */
  public Player(int player, Deck gameDeck){
    m_player = player;
    m_gameDeck = gameDeck;
    dealPlayerHand();
    m_starterPile = new LinkedList<Card>();
    Card firstCard = gameDeck.deal();
    m_starterPile.addFirst(firstCard);
  }

  /**A method that deals the player 5 random cards
  */
  private void dealPlayerHand(){
    Dealer playerHand = new Dealer();
    m_playerHand = new LinkedList<Card>();
    m_playerHand = playerHand.deals(m_gameDeck, 5);
  }

  /**The accessor for playerHand attribute
  * @return a LinkedList representing the player's hand
  */
  public LinkedList<Card> getPlayerHand(){
    return this.m_playerHand;
  }

  /**The accessor for playableCards attribute
  * @return a LinkedList representing the player's playable cards
  */
  public LinkedList<Card> getPlayableCards(){
    return this.m_playableCards;
  }

  /**The accessor for starterPile attribute
  * @return a LinkedList representing the starterPile
  */
  public LinkedList<Card> getStarterPile(){
    return m_starterPile;
  }

  /**A method that creates a LinkedList of Cards with cards that match the starter pile
  * @return a LinkedList representing playable cards
  * @param playerHand - the cards in the players hand
  * @param starterPile - the starter pile
  */
  private LinkedList<Card> findPlayableCards(LinkedList<Card> playerHand, LinkedList<Card> starterPile){
    m_starterPile = starterPile;
    m_playableCards = new LinkedList<Card>();
    m_crazyEights = new LinkedList<Card>();
    m_playerHand = playerHand;
    for(Card inHand : m_playerHand){ //iterate through players hand and call it inHand
      //if the suit or value of the card in hand and the starter pile and is not an 8, get the index of the card
      if (inHand.getSuit() == starterPile.get(0).getSuit() || inHand.getValue() == starterPile.get(0).getValue()){
        if (inHand.getValue() != 8){
          m_playableCards.add(inHand);
        }
      }
      else if (inHand.getValue() == 8){ //check for eights
        m_playableCards.add(inHand);
        m_crazyEights.add(inHand);
      }
    }
    return m_playableCards;
  }

  /**A method that picks a card based on probability
  * @return a Card representing the chosen card
  * @param playerHand - the cards in the players hand
  */
  private Card findChosenCard(LinkedList<Card> playerHand){
    m_playerHand = playerHand;
    int probability = randomGenerator.nextInt(10); //probability of 10%
    m_chosenCard = new Card();
    //iterate through the playable cards and choose a card with 10% probability
    for (int i = 0; i < m_playableCards.size(); ++i){
      if (m_crazyEights.size() > 0 && m_playableCards.size() > 0 && i == probability){
          m_chosenCard = m_crazyEights.get(0);
          playerHand.remove(m_chosenCard);
      }
      if (m_playableCards.get(i).getValue() == 8 && m_playableCards.size() == 0){
        m_chosenCard = m_crazyEights.get(i);
        playerHand.remove(m_chosenCard);
      }
      else{
        m_chosenCard = m_playableCards.get(i);
      }
    }
    if (m_gameDeck.getSize() == 0){
      return null;
    }
    m_playerHand.remove(m_chosenCard); //remove card from player's hand
    m_starterPile.addFirst(m_chosenCard); //add it to the starter pile and make it the first card (card to match)
    return m_chosenCard;
  }

  /**A method that takes a turn by calling findPlayableCards and findChosenCard, and deals out cards if there are no playable cards in hand
  * @return a Card representing the played card
  * @param starterPile - the starter pile
  * @param gameDeck - the game deck or stock pile
  */
  public Card takeTurn(LinkedList<Card> starterPile, Deck gameDeck){
    m_starterPile = starterPile;
    m_dealtCard = new Card();
    findPlayableCards(this.m_playerHand, m_starterPile); //find playable cards from current hand
    //keep dealing until there is a playable card
    while (this.m_playableCards.size() == 0 && this.m_crazyEights.size() == 0 && gameDeck.getSize() != 0){
      m_dealtCard = gameDeck.deal();
      m_playerHand.add(m_dealtCard);

      if (gameDeck.getSize() == 1){
        m_playerHand.add(gameDeck.getFirstCard());
        gameDeck.removeCard(0);
        break;
      }
      //if dealt card matches top card, add it to playable cards and break the loop
      if (m_dealtCard.getSuit() == m_starterPile.get(0).getSuit() || m_dealtCard.getValue() == m_starterPile.get(0).getValue()){
        m_playableCards.add(m_dealtCard);
        break;
      }
  }
    findChosenCard(m_playerHand);
    return m_chosenCard;
  }

  /**A method that changes the suit of the card if a crazy eight was played
  * @return a Card representing the new suit
  * @param crazyEight - the crazy eight card that was played
  */
  public void newSuit(Card crazyEight){
    int newSuit = randomGenerator.nextInt(4);
    crazyEight.setSuit(newSuit);
  }

}
