package Chapter06;

/**
 * Testing the customized Exception
 * @author Woo
 *
 */

public class TestException04 {
	public static void main(String[] args) {
		Person a = new Person();
		a.setAge(-10);
	}
}

class Person {
	private int age;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		if (age < 0) {
			// not throws
			throw new IllegalAgeException("age can't be negative");
			/*
			 * if the customized Exception extends just the Exception
			 * do throws or try catch
			 * try {
			 *  throw new IllegalAgeException("age can't be negative");
			 *  } catch (IllegalAgeException e) {
			 *  	e.printStackTrace();
			 *  }
			 */
		}
		this.age = age;
	}
}

class IllegalAgeException extends RuntimeException /*Exception*/ {
	public IllegalAgeException() {} // default constructor
	public IllegalAgeException(String msg) { // output the relevant message
		super(msg);
	}
}