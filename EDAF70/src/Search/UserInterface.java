package Search;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

public class UserInterface {

	// ==========================================================
	// Private Members
	// ==========================================================
	private JFrame frame;
	private final JPanel GUIPanel = new JPanel(new BorderLayout(3, 3));
    private JPanel reversiBoardPanel;
	private ReversiButton[][] ReversiBoard = new ReversiButton[8][8];
    private static final String COLS = "ABCDEFGH";
    private JLabel Log;
    
    // ==========================================================
 	// Constructor
 	// ========================================================== 
    public UserInterface(BasePlayer Player1, BasePlayer Player2) {
		initializeGUIPanel(Player1, Player2);
		setupFrame();

    }
    
    // ==========================================================
   	// Public Methods
   	// ========================================================== 
    public String position(Coordinates move) {
    	String pos = "[ " + Integer.toString(move.X + 1) + " , ";
    	pos += Character.toString(COLS.charAt(move.Y));
    	return pos += " ]";
    }
    
    public final JComponent getReversiBoard() {
        return reversiBoardPanel;
    }
    
    public final JComponent getGui() {
        return GUIPanel;
    } 
    
    public void updateGameBoard(GameBoard gb) {
		for(int i = 0; i < gb.getSize(); i++) {
			for( int j = 0; j < gb.getSize(); j++) {
				if (gb.Board[i][j] == GameBoard.EMPTY) ReversiBoard[i][j].setBackground(Color.GREEN);
				if (gb.Board[i][j] == GameBoard.WHITE) ReversiBoard[i][j].setBackground(Color.WHITE);
				if (gb.Board[i][j] == GameBoard.BLACK) ReversiBoard[i][j].setBackground(Color.BLACK);
			}
		}
	}
    
    public void showPossibleMoves(ArrayList<Coordinates> moves) {
		for(Coordinates move : moves) {
			ReversiBoard[move.X][move.Y].setBackground(Color.GRAY);
		}
	}
    
    public int chooseColor() {
		Object[] options = {"WHITE", "BLACK"};
		return JOptionPane.showOptionDialog(
			frame,
			"Would you rather play the black or the white player?",
			"Player 1: Choose your Color",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,     //do not use a custom Icon
			options,  //the titles of buttons
			options[1]); //default button title
    }
    
    public int chooseTimeLimit() {
    		Object[] possibilities = {"1","2","3","4","5","6"};
    		String n = (String)JOptionPane.showInputDialog(
    	                    frame,
    	                    "How many seconds should the time limit be?",
    	                    "Choose time limit",
    	                    JOptionPane.PLAIN_MESSAGE,
    	                    null,
    	                    possibilities,
    	                    possibilities[0]);
    		return (n != null) ?  Integer.parseInt(n) : null;
    }
    
    public void endGame(int StonesPlayer1, int StonesPlayer2) {
    	
    		// construct the message 
    		String message = "The result is \n"; 
    		message += "( " + StonesPlayer1 + " : " + StonesPlayer2 + " )\n";
    		message += "( Player 1 : Player 2 )";
 
    		// display the result to the user
    		JOptionPane.showMessageDialog(frame, message, "The game is over!", JOptionPane.INFORMATION_MESSAGE);
    	
    		// close the window
    		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
    
    public void addLogLine(String line) {
    	String newText = 
    			"<html>" + Log.getText() + "<br>" + line;
    	Log.setText(newText);
    }
    
    // ==========================================================
  	// Private Methods
  	// ========================================================== 
    private void setupFrame() {
        frame = new JFrame("Reversi by se4054pf-s and ax5006bi-s");
        frame.add(GUIPanel);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);    }
	
    private final void initializeGUIPanel(BasePlayer Player1, BasePlayer Player2) {
		
		// Customize the content panel
		GUIPanel.setBorder(new EmptyBorder(5,5,5,5));
		GUIPanel.setBackground(Color.WHITE);
		
		// Generate the log
		Log = new JLabel("This is the log of the game:        ");
		Log.setVerticalAlignment(SwingConstants.BOTTOM);
		Log.setHorizontalAlignment(SwingConstants.LEFT);
		Log.setAutoscrolls(true);
        GUIPanel.add(Log, BorderLayout.LINE_START);
        
        // Create the Panel for the ReversiBoard
        reversiBoardPanel = new JPanel(new GridLayout(0, 9));
        reversiBoardPanel.setBackground(Color.WHITE);
        reversiBoardPanel.setBorder(new LineBorder(Color.BLACK));
        GUIPanel.add(reversiBoardPanel);
        
        
        for (int i = 0; i < ReversiBoard.length; i++) {
            for (int j = 0; j < ReversiBoard[i].length; j++) {
                
            		ReversiButton b = new ReversiButton("");
                
            		b.putClientProperty( "X", i);
            		b.putClientProperty( "Y", j);
            		if(Player1 instanceof HumanPlayer) b.addActionListener(Player1);
            		if(Player2 instanceof HumanPlayer) b.addActionListener(Player2);
                
            		ReversiBoard[i][j] = b;
            }
        }
        
        //fill the top row
        reversiBoardPanel.add(new JLabel(""));
        
        // fill the top row
        for (int i = 0; i < 8; i++) {
        		reversiBoardPanel.add(
        				new JLabel(COLS.substring(i, i + 1),
                    SwingConstants.CENTER));
        }
        
        // fill the black non-pawn piece row
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (j) {
                    case 0:
                    		reversiBoardPanel.add(new JLabel("" + (i + 1),SwingConstants.CENTER));
                    default:
                    		reversiBoardPanel.add(ReversiBoard[i][j]);
                }
            }
        }
	}
}
