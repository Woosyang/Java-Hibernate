package JavaGame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * the main entrance of the game
 * @author Woo
 *
 */
public class MyGameFrame extends JFrame {
	Image ball = GameUtil.getImage("images/ball.png");
	public void launchFrame() {
		this.setTitle("Fighting Plane");
		this.setVisible(true);
		this.setSize(500, 500);
		this.setLocation(300, 300); // the position of the windows
		this.getBackground();
		// close the window and terminate the program
		this.addWindowListener(new WindowAdapter() {
			@Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
	}
	
	// be called automatically
	public void paint(Graphics g) {
		super.paint(g); // override
		Color c = g.getColor();
		Font f = g.getFont();
		g.setColor(Color.cyan);
		g.drawLine(100, 100, 300, 300);
		g.drawRect(100, 100, 100, 100);
		g.drawOval(100, 200, 100, 200);
		g.setFont(new Font("Consolas", Font.BOLD, 50));
		g.drawString("Hello", 100, 200);
		g.drawImage(ball, 300, 300, null);
		g.setColor(c); // restore
		g.setFont(f);
	}
	
	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();
		f.launchFrame();
	}
}
