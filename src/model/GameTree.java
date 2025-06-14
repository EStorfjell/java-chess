package model;

/**
 * Minimax tree for move searching.
 *
 * @author Emilie Storfjell
 */
public class GameTree<T> {
    private GameTree<T> leftChild;

    private GameTree<T> rightChild;

    private T node;

    public GameTree() {
        leftChild = null;
        rightChild = null;
    }
}
