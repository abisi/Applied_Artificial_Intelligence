package Search;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
 
public class ReversiButton extends JButton {
 
	private static final long serialVersionUID = 1L;  
	
	public ReversiButton(String label) {
    super(label);
  
	setBorder(new LineBorder(Color.BLACK,1));
    
    Dimension size = new Dimension(60,60);
    setPreferredSize(size);
 
    setContentAreaFilled(true);
  }
 
  protected void paintComponent(Graphics g) {
    if (getModel().isArmed()) {
      g.setColor(Color.gray);
    } else {
      g.setColor(getBackground());
    }
    g.fillOval(0, 0, getSize().width - 6, getSize().height - 6);
 
    super.paintComponent(g);
  }
 
  protected void paintBorder(Graphics g) {
    g.setColor(Color.darkGray);
    g.drawOval(0, 0, getSize().width - 6, getSize().height - 6);
  }
 
  // Hit detection.
  Shape shape;
 
  public boolean contains(int x, int y) {
    // If the button has changed size,  make a new shape object.
    if (shape == null || !shape.getBounds().equals(getBounds())) {
      shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
    }
    return shape.contains(x, y);
  }
}