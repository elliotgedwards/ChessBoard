import java.lang.Math;

public class Bishop extends Piece
{
	private boolean isWhite; // false = black, true = white
	private final static int value = 3;
	private Board board;
	private Square square;
	private int x;
	private int y;
	
	public Bishop(Board board, Square square, boolean isWhite)
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
		// Move along diagonals
		if(Math.abs(toSquare.x() - x) == Math.abs(toSquare.y() - y))
		{
			int xTemp = x;
			int yTemp = y;
			while(xTemp != toSquare.x() && yTemp != toSquare.y())
			{
				if(board.getSquare(xTemp, yTemp) != square && board.getSquare(xTemp, yTemp).hasPiece())
				{
					return false;
				}
				if(yTemp < toSquare.y())
				{
					yTemp++;
				}
				else if(yTemp > toSquare.y())
				{
					yTemp--;
				}
				if(xTemp < toSquare.x())
				{
					xTemp++;
				}
				else if(xTemp > toSquare.x())
				{
					xTemp--;
				}
			}
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
