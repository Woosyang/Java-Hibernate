package Chapter03;

/**
 * Testing the switch
 * when you encounter the multi-valued decision, use the switch. And the switch also can be replaced by if else.
 * @author Woo
 *
 */

public class TestSwitch {
	public static void main(String[] args) {
		int month = (int)(1 + 12 * Math.random());
		System.out.println("Month: " + month);
		
		switch(month) {
		case 1:
			System.out.println("January");
			break;
		case 2:
			System.out.println("February");
			break;
		default:
			System.out.println("Others");
			break;
		}
		
		System.out.println("------------");
		char c = 'a';
		int rand = (int)(26 * Math.random()); // [0,26) a -> 0
		char d = (char)(c + rand);
		System.out.print(d + " :");
		
		switch(d) {
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u':
			System.out.println(" vowel");
			break;
		case 'y':
		case 'w':
			System.out.println(" semi-vowel");
			break;
		default:
			System.out.println(" consonant");
			break; // not necessary if default is the last line of switch
		}
	}
}
