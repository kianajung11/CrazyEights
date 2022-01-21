import java.util.LinkedList;
import java.util.Random;
public class Deck{

  private LinkedList<Card> m_cards = new LinkedList<Card>();
  private final static Random randomNumber = new Random();

  //default constructor
  public Deck(){
    int counter = 0;
    for (int value = 2; value <= 14; ++value){
      for (int suit = 0; suit < 4; ++suit){
        m_cards.add(new Card(value, suit));
      }
    }
  }

  //copy constructor
  public Deck(Deck d){
    this.m_cards = d.m_cards;
  }

  //toString
  public String toString(){
    String s = "";
    for(Card cards: m_cards){
      s += cards + "\n";
    }
    return s;
    }

  //size method
  public int getSize(){
    return m_cards.size();
  }

  public Card getFirstCard(){
    return m_cards.get(0);
  }

  public void removeCard(int n){
    m_cards.remove(n);
  }

  //deal method
  public Card deal(){
    int randomCard = randomNumber.nextInt(m_cards.size()-1);
    m_cards.remove(m_cards.get(randomCard));
    return m_cards.get(randomCard);
  }

}
