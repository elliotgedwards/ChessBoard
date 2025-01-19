public class Square
{
	private Piece piece;
	private int x;
	private int y;
	
	public Square(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setPiece(Piece piece)
	{
		this.piece = piece;
	}
	
	public int x()
	{
		return x;
	}
	public int y()
	{
		return y;
	}
	public Piece piece()
	{
		return piece;
	}
	public boolean hasPiece()
	{
		if(piece == null)
		{
			return false;
		}
		return true;
	}
	
}
