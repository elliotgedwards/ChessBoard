import java.io.IOException;

public class Game
{
    private static boolean isWhitePlaying = true;
    private static Board board = new Board();
    private static Square[] lastMove = new Square[2]; // lastMove[0] == square last moved from, lastMove[1] == square last moved to
    private static Piece lastPieceTaken;
    private static Square selectedSquare;
    private static boolean gamePaused = false;
    private static Gui gui = new Gui();

    public static void setLastMove(Square fromSquare, Square toSquare)
    {
        lastMove[0] = fromSquare;
        lastMove[1] = toSquare;
    }

    public static void setLastPieceTaken(Piece pieceTaken)
    {
        lastPieceTaken = pieceTaken;
    }

    public static void setGamePaused(boolean x)
    {
        gamePaused = x;
    }

    public static void selectSquare(Square square)
    {
        if (gamePaused) return;
        if (selectedSquare == square) return;

        // Prevent empty squares or opponent's pieces from being selected
        if (selectedSquare == null)
        {
            if (!square.hasPiece())
            {
                return;
            }
            if (square.piece().isWhite() != isWhitePlaying)
            {
                return;
            }
        }
        // Select a piece to move
        if (square.hasPiece())
        {
            if (square.piece().isWhite() == isWhitePlaying)
            {
                selectedSquare = square;
                return;
            }
        }
        // Attempt to make a move to clicked square if a piece is selected
        if (selectedSquare.hasPiece())
        {
            if (selectedSquare.piece().canMoveTo(square)
                    && selectedSquare.piece().isWhite() == isWhitePlaying
                    && !endsInCheck(selectedSquare, square))
            {
                // En passant
                if (selectedSquare.piece().getClass() == Pawn.class
                        && Game.lastMove()[1] != null)
                {
                    // White en passant
                    if (selectedSquare.y() == 4)
                    {
                        if (Game.lastMove()[1].piece().getClass() == Pawn.class
                                && Game.lastMove()[0].y() == 6
                                && Game.lastMove()[1].y() == 4
                                && Math.abs(Game.lastMove()[1].x() - selectedSquare.x()) == 1
                                && square.x() == Game.lastMove()[1].x()
                                && square.y() - 1 == Game.lastMove()[1].y())
                        {
                            if (square.x() == Game.lastMove()[1].x()
                                    && square.y() - 1 == Game.lastMove()[1].y())
                            {
                                board.getSquare(square.x(), 4).setPiece(null);
                            }
                        }
                    }
                    // Black en passant
                    if (!selectedSquare.piece().isWhite()
                            && selectedSquare.y() == 3)
                    {
                        if (Game.lastMove()[1].piece().getClass() == Pawn.class
                                && Game.lastMove()[0].y() == 1
                                && Game.lastMove()[1].y() == 3
                                && Math.abs(Game.lastMove()[1].x() - selectedSquare.x()) == 1
                                && square.x() == Game.lastMove()[1].x()
                                && square.y() + 1 == Game.lastMove()[1].y())
                        {
                            board.getSquare(square.x(), 3).setPiece(null);
                        }
                    }

                }
                // Castling
                if (selectedSquare.piece().getClass() == King.class
                        && Math.abs(square.x() - selectedSquare.x()) == 2)
                {
                    // Castling to right
                    if (square.x() > selectedSquare.x())
                    {
                        board.getSquare(7, selectedSquare.y()).piece().setSquare(board.getSquare(square.x() - 1,
                                                                                                 selectedSquare.y()));
                    }
                    // Castling to the left
                    if (square.x() < selectedSquare.x())
                    {
                        board.getSquare(0, selectedSquare.y()).piece().setSquare(board.getSquare(square.x() + 1,
                                                                                                 selectedSquare.y()));
                    }
                }
                // Pawn promotion
                if (selectedSquare.piece().getClass() == Pawn.class && (square.y() == 0 || square.y() == 7))
                {
                    try
                    {
                        gui.promotePiece(square);
                    }
                    catch (IOException e)
                    {
                        throw new RuntimeException(e);
                    }
                }

                // Process taken after any valid move is made
                lastPieceTaken = square.piece();
                lastMove[0] = selectedSquare;
                lastMove[1] = square;
                selectedSquare.piece().setSquare(square);
                isWhitePlaying = !isWhitePlaying;
                gui.updatePieces();
                selectedSquare = null;
            }
        }
    }

    public static boolean isWhitePlaying()
    {
        return isWhitePlaying;
    }

    public static Square getSelectedSquare()
    {
        return selectedSquare;
    }

    public static Square[] lastMove()
    {
        return lastMove;
    }

    public static Board board()
    {
        return board;
    }

    public static Piece lastPieceTaken()
    {
        return lastPieceTaken;
    }

    public static boolean endsInCheck(Square fromSquare, Square toSquare)
    {
        Piece king = board.getCurrentKing();
        Piece pieceTaken = toSquare.piece();
        boolean tempHasMoved = fromSquare.piece().hasMoved();

        fromSquare.piece().setSquare(toSquare);

        for (int x1 = 0; x1 < 8; x1++)
        {
            for (int y1 = 0; y1 < 8; y1++)
            {
                if (board.getSquare(x1, y1).hasPiece())
                {
                    if (board.getSquare(x1, y1).piece().canMoveTo(king.getSquare()))
                    {
                        toSquare.piece().setSquare(fromSquare);
                        toSquare.setPiece(pieceTaken);
                        fromSquare.piece().setHasMoved(tempHasMoved);
                        return true;
                    }
                }
            }
        }
        toSquare.piece().setSquare(fromSquare);
        toSquare.setPiece(pieceTaken);
        fromSquare.piece().setHasMoved(tempHasMoved);
        return false;
    }

    public static void main(String[] args)
    {
    }
    //test
}
