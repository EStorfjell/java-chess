package model;

/**
 * ChessMain provides the main method for the chess engine.
 *
 * @author Emilie Storfjell
 */
public class ChessMain {
    public static final String START_POS = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    /**
     * Main method for the engine.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        Board board = new Board(START_POS);
        System.out.println(board);
    }
}