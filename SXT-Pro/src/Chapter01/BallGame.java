package Chapter01;
import java.awt.*;
import javax.swing.*;

public class BallGame extends JFrame {
	Image ball = Toolkit.getDefaultToolkit().getImage("images/ball.png");
	Image desk = Toolkit.getDefaultToolkit().getImage("images/desk.jpg");
	double x = 100;
	double y = 100;
	boolean right = true;
	
	//the method of drawing a window
	public void paint(Graphics g) { // creating an argument g
		System.out.println("Draw a window");
		g.drawImage(desk, 0, 0, null); // calling the method by g
		g.drawImage(ball, (int) x,(int) y, null);
		if (right) { // right == true
			x = x + 10;
		}
		else {
			x = x - 10;
		}
		if (x > 786) { // 856 - 40 -30
			right = false;
		}
		if (x < 40) {
			right = true;
		}
	}
	
	//loading the window
	void launchFrame() {
		setSize(856,500);
		setLocation(50,50); 
		// coordinate of the top-left of the window
		setVisible(true);
		//repaint the window 25 times per second
		while (true) {
			repaint(); // calling the method paint
			try {
				Thread.sleep(40); // 40 Ms
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("This is Woo");
		BallGame game = new BallGame(); 
		// game is a variable, game is calling the method launchFrame
		game.launchFrame();
	}
}