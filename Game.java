import java.util.Random;
import java.util.Scanner;

public class Game {
    Scanner sc = new Scanner(System.in);
    private Player player1;
    private Player player2;

    public Game() {
        //Scanner sc = new Scanner(System.in);

        System.out.print("Enter name for Player 1: ");
        String name1 = sc.nextLine();

        System.out.print("Do you want to play against a dumb robot? (y/n): ");
        String choice = sc.nextLine().trim().toLowerCase();

        if (choice.equals("y")) {
            player1 = new Player(name1, false);
            player2 = new Player("Robot", true);
        } else {
            System.out.print("Enter name for Player 2: ");
            String name2 = sc.nextLine();
            player1 = new Player(name1, false);
            player2 = new Player(name2, false);
        }
    }

    public void play() {
        boolean playAgain;
        do {
            playAgain = playAgain();
        } while (playAgain);

        // End screen
        System.out.println("\n Final Scores:");
        System.out.println(player1.getName() + ": " + player1.getScore());
        System.out.println(player2.getName() + ": " + player2.getScore());
        System.out.println("Thanks for playing!");
    }

    private boolean playAgain() {
        Board.populate();
        Random rand = new Random();
        boolean player1Turn = rand.nextBoolean();

        System.out.println("\nStarting a new game!");
        System.out.println("Initial pile size: " + Board.getNumPieces());
        System.out.println((player1Turn ? player1.getName() : player2.getName()) + " goes first.\n");

        while (Board.getNumPieces() > 0) {
            if (player1Turn) {
                player1.takeTurn(sc);
            } else {
                player2.takeTurn(sc);
            }
            player1Turn = !player1Turn;
        }

        // The player who just played lost
        Player winner = player1Turn ? player1 : player2;
        winner.incrScore();

        System.out.println("\nThe pile is empty! " + winner.getName() + " wins this round!\n");

        //Scanner sc = new Scanner(System.in);
        System.out.print("Play again? (y/n): ");
        String answer = sc.nextLine().trim().toLowerCase();
        return answer.equals("y");
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}
