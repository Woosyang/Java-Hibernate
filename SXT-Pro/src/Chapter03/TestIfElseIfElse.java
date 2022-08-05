package Chapter03;

/**
 * Testing the multiple-choice structure of if-else
 * @author Woo
 *
 */

public class TestIfElseIfElse {
	public static void main(String[] args){
		int age = (int)(100 * Math.random()); //[0,100)
		System.out.print("The age is " + age + ", belongs to ");
		if (age < 15){
			System.out.print("Children, Play~"); // println
		} else if (age < 30) {
			System.out.print("Teenager, Learn!"); // println
		} else if (age < 45) {
			System.out.print("Middle-aged, Work~"); // println
		} else if (age < 65) {
			System.out.print("Quinquagenarian, Exercise!"); // println
		} else if (age < 85) {
			System.out.print("Aged, Calcium~"); // println
		}else if (age < 100) {
			System.out.print("Macrobian, Incredible~"); // println
		}
	}
}
