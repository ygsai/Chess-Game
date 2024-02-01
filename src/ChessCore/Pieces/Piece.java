package ChessCore.Pieces;

import ChessCore.*;
import java.io.Serializable;

public abstract class Piece implements Serializable{
    private final Player owner;

    protected Piece(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public abstract boolean isValidMove(Move move, ChessGame game);
    public abstract boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board);
}
