import java.lang.Cloneable;

public class Board implements Cloneable
{
	private Square[][] squares = new Square[8][8];
	private Piece whiteKing, blackKing;
	
	public Board()
	{
		resetBoard();
	}
	
	public void resetBoard()
	{
		for(int row = 0; row < 8; row++)
		{
			for(int col = 0; col < 8; col++)
			{
				squares[row][col] = new Square(row, col);
			}
		}
		/* White pieces
		 *
		 * I ran into an issue which I couldn't figure out. The white pieces should have had their "isWhite" variables
		 * set to true in declaration, but they default to false. I had to set each white piece's "isWhite" variable to
		 * true with an extra method call to get around this issue.
		 * */
		for(int i = 0; i < 8; i++)
		{
			squares[i][1].setPiece(new Pawn(this, squares[i][1], true));// White Pawns
			squares[i][1].piece().setIsWhite(true);
		}
		squares[0][0].setPiece(new Rook(this, squares[0][0], true)); // White Rook on a1
		squares[0][0].piece().setIsWhite(true);
		squares[1][0].setPiece(new Knight(this, squares[1][0], true)); // White Knight on b1
		squares[1][0].piece().setIsWhite(true);
		squares[2][0].setPiece(new Bishop(this, squares[2][0], true)); // White Bishop on c1
		squares[2][0].piece().setIsWhite(true);
		squares[3][0].setPiece(new Queen(this, squares[3][0], true)); // White Queen on d1
		squares[3][0].piece().setIsWhite(true);
		squares[4][0].setPiece(new King(this, squares[4][0], true)); // White King on e1
		squares[4][0].piece().setIsWhite(true);
		squares[5][0].setPiece(new Bishop(this, squares[5][0], true)); // White Bishop on f1
		squares[5][0].piece().setIsWhite(true);
		squares[6][0].setPiece(new Knight(this, squares[6][0], true)); // White Knight on g1
		squares[6][0].piece().setIsWhite(true);
		squares[7][0].setPiece(new Rook(this, squares[7][0], true)); // White Rook on h1
		squares[7][0].piece().setIsWhite(true);
		
		// Black pieces
		for(int i = 0; i < 8; i++)
		{
			squares[i][6].setPiece(new Pawn(this, squares[i][6], false)); // Black Pawns
		}
		squares[0][7].setPiece(new Rook(this, squares[0][7], false)); // Black Rook on a1
		squares[1][7].setPiece(new Knight(this, squares[1][7], false)); // Black Knight on b1
		squares[2][7].setPiece(new Bishop(this, squares[2][7], false)); // Black Bishop on c1
		squares[3][7].setPiece(new King(this, squares[3][7], false)); // Black King on e1
		squares[4][7].setPiece(new Queen(this, squares[4][7], false)); // Black Queen on d1
		squares[5][7].setPiece(new Bishop(this, squares[5][7], false)); // Black Bishop on f1
		squares[6][7].setPiece(new Knight(this, squares[6][7], false)); // Black Knight on g1
		squares[7][7].setPiece(new Rook(this, squares[7][7], false)); // Black Rook on h1
		
		whiteKing = squares[4][0].piece();
		blackKing = squares[3][7].piece();
	}
	
	public Square getSquare(int x, int y)
	{
		assert x < 8 && y < 8;
		
		return squares[x][y];
	}
	
	public Piece getCurrentKing()
	{
		if(Game.isWhitePlaying())
		{
			return whiteKing;
		}
		else
		{
			return blackKing;
		}
	}
}
