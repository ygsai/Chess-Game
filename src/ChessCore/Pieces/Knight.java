package ChessCore.Pieces;

import ChessCore.*;

public final class Knight extends Piece {
    public Knight(Player owner) {
        super(owner);
    }

    @Override
    public boolean isValidMove(Move move, ChessGame game) {
        int deltaX = move.getAbsDeltaX();
        int deltaY = move.getAbsDeltaY();
        return (deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1);
    }

    @Override
    public boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board) {
        MoveBuilder move = new MoveBuilder();
                move.fromSquare(pieceSquare);
                move.toSquare(squareUnderAttack);
        Move m = move.build();
        int deltaX = m.getAbsDeltaX();
        int deltaY = m.getAbsDeltaY();
        return (deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1);
    }
}
