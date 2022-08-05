package Chapter03;

/**
 * Testing the double-choice structure of the if-else
 * @author Woo
 *
 */

public class TestIfElse {
	public static void main(String[] args) {
		int h = (int)(6 * Math.random() + 1);
		System.out.println(h);
		if( h <= 3) {
			System.out.println("Small");
		} else {
			System.out.println("Big");
		}
		
		System.out.println("-------------");
		// create a random radius between [0.0,4.0), and use it to calculate the perimeter and area of circle
		double r = 4 * Math.random();
		// Math.pow(r,2) means square of r
		double area = Math.PI * Math.pow(r, 2);
		double circle = 2 * Math.PI * r;
		System.out.println("The radius is:" + r);
		System.out.println("The area is:" + area);
		System.out.println("The circle is:" + circle);
		// if the area is greater than circle, print area is greater than circle and vice versa
		if (area >= circle) {
			System.out.println("area are greater than circle");
		} else {
			System.out.println("circle are greater than area");
		}
	}

}
