import java.lang.Math;

public class Queen extends Piece
{
	private boolean isWhite; // false = black, true = white
	private final static int value = 9;
	private Board board;
	private Square square;
	private int x;
	private int y;
	
	public Queen(Board board, Square square, boolean isWhite)
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
		if(Math.abs(toSquare.x() - x) == Math.abs(toSquare.y() - y)) // Move along diagonals
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
		// Move along y-axis
		if(toSquare.x() == x)
		{
			int yTemp;
			if(y < toSquare.y())
			{
				yTemp = y + 1;
			}
			else
			{
				yTemp = y - 1;
			}
			
			while(yTemp != toSquare.y())
			{
				if(board.getSquare(x, yTemp).hasPiece())
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
			}
			return true;
		}
		// Move along x-axis
		if(toSquare.y() == y)
		{
			int xTemp;
			if(x < toSquare.x())
			{
				xTemp = x + 1;
			}
			else
			{
				xTemp = x - 1;
			}
			
			while(xTemp != toSquare.x())
			{
				if(board.getSquare(xTemp, y).hasPiece())
				{
					return false;
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
