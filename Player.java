import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name;
    private int score;
    private boolean autoPlay;

    public Player(String name, boolean autoPlay) {
        this.name = name;
        this.autoPlay = autoPlay;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void incrScore() {
        score++;
    }

    public void takeTurn(Scanner sc) {
        int pieces = Board.getNumPieces();
        int max = Math.max(1, pieces/2);

        int remove;
        if (autoPlay) {
            remove = new Random().nextInt(max) + 1;
            System.out.println(name + " (robot) removes " + remove + " piece(s).");
        } else {
            System.out.println(name + ", there are " + pieces + " pieces remaining.");
            System.out.print("How many pieces will you take (1-" + max + ")? ");
            remove = sc.nextInt();
            sc.nextLine();  // ✓ Clean up newline
            
            while (remove < 1 || remove > max) {
                System.out.print("Invalid choice. Enter a number between 1 and " + max + ": ");
                remove = sc.nextInt();
                sc.nextLine();  // ✓ Clean up newline
            }
        }
        
        Board.removePieces(remove);
    }
     // Smart strategy for the computer: always try to leave a pile of size (2^k)-1
     public static int getSmartMove(int pile, int maxRemove) {
        for(int k = 5; k >= 1; k--) { // Check possible (2^k)-1 values from highest to lowest
            int target = (1 << k) - 1; // Calculate target as 2^k - 1
            if(target < pile) { // If target is valid
                int remove = pile - target; // Calculate required removal
                if(remove >= 1 && remove <= maxRemove) { // If move is allowed
                    return remove; // Make the smart move
                }
            }
        }
        return Math.min(1, maxRemove); // Default: remove 1 stone if no smart move possible
    }

}