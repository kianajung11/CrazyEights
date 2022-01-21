public class TestCards{
  public static void main(String[] args){
    //card class
    Card card1 = new Card(5, 2); //create a single card
    Card card2 = new Card(20, 7); //create an invalid card
    System.out.println(card1); //print valid card (5 of clubs)

    //deck class
    Deck deck1 = new Deck(); //create a deck
    Deck deck2 = new Deck(deck1); //copy deck1
    System.out.println(deck1); //print all cards in deck1
    System.out.println(deck2); //print all cards in deck2
    System.out.println(deck1.getSize()); //test getSize method
    System.out.println(deck1.deal());
    System.out.println(deck1.getSize()); //test getSize method after dealing

    //dealer class
    Dealer deck = new Dealer(); //create a deck
    System.out.println(deck.getSize()); //test getSize method
    System.out.println(deck.deals(deck1, 7)); //deal 7 random cards
    System.out.println(deck);
  }
}
