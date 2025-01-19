public abstract class Piece
{
    private boolean isWhite;
    private int value;
    private Board board;
    private Square square;
    private int x;
    private int y;
    private boolean hasMoved = false;

    abstract boolean canMoveTo(Square toSquare);

    public void setSquare(Square square)
    {
        this.square.setPiece(null);
        this.square = square;
        x = square.x();
        y = square.y();
        square.setPiece(this);
    }
    public void setIsWhite(boolean isWhite)
    {
        this.isWhite = isWhite;
    }
    
    public void setHasMoved(boolean hasMoved)
    {
        this.hasMoved = hasMoved;
    }

    public int getValue()
    {
        return value;
    }

    public boolean isWhite()
    {
        return isWhite;
    }
    public boolean hasMoved()
    {
        return hasMoved;
    }


    public Square getSquare()
    {
        return square;
    }

}
