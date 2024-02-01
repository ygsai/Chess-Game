package ChessCore.Pieces;

import ChessCore.*;

public final class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner);
    }

    @Override
    public boolean isValidMove(Move move, ChessGame game) {
        return move.isDiagonalMove() && !game.isTherePieceInBetween(move);
    }

    @Override
    public boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board) {
        MoveBuilder move = new MoveBuilder();
                move.fromSquare(pieceSquare);
                move.toSquare(squareUnderAttack);
        Move m = move.build();
        return m.isDiagonalMove() && !board.isTherePieceInBetween(m);
    }
}
