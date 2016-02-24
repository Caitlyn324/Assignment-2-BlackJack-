import java.util.*;
import java.io.*;
import java.text.NumberFormat;

  public class Player {

    static int hands = 0, wins = 0;
    static double money = 0.0;
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    static String name, fileName;
    File file;

    public Player(String playerName) throws IOException {

      name = playerName;
      final String fileName = new String (name + ".txt");
      file = new File (fileName);
      int hands = 0, wins = 0;
      double money = 100.0;

      if (!file.exists()) {
        System.out.println("Ok, I'll write you a new save file, " + name + "!");
        PrintWriter out = new PrintWriter(fileName);
        out.println(name);
        out.println(hands);
        out.println(wins);
        out.println(money);
        out.close();
        this.load(fileName);

      }

      else {
        System.out.println("Welcome, returning player " + name + "!");
        this.load(fileName);
      }
    }

    public String getFileName(String foo) {
      String fileName = new String(foo + ".txt");
      return fileName;
    }

    public void load(String fileName) throws IOException {

      File file = new File(fileName);
      Scanner input = new Scanner(file);
      name = input.nextLine();
      hands = Integer.parseInt(input.nextLine());
      wins = input.nextInt();
      money = input.nextDouble();

      System.out.print("Name: ");
      System.out.printf("%-15s\n", name);
      System.out.print("Total Hands: ");
      System.out.printf("%-10d\n", hands);
      System.out.print("Hands Won: ");
      System.out.printf("%-12d\n", wins);
      System.out.print("Money: ");
      System.out.printf("%-14.2f\n", money);
    }

    public void save(String playFileName) throws IOException {
      String fileName = playFileName;
      PrintWriter out = new PrintWriter(fileName);
      out.println(name);
      out.println(hands);
      out.println(wins);
      out.println(money);
      out.close();
    }

    public double getBalance() {
      return money;
    }

    public double winBalance(double bet) {
      money += bet;
      return money;
    }

    public double blackjackBalance(double bet) {
      money += 1.5 * bet;
      return money;
    }

    public double loseBalance(double bet) {
      money -= bet;
      return money;
    }

    public void wonHand() {
      hands++;
      wins++;
    }

    public void lostHand() {
      hands++;
    }

}
