//holds the actual game
import java.util.*;
import java.io.*;
import java.text.NumberFormat;


public class Blackjack {

  public static void main(String[] args) throws IOException, InputMismatchException {

    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    Scanner input = new Scanner(System.in);
    boolean contBet = true;
    double betD = 0.0;

    System.out.print("Welcome it Infinite BlackJack! What is your name? ");
    String playerName = input.nextLine();
    Player _Sora = new Player(playerName);
    String fileName = _Sora.getFileName(playerName);
    System.out.println(fileName);

    System.out.print("would you like to place a bet?(Y/N) ");
    String contBetChoice = input.nextLine();
    if (contBetChoice.equalsIgnoreCase("n"))
      contBet = false;
    else if (!contBetChoice.equalsIgnoreCase("n") && !contBetChoice.equalsIgnoreCase("y")) {
      do {
        System.out.print("Sorry, I didn't catch that. would you like to place a bet?(Y/N) ");
        contBetChoice = input.nextLine();
      } while (!contBetChoice.equalsIgnoreCase("n") && !contBetChoice.equalsIgnoreCase("y"));
    }

    while (contBetChoice.equalsIgnoreCase("y")) {
      boolean blackjack = false;
      boolean hit = false;
      int scoreHand = 0;
      int scoreHandHouse = 0;
      boolean lost = false;
      boolean lostHouse = false;
      double balance = _Sora.getBalance();
      System.out.print("Ok! how much would you like to bet?");
      while (true) {
        String bet = input.nextLine();
        try {
          betD = Double.parseDouble(bet);
          break;
        }
        catch (NumberFormatException nfe) {
          System.out.println("Please enter valid input.");
        }
      }
      if (betD <= 0.00) {
        do {
          System.out.println("I'm sorry, please enter a value greater than 0!");
          betD = input.nextDouble();
        } while (betD <=0.00);

      }
      else if (betD > balance) {
        do {
          System.out.println("I'm sory, you don't have that much money! Please enter a value less than your current balance!");
          betD = input.nextDouble();
        } while (betD > balance);
      }

      System.out.println("Your bet is " + formatter.format(betD) + ". Let's get started!");

      Card pCard1 = new Card();
      boolean pAce1 = pCard1.acePresence();
      int pCard1Value = pCard1.getValue();
      Card pCard2 = new Card();
      boolean pAce2 = pCard2.acePresence();
      int pCard2Value = pCard2.getValue();
      scoreHand = pCard1Value + pCard2Value;


      System.out.print("Your hand is: " + pCard1.cardName() + " " + pCard2.cardName() + ".");
      System.out.println(" Your current score for this hand is " + scoreHand + ".");

      if (scoreHand == 21) {
        blackjack = true;
        System.out.println("You've got BlackJack!");
      }

      // dealt first hand to Sora.

      String hitChoice;
      if (scoreHand > 21) {
        lost = true;
        System.out.println("Sorry, you busted!");
      }
      else if (scoreHand <= 21) { //player choices
        do {
          System.out.print("Would you like to [H]it or [S]tay? ");
          hitChoice = input.nextLine();
          if (!hitChoice.equalsIgnoreCase("h") && !hitChoice.equalsIgnoreCase("hit") && !hitChoice.equalsIgnoreCase("s") && !hitChoice.equalsIgnoreCase("stay")) {
            do {
              System.out.print("Sorry, I didn't catch that. Would you like to [H]it or [S]tay? ");
              hitChoice = input.nextLine();
            } while (!hitChoice.equalsIgnoreCase("h") && !hitChoice.equalsIgnoreCase("hit") && !hitChoice.equalsIgnoreCase("s") && !hitChoice.equalsIgnoreCase("stay"));
          }

          else if (hitChoice.equalsIgnoreCase("h") || hitChoice.equalsIgnoreCase("hit")) {
            hit = true;
            Card pCard3 = new Card();
            int pCard3Value = pCard3.getValue();
            boolean pCard3Ace = pCard3.acePresence();
            int aceTest = pCard3Value + scoreHand;
            if (aceTest > 21 && pCard3Ace) {
              pCard3Value = 1;
            }
            scoreHand += pCard3Value;
            System.out.print("You draw a(n) " + pCard3.cardName() + ".");
            System.out.println(" Your current score for this hand is " + scoreHand);
          }

          else if (hitChoice.equalsIgnoreCase("s") || hitChoice.equalsIgnoreCase("stay")) {
            hit = false; }

            Card pCard3 = new Card();

        } while (scoreHand <= 21 && hit);
      }
      //begin dealer hand

      Card hCard1 = new Card();
      Card hCard2 = new Card();
      scoreHandHouse = hCard1.getValue() + hCard2.getValue();
      boolean blackjackHouse = false;
       if (scoreHandHouse == 21) {
         blackjackHouse = true;
       }

      boolean houseAce1 = hCard1.acePresence();
      boolean houseAce2 = hCard2.acePresence();
      System.out.println("Dealer's hand: " + hCard1.cardName() + " " + hCard2.cardName());
      System.out.println("Dealer's score is " + scoreHandHouse + ".");
      boolean houseHit = false;
      if (houseAce1 == true || houseAce2  == true) {
        houseHit = true;
      }

      if (scoreHandHouse > 21) {
        lostHouse = true;
      }
      else {
        do {
          if (houseHit = true  && scoreHandHouse == 17) {
            Card hCard3 = new Card();
            scoreHandHouse += hCard3.getValue();
            }

          else if (scoreHandHouse <= 16) {
            Card hCard3 = new Card();
            scoreHandHouse += hCard3.getValue();
            System.out.println("Dealer draws: " + hCard3.cardName() + ".");
            System.out.println("Dealer's score is " + scoreHandHouse + ".");

          }
        } while (scoreHandHouse < 18 && !lost);
      }
      if (scoreHandHouse > 21) {
        lostHouse = true;
      }

      System.out.println("Finally, the score is " + scoreHand + " to " + scoreHandHouse + ".");
      if (scoreHand > 21)
        lost = true;
      if (lost && lostHouse) {
        System.out.println("You both lost! Better luck next time!");
        _Sora.lostHand();

      }
      else if (lost && !lostHouse) {
        System.out.println("You lost! Better luck next time!");
        balance = _Sora.loseBalance(betD);
        _Sora.lostHand();
      }
      else if (!lost && lostHouse) {
        System.out.println("Congradulations, you won the hand!");
        balance = _Sora.winBalance(betD);
        _Sora.wonHand();
      }
      else {
        if (blackjack && !blackjackHouse) {
          System.out.println("Congradulations, you Got BlackJack!");
          balance = _Sora.blackjackBalance(betD);
          _Sora.wonHand();
        }

        else if (blackjack && blackjackHouse) {
          System.out.println("Sorry, you Both got BlackJack! This hand's a Push!");
          _Sora.lostHand();
        }

        else {
          if (scoreHand > scoreHandHouse) {
            System.out.println("Congradulations, you won the hand!");
            balance = _Sora.winBalance(betD);
            _Sora.wonHand();
          }

          else if (scoreHand == scoreHandHouse) {
            System.out.println("Sorry, This is a Push!!");
            _Sora.lostHand();
          }

          else if (scoreHand < scoreHandHouse) {
            System.out.println("Sorry, you lost the hand!");
            balance = _Sora.loseBalance(betD);
            _Sora.lostHand();
          }
        }
      }
    _Sora.save(fileName);
    _Sora.load(fileName);

    System.out.print("Wasn't that FUN?!? would you like to play again? (Y/N) ");
    contBetChoice = input.nextLine();
    if (!contBetChoice.equalsIgnoreCase("n") && !contBetChoice.equalsIgnoreCase("y")) {
      do {
        System.out.print("Sorry, I didn't catch that. would you like to place a bet?(Y/N) ");
        contBetChoice = input.nextLine();
      } while (!contBetChoice.equalsIgnoreCase("n") && !contBetChoice.equalsIgnoreCase("y"));
    }
      }
      System.out.println("Thanks for playing!");
      input.close();

}
}
