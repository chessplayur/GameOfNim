import java.util.Scanner;

public class Player {
    private String name;
    private int score;
    private boolean autoPlay; // True for the robot player

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
        // Calculate the maximum number of pieces that can be taken (half the pile, minimum 1)
        int max = Math.max(1, pieces/2);

        int remove = 0;
        if (autoPlay) { 
            remove = getSmartMove(pieces, max); 
            System.out.println(name + " NimBot removes " + remove + " piece(s).");
        } else {
            System.out.println(name + ", there are " + pieces + " pieces remaining.");
            System.out.print("How many pieces will you take (1-" + max + ")? ");
            
            // Input validation loop
            while (true) {
                if (sc.hasNextInt()) {
                    remove = sc.nextInt();
                    sc.nextLine();  // Clean up newline (whatever that means)
                    if (remove >= 1 && remove <= max) {
                        break; // Valid input, exit loop
                    }
                } else {
                    sc.nextLine(); // Clear invalid non-integer input
                }
                System.out.print("Invalid choice. Enter a number between 1 and " + max + ": ");
            }
        }
        
        Board.removePieces(remove);
    }
    

    public static int getSmartMove(int pile, int maxRemove) {
        // Iterate over potential winning target sizes (2^k - 1)
        for(int k = 5; k >= 1; k--) { 
            int target = (1 << k) - 1; // Calculate target 
            
            if(target < pile) { // Check if the target is smaller than the current pile
                int remove = pile - target; // Calculate required removal
                
                if(remove >= 1 && remove <= maxRemove) { // Check if the move is legal
                    return remove; // Make the smart, winning move
                }
            }
        }
        // Default: If no optimum move available, remove 1 stone
        return Math.min(1, maxRemove); 
    }
}
