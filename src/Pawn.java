import java.lang.Math;

public class Pawn extends Piece
{
	private boolean isWhite; // false = black, true = white
	private final int value = 1;
	private Board board;
	private boolean hasMoved = false;
	private Square square;
	private int x;
	private int y;
	
	public Pawn(Board board, Square square, boolean isWhite)
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
		// rules for white pawn movement
		if(isWhite)
		{
			if(x == toSquare.x() && !toSquare.hasPiece())
			{
				// Moving forward 1 square
				if(toSquare.y() == y + 1)
				{
					return true;
				}
				// Moving forward 2 squares
				if(toSquare.y() == y + 2 && !board.getSquare(x, y + 1).hasPiece() && !hasMoved)
				{
					return true;
				}
			}
			// Taking a piece diagonally
			if(Math.abs(toSquare.x() - square.x()) == 1 && square.y() + 1 == toSquare.y() && toSquare.hasPiece())
			{
				return true;
				
			}
			// Taking a piece en passant
			if(Game.lastMove()[1] != null && y == 4)
			{
				if(Game.lastMove()[1].piece().getClass() == Pawn.class
				  && Game.lastMove()[0].y() == 6
				  && Game.lastMove()[1].y() == 4
				  && Math.abs(Game.lastMove()[1].x() - x) == 1
				  && toSquare.x() == Game.lastMove()[1].x()
				  && toSquare.y() - 1 == Game.lastMove()[1].y())
				{
					if(toSquare.x() == Game.lastMove()[1].x() && toSquare.y() - 1 == Game.lastMove()[1].y())
					{
						return true;
					}
				}
			}
			return false;
		}
		// rules for black pawn movement
		else
		{
			if(x == toSquare.x() && !toSquare.hasPiece())
			{
				// Moving forward 1 square
				if(toSquare.y() == y - 1)
				{
					return true;
					
				}
				// Moving forward 2 squares
				if(toSquare.y() == y - 2 && !board.getSquare(x, y - 1).hasPiece() && !hasMoved)
				{
					return true;
				}
			}
			// Taking a piece diagonally
			if(Math.abs(toSquare.x() - square.x()) == 1
			  && square.y() - 1 == toSquare.y() && toSquare.hasPiece())
			{
				return true;
			}
			// Taking a piece en passant
			if(Game.lastMove()[1] != null
			  && y == 3)
			{
				if(Game.lastMove()[1].piece().getClass() == Pawn.class
				  && Game.lastMove()[0].y() == 1
				  && Game.lastMove()[1].y() == 3
				  && Math.abs(Game.lastMove()[1].x() - x) == 1
				  && toSquare.x() == Game.lastMove()[1].x()
				  && toSquare.y() + 1 == Game.lastMove()[1].y())
				{
					if(toSquare.x() == Game.lastMove()[1].x() && toSquare.y() + 1 == Game.lastMove()[1].y())
					{
						return true;
					}
				}
			}
			return false;
		}
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
}
