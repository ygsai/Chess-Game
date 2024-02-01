package Frontend;

import ChessCore.BoardFile;
import ChessCore.BoardRank;
import ChessCore.ChessBoard;
import ChessCore.Pieces.Pawn;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ChessCore.ClassicChessGame;
import ChessCore.GameObserver;
import ChessCore.Move;
import ChessCore.MoveBuilder;
import ChessCore.PawnPromotion;
import ChessCore.Pieces.Bishop;
import ChessCore.Pieces.King;
import ChessCore.Pieces.Knight;
import ChessCore.Pieces.Piece;
import ChessCore.Pieces.PiecesFactory;
import ChessCore.Pieces.Queen;
import ChessCore.Pieces.Rook;
import ChessCore.Player;
import ChessCore.Square;
import ChessCore.Utilities;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class GUIClass extends JFrame implements GameObserver {

    private final ClassicChessGame game = ClassicChessGame.getInstance();
    private Square clickedSquare;
    public static GUIClass gui = null;

    private GUIClass() {
        game.addObserver(this);
        JPanel pn = new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (white) {
                            g.setColor(Color.LIGHT_GRAY);
                        } else {
                            g.setColor(Color.DARK_GRAY);
                        }
                        g.fillRect(i * 70, j * 70, 70, 70);
                        white = !white;
                        if (game.getWhoseTurn() == Player.BLACK) {
                            try {

                                BoardFile file = getFile(i);
                                BoardRank rank = getRank(7 - j);
                                Square square = new Square(file, rank);
                                Piece piece = game.getPieceAtSquare(square);
                                Image pieceImage = getPieceImage(piece);

                                if (pieceImage != null) {
                                    g.drawImage(pieceImage, i * 70, j * 70, null);
                                }
                            } catch (IOException ex) {
                                System.out.println("error");
                            }
                        } else {
                            try {

                                BoardFile file = getFile(i);
                                BoardRank rank = getRank(j);
                                Square square = new Square(file, rank);
                                Piece piece = game.getPieceAtSquare(square);
                                Image pieceImage = getPieceImage(piece);

                                if (pieceImage != null) {
                                    g.drawImage(pieceImage, i * 70, j * 70, null);
                                }
                            } catch (IOException ex) {
                                System.out.println("error");
                            }
                        }
                    }
                    white = !white;
                }
                if (clickedSquare != null) {
                    g.setColor(Color.RED);
                    if (game.getWhoseTurn() == Player.BLACK) {
                        g.drawRect(clickedSquare.getFile().ordinal() * 70,
                                (7 - clickedSquare.getRank().ordinal()) * 70, 70, 70);
                    } else {
                        g.drawRect(clickedSquare.getFile().ordinal() * 70,
                                clickedSquare.getRank().ordinal() * 70, 70, 70);
                    }
                }

                highlightValidMoves(g);
                highlightKingInCheck(g);
            }

        };
        pn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClick(e);
            }
        });
        pn.setBounds(0, 0, 600, 600);
        add(pn);
        //undo button addition
        JButton undoButton = new JButton("Undo");
        undoButton.setBounds(600, 300, 100, 20);

        undoButton.addActionListener(e -> {

            undoButtonClick();

        });
        this.add(undoButton);

    }

    public static GUIClass getInstance() {
        if (gui == null) {
            gui = new GUIClass();
        }
        return gui;
    }

    public static void main(String[] args) {
        GUIClass frame = getInstance();
        frame.setLayout(null);
        frame.setBounds(0, 0, 700, 600);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void undoButtonClick() {
        if (!game.boardStack.isEmpty()) {
            ChessBoard previousBoard = game.boardStack.pop();
            game.setBoard(previousBoard);
            game.whoseTurn = Utilities.revertPlayer(game.whoseTurn);

            repaint();
        } else {
            JOptionPane.showMessageDialog(null, "Cannot undo further.");
        }
    }

    public BoardFile getFile(int i) {
        switch (i) {
            case 0: {
                return BoardFile.A;
            }
            case 1: {
                return BoardFile.B;
            }
            case 2: {
                return BoardFile.C;
            }
            case 3: {
                return BoardFile.D;
            }
            case 4: {
                return BoardFile.E;
            }
            case 5: {
                return BoardFile.F;
            }
            case 6: {
                return BoardFile.G;
            }
            case 7: {
                return BoardFile.H;
            }
            default: {
            }
        }
        return null;
    }

    public static BoardRank getRank(int j) {

        switch (j) {
            case 0: {
                return BoardRank.FIRST;
            }
            case 1: {
                return BoardRank.SECOND;
            }
            case 2: {
                return BoardRank.THIRD;
            }
            case 3: {
                return BoardRank.FORTH;
            }
            case 4: {
                return BoardRank.FIFTH;
            }
            case 5: {
                return BoardRank.SIXTH;
            }
            case 6: {
                return BoardRank.SEVENTH;
            }
            case 7: {
                return BoardRank.EIGHTH;
            }
            default: {
            }
        }

        return null;
    }

    public static Image getPieceImage(Piece piece) throws IOException {

        if (piece != null) {
            try {
                if (piece instanceof Pawn) {
                    if (piece.getOwner().equals(Player.WHITE)) {
                        return ImageIO.read(GUIClass.class.getResource("WhitePawn.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    } else {
                        return ImageIO.read(GUIClass.class.getResource("BlackPawn.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    }
                } else if (piece instanceof Queen) {
                    if (piece.getOwner().equals(Player.WHITE)) {
                        return ImageIO.read(GUIClass.class.getResource("WhiteQueen.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    } else {
                        return ImageIO.read(GUIClass.class.getResource("BlackQueen.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    }
                } else if (piece instanceof Rook) {
                    if (piece.getOwner().equals(Player.WHITE)) {
                        return ImageIO.read(GUIClass.class.getResource("WhiteRook.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    } else {
                        return ImageIO.read(GUIClass.class.getResource("BlackRook.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    }
                } else if (piece instanceof Knight) {
                    if (piece.getOwner().equals(Player.WHITE)) {
                        return ImageIO.read(GUIClass.class.getResource("WhiteKnight.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    } else {
                        return ImageIO.read(GUIClass.class.getResource("BlackKnight.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    }
                } else if (piece instanceof King) {
                    if (piece.getOwner().equals(Player.WHITE)) {
                        return ImageIO.read(GUIClass.class.getResource("WhiteKing.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    } else {
                        return ImageIO.read(GUIClass.class.getResource("BlackKing.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    }
                } else if (piece instanceof Bishop) {
                    if (piece.getOwner().equals(Player.WHITE)) {
                        return ImageIO.read(GUIClass.class.getResource("WhiteBishop.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    } else {
                        return ImageIO.read(GUIClass.class.getResource("BlackBishop.png")).getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                    }
                }
            } catch (IOException e) {
                System.out.println("error occured");
            }
        }
        return null;

    }
public void mouseClick(MouseEvent e) {
    int cy = e.getY();
    int cx = e.getX();
    int f = cx / 70;
    int r;
    if (game.getWhoseTurn() == Player.BLACK) {
        r = 7 - (cy / 70);
    } else {
        r = cy / 70;
    }
    BoardFile file = getFile(f);
    BoardRank rank = getRank(r);
    Square currentSquare = new Square(file, rank);
    Piece currentPiece = game.getPieceAtSquare(currentSquare);
    if (clickedSquare != null) {
        if (currentPiece instanceof Pawn && (rank == BoardRank.EIGHTH || rank == BoardRank.FIRST)) {
            // Handle promotion
            promotePawn(currentSquare, clickedSquare, currentPiece);
        } else {
            MoveBuilder move = new MoveBuilder();
                move.fromSquare(clickedSquare);
                move.toSquare(currentSquare);
             Move m=   move.build();

            game.makeMove(m);
        }
        clickedSquare = null;
    } else {
        clickedSquare = currentSquare;
    }
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
    frame.repaint();
}

private void promotePawn(Square fromSquare, Square toSquare, Piece promotedPiece) {
    Object[] options = {"None", "Knight", "Bishop", "Rook", "Queen"};
    PiecesFactory pieces = new PiecesFactory();
    int choice = JOptionPane.showOptionDialog(null, "Choose a piece for promotion:", "Pawn Promotion", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    switch (choice) {
        case 0: {
            MoveBuilder move = new MoveBuilder();
                move.fromSquare(fromSquare);
                move.toSquare(toSquare);
             Move m=   move.build();
            game.makeMove(m);
            break;
        }
        case 1:
            promotedPiece = pieces.createPiece("Knight", game.getWhoseTurn());
            game.board.setPieceAtSquare(fromSquare,promotedPiece);
            break;
        case 2:
            promotedPiece = pieces.createPiece("Bishop", game.getWhoseTurn());
            game.board.setPieceAtSquare(fromSquare,promotedPiece);
            break;
        case 3:
            promotedPiece = pieces.createPiece("Rook", game.getWhoseTurn());
            game.board.setPieceAtSquare(fromSquare,promotedPiece);
            break;
        case 4:
            promotedPiece = pieces.createPiece("Queen", game.getWhoseTurn());
            game.board.setPieceAtSquare(fromSquare,promotedPiece);
            break;
    }
    MoveBuilder move = new MoveBuilder();
                move.fromSquare(fromSquare);
                move.toSquare(toSquare);
             Move m=   move.build();
        game.makeMove(m);
}


    private void highlightValidMoves(Graphics g) {
        if (clickedSquare != null) {
            ArrayList<Square> validMoves = (ArrayList<Square>) game.getAllValidMovesFromSquare(clickedSquare);
            g.setColor(Color.PINK);
            for (int i = 0; i < validMoves.size(); i++) {
                Square highlighted = validMoves.get(i);
                int x = highlighted.getFile().getValue() * 70;
                int y;
                if (game.getWhoseTurn() == Player.BLACK) {
                    y = (7 - highlighted.getRank().getValue()) * 70;
                } else {
                    y = highlighted.getRank().getValue() * 70;
                }
                g.fillRect(x, y, 70, 70);
            }
        }
    }

    private void highlightKingInCheck(Graphics g) {
        Player currentPlayer = game.getWhoseTurn();
        if (Utilities.isInCheck(currentPlayer, game.getBoard())) {
            g.setColor(Color.RED);

            Square kingSquare = Utilities.getKingSquare(currentPlayer, game.getBoard());
            int x = kingSquare.getFile().getValue() * 70;
            int y;
            if (game.getWhoseTurn() == Player.BLACK) {
                y = (7 - kingSquare.getRank().getValue()) * 70;
            } else {
                y = kingSquare.getRank().getValue() * 70;
            }
            g.fillRect(x, y, 70, 70);
        }
    }

    @Override
    public void onChessGameStateChanged() {
        this.repaint();
    }

}
