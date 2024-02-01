/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChessCore;

import ChessCore.Pieces.Piece;

/**
 *
 * @author yugoslavia
 */
        public class MoveBuilder {
        private Square fromSquare;
        private Square toSquare;
        private PawnPromotion pawnPromotion;
        private Piece capturedPiece;

        public MoveBuilder fromSquare(Square fromSquare) {
            this.fromSquare = fromSquare;
            return this;
        }

        public MoveBuilder toSquare(Square toSquare) {
            this.toSquare = toSquare;
            return this;
        }

        public MoveBuilder pawnPromotion(PawnPromotion pawnPromotion) {
            this.pawnPromotion = pawnPromotion;
            return this;
        }

        public MoveBuilder capturedPiece(Piece capturedPiece) {
            this.capturedPiece = capturedPiece;
            return this;
        }

        public Move build() {
            // Validate that required fields are set
            if (fromSquare == null || toSquare == null) {
                throw new IllegalStateException("fromSquare and toSquare are required");
            }

            return new Move(fromSquare, toSquare, pawnPromotion, capturedPiece);
        }

    
    }
