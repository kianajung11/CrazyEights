public class Card{

  //attributes
  private int m_value;
  private int m_suit;
  public static final int HEARTS = 0;
  public static final int SPADES = 1;
  public static final int CLUBS = 2;
  public static final int DIAMONDS = 3;
  public static final int JACK = 11;
  public static final int QUEEN = 12;
  public static final int KING = 13;
  public static final int ACE = 14;

  //default constructor
  public Card(){
    m_value = 0;
    m_suit = 0;
  }

  //overloaded constructor
  public Card(int v, int s){
    if(v <= 14 && v >= 2){
      m_value = v;
    }
    else{
      System.out.println("Card value invalid.");
    }
    if(s <= 4){
      m_suit = s;
    }
    else{
      System.out.println("Card suit invalid.");
    }
  }

  //copy constructor
  public Card(Card c){
    this.m_value = c.m_value;
    this.m_suit = c.m_suit;
  }

  //accessors
  public int getValue(){
    return m_value;
  }
  public int getSuit(){
    return m_suit;
  }

  //mutators
  public void setValue(int v){
    m_value = v;
  }
  public void setSuit(int s){
    m_suit = s;
  }

  //toString method
  public String toString(){
    String s = "";
    String value = "";
    String suit = "";
    //finding value of card
    if(m_value <= 10){
      value += m_value;
    }
    else{
    switch(m_value){
      case 11:
        value = "Jack";
        break;
      case 12:
        value = "Queen";
        break;
      case 13:
        value = "King";
        break;
      case 14:
        value = "Ace";
        break;
      }
    }
    //finding suit of card
    if(m_suit == 0){
      suit = "Hearts";
    }
    if(m_suit == 1){
      suit = "Spades";
    }
    if(m_suit == 2){
      suit = "Clubs";
    }
    if(m_suit == 3){
      suit = "Diamonds";
    }
    s += value + " of " + suit;
    return s;
  }

  public boolean equals(Object o){
    if (this == o){
      return true;
    }
    if (!(o instanceof Card)){
      return false;
    }
    Card c = (Card) o;
    return this.m_value == c.m_value;
  }

}
