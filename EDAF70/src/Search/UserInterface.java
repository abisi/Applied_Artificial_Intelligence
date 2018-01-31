package Search;

import java.awt.*;
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
		for(int i = 0; i < moves.size() ; i++) {
			ReversiBoard[moves.get(i).X][moves.get(i).Y].setBackground(Color.GRAY);
		}
	}
    
    // ==========================================================
  	// Private Methods
  	// ========================================================== 
    private void setupFrame() {
		frame = new JFrame("Reversi by se4054pf-s and ax5006bi-s");
        frame.add(GUIPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
	
    private final void initializeGUIPanel(BasePlayer Player1, BasePlayer Player2) {
		
		// Customize the content panel
		GUIPanel.setBorder(new EmptyBorder(5,5,5,5));
		
		// Make space for later user
		JLabel log = new JLabel("J");
        GUIPanel.add(log, BorderLayout.LINE_START);
        
        // Create the Panel for the ReversiBoard
        reversiBoardPanel = new JPanel(new GridLayout(0, 9));
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
