package ChessCore;

import ChessCore.Pieces.*;

public final class ClassicBoardInitializer implements BoardInitializer {
    private static final BoardInitializer instance = new ClassicBoardInitializer();

    private ClassicBoardInitializer() {
    }

    public static BoardInitializer getInstance() {
        return instance;
    }

    @Override
    public Piece[][] initialize() {
       PiecesFactory pieces = new PiecesFactory();
        Piece[][] initialState = {
            {pieces.createPiece("ROOK", Player.WHITE), pieces.createPiece("KNIGHT", Player.WHITE), pieces.createPiece("BISHOP", Player.WHITE), pieces.createPiece("QUEEN", Player.WHITE),pieces.createPiece("KING", Player.WHITE),pieces.createPiece("BISHOP", Player.WHITE), pieces.createPiece("KNIGHT", Player.WHITE), pieces.createPiece("ROOK", Player.WHITE)},
            {pieces.createPiece("PAWN", Player.WHITE), pieces.createPiece("PAWN", Player.WHITE),pieces.createPiece("PAWN", Player.WHITE), pieces.createPiece("PAWN", Player.WHITE), pieces.createPiece("PAWN", Player.WHITE), pieces.createPiece("PAWN", Player.WHITE),pieces.createPiece("PAWN", Player.WHITE), pieces.createPiece("PAWN", Player.WHITE)},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {pieces.createPiece("PAWN", Player.BLACK), pieces.createPiece("PAWN", Player.BLACK),pieces.createPiece("PAWN", Player.BLACK),pieces.createPiece("PAWN", Player.BLACK),pieces.createPiece("PAWN", Player.BLACK),pieces.createPiece("PAWN", Player.BLACK),pieces.createPiece("PAWN", Player.BLACK), pieces.createPiece("PAWN", Player.BLACK)},
            {pieces.createPiece("ROOK", Player.BLACK),pieces.createPiece("KNIGHT", Player.BLACK),pieces.createPiece("BISHOP", Player.BLACK), pieces.createPiece("QUEEN", Player.BLACK),pieces.createPiece("KING", Player.BLACK),pieces.createPiece("BISHOP", Player.BLACK),pieces.createPiece("KNIGHT", Player.BLACK), pieces.createPiece("ROOK", Player.BLACK)}
        };
        return initialState;
    }
}
