public class Rook extends Piece
{
	private boolean isWhite; // false = black, true = white
	private final static int value = 5;
	private Board board;
	private Square square;
	private int x;
	private int y;
	private boolean hasMoved = false;

	public Rook(Board board, Square square, boolean isWhite)
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
				if(board.getSquare(x, xTemp).hasPiece())
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
		hasMoved = true;
	}
	public boolean hasMoved()
	{
		return hasMoved;
	}
}
