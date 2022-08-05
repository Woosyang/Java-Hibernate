package Chapter01;
import java.awt.*;
import javax.swing.*;

public class BallGamePro extends JFrame {
	Image ball = Toolkit.getDefaultToolkit().getImage("images/ball.png");
	Image desk = Toolkit.getDefaultToolkit().getImage("images/desk.jpg");
	double x = 100;
	double y = 100;
	double degree = 3.14/3; // radius
	
	// the method of drawing a window
	public void paint(Graphics g) { // creating an argument g
		System.out.println("Draw a window");
		g.drawImage(desk, 0, 0, null); // calling the method by g
		g.drawImage(ball, (int) x,(int) y, null);
		x = x + 10 * Math.cos(degree);
		y = y + 10 * Math.sin(degree);
		if (y < 40 + 40 || y > 430) { // the width of the banner
			degree = -degree; // flip x
		}
		if (x > 786 || x < 40) {
			degree = 3.14 - degree; // flip y
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
			} catch(Exception e) {
				e.printStackTrace();
				}
		}
	}

	public static void main(String[] args) {
		System.out.println("This is Woo");
		BallGamePro game = new BallGamePro(); 
		// game is a variable, game is calling the method launchFrame
		game.launchFrame();
	 }
	}
