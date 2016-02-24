//holds the information for each card
import java.util.Random;

public class Card {

  String newCard;
  private String[] cardNumber = new String[]
    {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
    private String[] cardSuit = new String[]
    {"s", "c", "h", "d"};
  Random random = new Random();
  static int suit;
  static int number;
  static boolean ace = false;
  static int cardValue;
  static int counter = 0;

  public Card()  {
    int suit = random.nextInt(4);
    int number = random.nextInt(13);
    if (number == 12)
      ace = true;
    newCard = new String (cardNumber[number] + cardSuit[suit]);
    counter++;
    if (number >= 0 && number <= 7) {
      cardValue = Integer.valueOf(cardNumber[number]);
    }
    else if (number == 8 || number== 9 || number== 10 || number == 11) {
      cardValue = 10; }
    else if (number == 12) {
      cardValue = 11;
      ace = true;
    }
  }

  public int getValue() {
    return cardValue;
  }

  public String cardName() {
      return newCard;
  }

  public boolean acePresence() {
    return ace;
  }

}
