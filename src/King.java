import java.lang.Math;

public class King extends Piece
{
	private boolean isWhite; // false = black, true = white
	private final int value = 0;
	private Board board;
	private Square square;
	private int x;
	private int y;
	private boolean hasMoved = false;
	
	public King(Board board, Square square, boolean isWhite)
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
		// Move to adjacent square
		if(Math.abs(toSquare.x() - x) <= 1
		  && Math.abs(toSquare.y() - y) <= 1)
		{
			return true;
		}
		// Castling
		if(toSquare.y() == y
		  && !hasMoved
		  && !Game.endsInCheck(square, square))
		{
			// Castling to the right
			if(x + 2 == toSquare.x()
			  && board.getSquare(7, y).hasPiece())
			{
				for(int i = x + 1; i < 7; i++)
				{
					if(board.getSquare(i, y).hasPiece())
					{
						return false;
					}
				}
				if(board.getSquare(7, y).piece().getClass() == Rook.class)
				{
					if(!board.getSquare(7, y).piece().hasMoved()
					  && !Game.endsInCheck(square, board.getSquare(x + 1, y)))
					{
						return true;
					}
				}
			}
			//Castling to the left
			if(x - 2 == toSquare.x()
			  && board.getSquare(0, y).hasPiece())
			{
				for(int i = x - 1; i > 0; i--)
				{
					if(board.getSquare(i, y).hasPiece())
					{
						return false;
					}
				}
				if(board.getSquare(0, y).piece().getClass() == Rook.class)
				{
					if(!board.getSquare(0, y).piece().hasMoved()
					  && !Game.endsInCheck(square, board.getSquare(x - 1, y)))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	public void setHasMoved(boolean hasMoved)
	{
		this.hasMoved = hasMoved;
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
	public Square getSquare()
	{
		return square;
	}
	public boolean hasMoved()
	{
		return hasMoved;
	}
}
