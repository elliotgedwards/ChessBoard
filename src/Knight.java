import java.lang.Math;

public class Knight extends Piece
{
    private boolean isWhite; // false = black, true = white
    private final int value = 3;
    private Board board;
    private Square square;
    private int x;
    private int y;

    public Knight(Board board, Square square, boolean isWhite)
    {
        this.square = square;
        this.board = board;
        this.isWhite = isWhite;
        x = square.x();
        y = square.y();
    }

    public boolean canMoveTo(Square toSquare)
    {
        if(toSquare == square)
        {
            return false;
        }
        if(toSquare.hasPiece())
        {
            if(toSquare.piece().isWhite() == isWhite)
            {
                return false;
            }
        }
        if((Math.abs(toSquare.x() - x) == 2 && Math.abs(toSquare.y() - y) == 1) || (Math.abs(toSquare.y() - y) == 2 && Math.abs(toSquare.x() - x) == 1))
        {
            return true;
        }
        return false;
    }
    public void setSquare(Square square)
    {

        this.square.setPiece(null);
        this.square = square;
        x = square.x();
        y = square.y();
        square.setPiece(this);
    }
}
