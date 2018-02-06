package Search;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
 
public class ReversiButton extends JButton {
 
    // ==========================================================
  	// Private Properties
  	// ========================================================== 

	private static final long serialVersionUID = 1L;  
	
    // ==========================================================
  	// Constructor
  	// ========================================================== 
	
	public ReversiButton(String label) {
    super(label);
  
	setBorder(new LineBorder(Color.BLACK,1));
	setBackground(Color.LIGHT_GRAY);
	
    Dimension size = new Dimension(60,60);
    setPreferredSize(size);
 
    setContentAreaFilled(true);
	
	}
}