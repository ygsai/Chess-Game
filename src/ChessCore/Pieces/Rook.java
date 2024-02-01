package ChessCore.Pieces;

import ChessCore.*;

public final class Rook extends Piece {
    public Rook(Player owner) {
        super(owner);
    }

    @Override
    public boolean isValidMove(Move move, ChessGame game) {
        return (move.isHorizontalMove() || move.isVerticalMove()) && !game.isTherePieceInBetween(move);
    }

    @Override
    public boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board) {
        MoveBuilder move = new MoveBuilder();
                move.fromSquare(pieceSquare);
                move.toSquare(squareUnderAttack);
        Move m = move.build();
        return (m.isHorizontalMove() || m.isVerticalMove()) && !board.isTherePieceInBetween(m);
    }
}
