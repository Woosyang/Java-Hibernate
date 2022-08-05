package Chapter08;
import java.util.*;

/**
 * Test the enumerating
 * @author Woo
 *
 */

public class TestEnum {
	public static void main(String[] args) {
		// : colon
		System.out.println(Season.SPRING);
		for (Week W : Week.values()/* an array */) {
			System.out.print(W);
			System.out.print(" ");
		}
		System.out.println();
		int A = new Random().nextInt(4);
		switch(Season.values()[A]) { // Season[0], Season[1], Season[2], Season[3]		
		case SPRING:
			System.out.println("Warm");
			break;
		case SUMMER:
			System.out.println("Hot");
			break;
		case AUTUMN:
			System.out.println("Cool");
			break;
		case WINTER:
			System.out.println("Cold");
			break;
		default:
			break;
		}
	}
}

// normally use this to define a group of constant
/* Season */
enum Season {
	SPRING, SUMMER, AUTUMN, WINTER
}

/* Weekday */
enum Week {
	Mon, Tue, Wes, Thu, Fri, Sat, Sun
}