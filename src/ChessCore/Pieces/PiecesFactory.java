/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChessCore.Pieces;

import ChessCore.Player;

/**
 *
 * @author yugoslavia
 */
public class PiecesFactory {
    public Piece createPiece(String piece,Player owner){
        if(piece==null)
            return null;
        switch (piece) {
            case "PAWN":
                return new Pawn(owner);
            case "BISHOP":
                return new Bishop(owner);
            case "KING":
                return new King(owner);
            case "KNIGHT":
                return new Knight(owner);
            case "QUEEN":
                return new Queen(owner);
            case "ROOK":
                return new Rook(owner);
            default:
                throw new IllegalArgumentException("Unknown Piece "+piece);
        }
    }
}
