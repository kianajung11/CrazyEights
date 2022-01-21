import java.util.LinkedList;
import java.util.Random;
public class Dealer{

  //attribute
  private Deck m_deck;
  private final static Random randomNumber = new Random();

  //default constructor
  public Dealer(){
    m_deck = new Deck();
  }

  public Deck getDeck(){
    return m_deck;
  }

  //deals method
  public LinkedList<Card> deals(Deck gameDeck, int n){
    m_deck = gameDeck;
    LinkedList<Card> dealt = new LinkedList<>();
    Card dealtCard = new Card();
    if (gameDeck.getSize() == 0){
      return dealt;
    }
    else{
      for (int i = 0; i <= n-1; ++i){
         dealtCard = gameDeck.deal();
         dealt.add(dealtCard);
      }
    }
    return dealt;
  }

  //size method
  public int getSize(){
    return m_deck.getSize();
  }

  //toString method
  public String toString(){
    String s = m_deck.toString();
    return s;
  }

}
