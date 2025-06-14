package model;

import java.util.Optional;

/**
 * Representation of a chess board.
 *
 * @author Emilie Storfjell
 */
public class Board {
    /**
     * Width and height of the chess board.
     */
    public static final int BOARD_SIZE = 8;

    /**
     * Internal representation of the board/
     */
    private final PieceType[][] board;

    /**
     * Constructs a blank board.
     */
    public Board() {
        board = new PieceType[BOARD_SIZE][BOARD_SIZE];
    }

    /**
     * Constructs a board from a FEN string.
     *
     * @param fenString board representation in Forsyth–Edwards Notation
     * @throws IllegalArgumentException if string is invalid
     */
    public Board(final String fenString) throws IllegalArgumentException {
        this();

        if (fenString == null || fenString.isEmpty())
            throw new IllegalArgumentException("FEN string must not be null or empty!");

        int posLength = fenString.indexOf(' ');
        String positions = fenString.substring(0, posLength);
        int index = 0;
        int rank = BOARD_SIZE - 1;
        int file = 0;
        while (index < posLength && rank >= 0) {
            if (file == BOARD_SIZE && positions.charAt(index) == '/') {
                rank--;
                file = 0;
                index++;
            }
            if (file >= BOARD_SIZE)
                throw new IllegalArgumentException("FEN string has too many files at index: " + index);
            char c = positions.charAt(index);
            if (c >= '1' && c <='8') {
                file += c - '0';
            } else {
                switch (c) {
                    case '/':
                        rank--;
                        file = 0;
                        break;
                    case 'P':
                        board[rank][file] = PieceType.W_PAWN;
                        break;
                    case 'p':
                        board[rank][file] = PieceType.B_PAWN;
                        break;
                    case 'N':
                        board[rank][file] = PieceType.W_KNIGHT;
                        break;
                    case 'n':
                        board[rank][file] = PieceType.B_KNIGHT;
                        break;
                    case 'B':
                        board[rank][file] = PieceType.W_BISHOP;
                        break;
                    case 'b':
                        board[rank][file] = PieceType.B_BISHOP;
                        break;
                    case 'R':
                        board[rank][file] = PieceType.W_ROOK;
                        break;
                    case 'r':
                        board[rank][file] = PieceType.B_ROOK;
                        break;
                    case 'Q':
                        board[rank][file] = PieceType.W_QUEEN;
                        break;
                    case 'q':
                        board[rank][file] = PieceType.B_QUEEN;
                        break;
                    case 'K':
                        board[rank][file] = PieceType.W_KING;
                        break;
                    case 'k':
                        board[rank][file] = PieceType.B_KING;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid character in FEN string: " + c);
                }
                file++;
            }
            index++;
        }
    }

    /**
     * Set a square to the specified piece.
     *
     * @param rank zero-indexed row number
     * @param file zero-indexed column number
     * @param piece the piece to place
     * @throws IllegalArgumentException if square is out of bounds
     */
    public void setPiece(final int rank, final int file, final PieceType piece) throws IllegalArgumentException {
        if (rank < 0 || rank >= BOARD_SIZE || file < 0 || file >= BOARD_SIZE)
            throw new IllegalArgumentException("Rank and file must be within bounds");

        board[rank][file] = piece;
    }

    /**
     * Get the piece at the specified square.
     *
     * @param rank zero-indexed row number
     * @param file zero-indexed column number
     * @return an <code>Optional</code> containing the piece if present
     * @throws IllegalArgumentException if square is out of bounds
     */
    public Optional<PieceType> getPiece(final int rank, final int file) throws IllegalArgumentException {
        if (rank < 0 || rank >= BOARD_SIZE || file < 0 || file >= BOARD_SIZE)
            throw new IllegalArgumentException("Rank and file must be within bounds");

        return Optional.ofNullable(board[rank][file]);
    }

    public int evaluate() {
        // TODO: Write evaluation function.
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = BOARD_SIZE - 1; i >= 0; i--) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                PieceType piece = board[i][j];
                if (piece != null) {
                    sb.append(piece);
                } else {
                    sb.append('_');
                }
            }
            if (i > 0) sb.append("\n");
        }
        return sb.toString();
    }

    public enum PieceType {
        W_PAWN('P'),
        W_KNIGHT('N'),
        W_BISHOP('B'),
        W_ROOK('R'),
        W_QUEEN('Q'),
        W_KING('K'),

        B_PAWN('p'),
        B_KNIGHT('n'),
        B_BISHOP('b'),
        B_ROOK('r'),
        B_QUEEN('q'),
        B_KING('k');

        private final char symbol;

        PieceType(final char symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return String.valueOf(symbol);
        }
    }
}
