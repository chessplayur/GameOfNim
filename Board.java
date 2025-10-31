import java.util.Random;

public class Board {
    private static int pieces;
    
    /**
     * Initializes the number of pieces on the board to a random value between 10 and 50.
     */
    public static void populate() {
        Random rand = new Random();
        pieces = rand.nextInt(41) + 10; // random number between 10 and 50
    }

    public static int getNumPieces() {
        return pieces;
    }

    /**
     * Removes a specified number of pieces from the pile.
     * FIX: The check if (pieces < 0) pieces = 1; was removed, as it prevented the game
     * from ending when the piece count naturally hit 0.
     * @param num The number of pieces to remove.
     */
    public static void removePieces(int num) {
        pieces -= num;
    }
}
