package Search;
/*
 * A class that represents the game-user interface.
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Reversi {

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// Private Members
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	private final JPanel GUI = new JPanel(new BorderLayout(3, 3));
    private JButton[][] ReversiBoardSquares = new JButton[8][8];
    private JPanel reversiBoard;
    private static final String COLS = "ABCDEFGH";

    
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// Main Method
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%    
	public static void main(String[] args) {
		Reversi rvs = new Reversi();
		
		JFrame frame = new JFrame("Reversi by se4054pf-s and ax5006bi-s");
        frame.add(rvs.getGui());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);

        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        frame.pack();
        // ensures the minimum size is enforced.
        frame.setMinimumSize(frame.getSize());
        frame.setVisible(true);
		
	}
	
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// Constructor
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	Reversi() {
		initializeGUI();
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
        reversiBoard = new JPanel(new GridLayout(0, 9));
        reversiBoard.setBorder(new LineBorder(Color.BLACK));
        GUI.add(reversiBoard);
        
        // create the reversi board squares
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < ReversiBoardSquares.length; i++) {
            for (int j = 0; j < ReversiBoardSquares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                b.setPreferredSize(new Dimension(60,60));
                b.setBackground(Color.GREEN);
                ReversiBoardSquares[j][i] = b;
            }
        }
        
        //fill the chess board
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
                    		reversiBoard.add(new JLabel("" + (i + 1),
                                SwingConstants.CENTER));
                    default:
                    		reversiBoard.add(ReversiBoardSquares[j][i]);
                }
            }
        }
	}

}