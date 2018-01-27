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
    private JButton[][] ReversiBoardSquares = new JButton[8][8];
    private JPanel reversiBoard;
    private static final String COLS = "ABCDEFGH";

    
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// Main Method
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%    
	public static void main(String[] args) {
		
		// sets creates the reversi class
		Reversi rvs = new Reversi();
		
		// set up the fram
		JFrame frame = new JFrame("Reversi by se4054pf-s and ax5006bi-s");
        frame.add(rvs.getGui());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);

        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        frame.pack();
        // ensures the minimum size is enforced.
        frame.setMinimumSize(frame.getSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        GUI.add(tools, BorderLayout.PAGE_START);
        JButton newGameButton = new JButton("New Game");
        newGameButton.putClientProperty( "X", -1);
        newGameButton.putClientProperty( "Y", -1);
        newGameButton.putClientProperty( "newGame", true);
        newGameButton.addActionListener(this);
        tools.add(newGameButton);
		
        reversiBoard = new JPanel(new GridLayout(0, 9));
        GUI.add(reversiBoard);
        
        // create the reversi board squares
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < ReversiBoardSquares.length; i++) {
            for (int j = 0; j < ReversiBoardSquares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                b.setPreferredSize(new Dimension(60,60));
                b.putClientProperty( "X", i);
                b.putClientProperty( "Y", j);
                b.putClientProperty( "newGame", false);
                b.addActionListener(this);
                b.setForeground(Color.RED);
                b.setOpaque(true);
                ReversiBoardSquares[i][j] = b;
            }
        }
        
        //fill the reversi board
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
                    		reversiBoard.add(ReversiBoardSquares[i][j]);
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
		
		if (newGame) newGameButtonPressed();
		else boardButtonPressed(x,y);
	}
	
	public void boardButtonPressed(int x, int y) {
		System.out.println("The button ["+ x +"," + y +"] was pressed.");
	}
	
	public void newGameButtonPressed() {
		System.out.println("The new Game button was pressed.");
	}

}