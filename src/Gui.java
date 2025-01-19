import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gui
{
    private JFrame frame = new JFrame("Chess");
    private JPanel backgroundPanel = new JPanel(new GridBagLayout());
    private JPanel boardPanel;
    private JButton[][] squareButtons = new JButton[8][8];

    public Gui()
    {
        guiSetup();
    }

    private void guiSetup()
    {
        frame.setLayout(new GridLayout(0, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardPanel = new JPanel(new GridLayout(8, 8));
        frame.setVisible(true);
        backgroundPanel.add(boardPanel);
        frame.add(backgroundPanel);
        backgroundPanel.setBackground(new Color(116, 120, 126));

        Color darkSquareColor = new Color(27, 94, 13);
        for (int y = 7; y >= 0; y--)
        {
            for (int x = 0; x < 8; x++)
            {
                Square sq = Game.board().getSquare(x, y);
                JButton currentButton = new JButton();
                currentButton.addActionListener(new ActionListener()
                {
                    final Square square = sq;
                    final JButton self = currentButton;

                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Game.selectSquare(square);
                    }
                });
                currentButton.setPreferredSize(new Dimension(64, 64));
                currentButton.setOpaque(true);
                currentButton.setBorderPainted(false);
                if ((x + y) % 2 != 0)
                {
                    currentButton.setBackground(darkSquareColor);
                }

                squareButtons[x][y] = currentButton;
                boardPanel.add(squareButtons[x][y]);
            }
        }
        updatePieces();
        frame.pack();
    }

    // Display piece icons on chess board
    public void updatePieces()
    {
        try
        {
            for (int x = 0; x < 8; x++)
            {
                for (int y = 0; y < 8; y++)
                {
                    if (Game.board().getSquare(x, y).hasPiece())
                    {
                        Piece currentPiece = Game.board().getSquare(x, y).piece();
                        if (currentPiece.isWhite()) // Set white piece icons
                        {
                            if (currentPiece.getClass() == Pawn.class)
                            {
                                BufferedImage pieceIcon = ImageIO.read(new File("resources/wPawn.png"));
                                squareButtons[x][y].setIcon(new ImageIcon(pieceIcon));
                            }
                            if (currentPiece.getClass() == Rook.class)
                            {
                                BufferedImage pieceIcon = ImageIO.read(new File("resources/wRook.png"));
                                squareButtons[x][y].setIcon(new ImageIcon(pieceIcon));
                            }
                            if (currentPiece.getClass() == Knight.class)
                            {
                                BufferedImage pieceIcon = ImageIO.read(new File("resources/wKnight.png"));
                                squareButtons[x][y].setIcon(new ImageIcon(pieceIcon));
                            }
                            if (currentPiece.getClass() == Bishop.class)
                            {
                                BufferedImage pieceIcon = ImageIO.read(new File("resources/wBishop.png"));
                                squareButtons[x][y].setIcon(new ImageIcon(pieceIcon));
                            }
                            if (currentPiece.getClass() == Queen.class)
                            {
                                BufferedImage pieceIcon = ImageIO.read(new File("resources/wQueen.png"));
                                squareButtons[x][y].setIcon(new ImageIcon(pieceIcon));
                            }
                            if (currentPiece.getClass() == King.class)
                            {
                                BufferedImage pieceIcon = ImageIO.read(new File("resources/wKing.png"));
                                squareButtons[x][y].setIcon(new ImageIcon(pieceIcon));
                            }
                        }
                        else
                        {
                            if (currentPiece.getClass() == Pawn.class)
                            {
                                BufferedImage pieceIcon = ImageIO.read(new File("resources/bPawn.png"));
                                squareButtons[x][y].setIcon(new ImageIcon(pieceIcon));
                            }
                            if (currentPiece.getClass() == Rook.class)
                            {
                                BufferedImage pieceIcon = ImageIO.read(new File("resources/bRook.png"));
                                squareButtons[x][y].setIcon(new ImageIcon(pieceIcon));
                            }
                            if (currentPiece.getClass() == Knight.class)
                            {
                                BufferedImage pieceIcon = ImageIO.read(new File("resources/bKnight.png"));
                                squareButtons[x][y].setIcon(new ImageIcon(pieceIcon));
                            }
                            if (currentPiece.getClass() == Bishop.class)
                            {
                                BufferedImage pieceIcon = ImageIO.read(new File("resources/bBishop.png"));
                                squareButtons[x][y].setIcon(new ImageIcon(pieceIcon));
                            }
                            if (currentPiece.getClass() == Queen.class)
                            {
                                BufferedImage pieceIcon = ImageIO.read(new File("resources/bQueen.png"));
                                squareButtons[x][y].setIcon(new ImageIcon(pieceIcon));
                            }
                            if (currentPiece.getClass() == King.class)
                            {
                                BufferedImage pieceIcon = ImageIO.read(new File("resources/bKing.png"));
                                squareButtons[x][y].setIcon(new ImageIcon(pieceIcon));
                            }
                        }
                    }
                    else
                    {
                        squareButtons[x][y].setIcon(null);
                    }
                }
            }
        }
        catch (IOException io)
        {
            System.err.println("Caught IOException: " + io.getMessage());
        }
    }

    // Create window to promote a pawn to a selected piece
    public void promotePiece(Square square) throws IOException
    {
        Game.setGamePaused(true);
        JFrame promotionFrame = new JFrame("Promote a pawn");
        JPanel promotionPanel = new JPanel(new GridLayout(1, 4));
        promotionFrame.add(promotionPanel);
        boolean pawnIsWhite = Game.getSelectedSquare().piece().isWhite();
        promotionFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        JButton queenButton = new JButton();
        queenButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Queen newQueen = new Queen(Game.board(), square, square.piece().isWhite());
                newQueen.setIsWhite(pawnIsWhite);
                square.setPiece(newQueen);
                updatePieces();
                Game.setGamePaused(false);
                promotionFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                promotionFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        queenButton.setPreferredSize(new Dimension(64, 64));
        queenButton.setOpaque(true);
        queenButton.setBorderPainted(false);

        JButton knightButton = new JButton();
        knightButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Knight newKnight = new Knight(Game.board(), square, square.piece().isWhite());
                newKnight.setIsWhite(pawnIsWhite);
                square.setPiece(newKnight);
                updatePieces();
                Game.setGamePaused(false);
                promotionFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                promotionFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        knightButton.setPreferredSize(new Dimension(64, 64));
        knightButton.setOpaque(true);
        knightButton.setBorderPainted(false);

        JButton rookButton = new JButton();
        rookButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Rook newRook = new Rook(Game.board(), square, square.piece().isWhite());
                newRook.setIsWhite(pawnIsWhite);
                square.setPiece(newRook);
                updatePieces();
                Game.setGamePaused(false);
                promotionFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                promotionFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        rookButton.setPreferredSize(new Dimension(64, 64));
        rookButton.setOpaque(true);
        rookButton.setBorderPainted(false);

        JButton bishopButton = new JButton();
        bishopButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Bishop newBishop = new Bishop(Game.board(), square, square.piece().isWhite());
                newBishop.setIsWhite(pawnIsWhite);
                square.setPiece(newBishop);
                updatePieces();
                Game.setGamePaused(false);
                promotionFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                promotionFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        bishopButton.setPreferredSize(new Dimension(64, 64));
        bishopButton.setOpaque(true);
        bishopButton.setBorderPainted(false);

        if (Game.getSelectedSquare().piece().isWhite())
        {
            BufferedImage queenIcon = ImageIO.read(new File("resources/wQueen.png"));
            queenButton.setIcon(new ImageIcon(queenIcon));
            BufferedImage knightIcon = ImageIO.read(new File("resources/wKnight.png"));
            knightButton.setIcon(new ImageIcon(knightIcon));
            BufferedImage rookIcon = ImageIO.read(new File("resources/wRook.png"));
            rookButton.setIcon(new ImageIcon(rookIcon));
            BufferedImage bishopIcon = ImageIO.read(new File("resources/wBishop.png"));
            bishopButton.setIcon(new ImageIcon(bishopIcon));
        }
        else
        {
            BufferedImage queenIcon = ImageIO.read(new File("resources/bQueen.png"));
            queenButton.setIcon(new ImageIcon(queenIcon));
            BufferedImage knightIcon = ImageIO.read(new File("resources/bKnight.png"));
            knightButton.setIcon(new ImageIcon(knightIcon));
            BufferedImage rookIcon = ImageIO.read(new File("resources/bRook.png"));
            rookButton.setIcon(new ImageIcon(rookIcon));
            BufferedImage bishopIcon = ImageIO.read(new File("resources/bBishop.png"));
            bishopButton.setIcon(new ImageIcon(bishopIcon));
        }

        promotionPanel.add(queenButton);
        promotionPanel.add(knightButton);
        promotionPanel.add(rookButton);
        promotionPanel.add(bishopButton);
        promotionFrame.pack();
        promotionFrame.setVisible(true);
    }
}

