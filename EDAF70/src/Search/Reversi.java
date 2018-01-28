package Search;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class Reversi implements ActionListener {

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// Private Members
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	private final JPanel GUI = new JPanel(new BorderLayout(3, 3));
    private ReversiButton[][] ReversiBoard = new ReversiButton[8][8];
    private JPanel reversiBoard;
    private static final String COLS = "ABCDEFGH";
    private static GameBoard gb;
    
    private BasePlayer Player1;
    private BasePlayer Player2;

    
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// Main Method
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%    
	public static void main(String[] args) {
		
		// creates the Reversi class
		Reversi rvs = new Reversi();
		
		
		// set up the frame
		JFrame frame = new JFrame("Reversi by se4054pf-s and ax5006bi-s");
        frame.add(rvs.getGui());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
		
	}
	
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// Constructor
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	Reversi() {
		initializeGUI();
		
		newGame();
	}
	
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// Public Members
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
	public final JComponent getReversiBoard() {
        return reversiBoard;
    }

    public final JComponent getGui() {
        return GUI;
    } 
	
	public final void initializeGUI() {
		
		// Customize the content panel
		GUI.setBorder(new EmptyBorder(5,5,5,5));
		
		JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        GUI.add(tools, BorderLayout.PAGE_START);
        JButton newGameButton = new JButton("New Game");
        newGameButton.putClientProperty( "X", -1);
        newGameButton.putClientProperty( "Y", -1);
        newGameButton.putClientProperty( "newGame", true);
        newGameButton.addActionListener(this);
        tools.add(newGameButton);
		
        GUI.add(new JLabel("?"), BorderLayout.LINE_START);
        
        reversiBoard = new JPanel(new GridLayout(0, 9));
        reversiBoard.setBorder(new LineBorder(Color.BLACK));
        GUI.add(reversiBoard);
        
        
        for (int i = 0; i < ReversiBoard.length; i++) {
            for (int j = 0; j < ReversiBoard[i].length; j++) {
                
            		ReversiButton b = new ReversiButton("");
                
            		b.putClientProperty( "X", i);
            		b.putClientProperty( "Y", j);
            		b.putClientProperty( "newGame", false);
            		b.addActionListener(this);
                
            		ReversiBoard[i][j] = b;
            }
        }
        
        //fill the top row
        reversiBoard.add(new JLabel(""));
        
        // fill the top row
        for (int i = 0; i < 8; i++) {
        		reversiBoard.add(
                    new JLabel(COLS.substring(i, i + 1),
                    SwingConstants.CENTER));
        }
        
        // fill the black non-pawn piece row
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (j) {
                    case 0:
                    		reversiBoard.add(new JLabel("" + (i + 1),SwingConstants.CENTER));
                    default:
                    		reversiBoard.add(ReversiBoard[i][j]);
                }
            }
        }
	}
	
	/*
	 * Implements the Event Listener Action
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		int x = (int) button.getClientProperty("X");
		int y = (int) button.getClientProperty("Y");
		boolean newGame = (boolean) button.getClientProperty("newGame");
		
		if (newGame) newGame();
		else boardButtonPressed(x,y);
	}
	
	public void boardButtonPressed(int x, int y) {
		System.out.println("The button ["+ x +"," + y +"] was pressed.");
		
	}
	
	public void newGame() {
		System.out.println("New Game.");
		// Create the Gameboard
		gb = new GameBoard();
		
		// Create Player
		Player1 = new BasePlayer(GameBoard.WHITE,GameBoard.BLACK);
		Player2 = new BasePlayer(GameBoard.BLACK,GameBoard.WHITE);
		
		// create starting position
		gb.makeMove(Player1.getColor(),new Coordinates(4,3));
		gb.makeMove(Player1.getColor(),new Coordinates(3,4));
		gb.makeMove(Player2.getColor(),new Coordinates(3,3));
		gb.makeMove(Player2.getColor(),new Coordinates(4,4));
		
		// update GameBoard
		updateGameBoard();
		
	}

	public void updateGameBoard() {
		System.out.println("Update GameBoard");
		for(int i = 0; i < gb.getSize(); i++) {
			for( int j = 0; j < gb.getSize(); j++) {
				System.out.println("Board at [" + i + " , " + j + "] is " + gb.Board[i][j]);
				if (gb.Board[i][j] == GameBoard.EMPTY) ReversiBoard[i][j].setBackground(Color.GREEN);
				if (gb.Board[i][j] == GameBoard.WHITE) ReversiBoard[i][j].setBackground(Color.WHITE);
				if (gb.Board[i][j] == GameBoard.BLACK) ReversiBoard[i][j].setBackground(Color.BLACK);
			}
		}
	}
	
}